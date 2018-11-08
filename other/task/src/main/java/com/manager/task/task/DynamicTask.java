package com.manager.task.task;

import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * dynamic task
 *
 * @author chao
 */
@Slf4j
@Component
public class DynamicTask {

    @Autowired
    private Scheduler scheduler;

    /**
     * 更新定时任务的触发表达式
     *
     * @param triggerName 触发器名字
     * @param groupName 触发器group名字
     * @param start true启动/false停止
     */
    public boolean pauseOrResumeTrigger(String triggerName, String groupName, boolean start) {
        if (StringUtils.isBlank(groupName)) {
            groupName = Scheduler.DEFAULT_GROUP;
        }
        try {
            CronTrigger trigger = (CronTrigger) getTrigger(triggerName, groupName);
            if (trigger != null && trigger.getKey() != null) {
                if (start) {
                    scheduler.resumeTrigger(trigger.getKey());
                } else {
                    scheduler.pauseTrigger(trigger.getKey());
                }
            }
            return true;
        } catch (SchedulerException e) {
            log.error("Fail to reschedule. " + e);
            return false;
        }
    }

    /**
     * 更新定时任务的触发表达式
     *
     * @param triggerName 触发器名字
     * @param cronExpression 触发表达式
     * @return 成功则返回true，否则返回false
     */
    public boolean rescheduleJob(String triggerName, String groupName, String cronExpression) {
        if (StringUtils.isBlank(groupName)) {
            groupName = Scheduler.DEFAULT_GROUP;
        }
        try {
            CronTrigger trigger = (CronTrigger) getTrigger(triggerName, groupName);
            if (trigger == null) {
                return false;
            }
            if (StringUtils.equals(trigger.getCronExpression(), cronExpression)) {
                return true;
            }
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            triggerBuilder.withIdentity(trigger.getKey());
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression));
            triggerBuilder.startNow();
            //new trigger
            trigger = (CronTrigger) triggerBuilder.build();

            scheduler.rescheduleJob(trigger.getKey(), trigger);
            return true;
        } catch (SchedulerException e) {
            log.error("Fail to reschedule. " + e);
            return false;
        }
    }

    /**
     * 获取触发器
     *
     * @param triggerName 触发器名字
     * @param groupName 触发器组名字
     */
    private Trigger getTrigger(String triggerName, String groupName) {
        Trigger trigger;
        if (StringUtils.isBlank(groupName) || StringUtils.isBlank(triggerName)) {
            log.warn("triggerName or triggerName is empty!");
            return null;
        }
        try {
            trigger = scheduler.getTrigger(TriggerKey.triggerKey(triggerName, groupName));
        } catch (SchedulerException e) {
            log.warn("Fail to get the trigger (triggerName: " + triggerName + ", groupName : " + groupName + ")");
            return null;
        }
        return trigger;
    }

    public boolean createOrRescheduleWithData(String triggerName, String groupName, String cronExpression,
            Class<? extends Job> jobClass, Object jobDataMap) {
        if (StringUtils.isBlank(groupName)) {
            groupName = Scheduler.DEFAULT_GROUP;
        }

        try {
            CronTrigger trigger = (CronTrigger) getTrigger(triggerName, groupName);
            if (trigger == null) {
                /*
                 * job detail
                 */
                JobBuilder jobBuilder = JobBuilder.newJob(jobClass);
                jobBuilder.withIdentity("job" + triggerName, groupName);
                JobDetail jobDetail = jobBuilder.build();

                jobDetail.getJobDataMap().put("data",jobDataMap);

                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                triggerBuilder.withIdentity(TriggerKey.triggerKey(triggerName, groupName));
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression));
                triggerBuilder.startNow();
                //new trigger
                trigger = (CronTrigger) triggerBuilder.build();

                scheduler.scheduleJob(jobDetail, trigger);
                return false;
            } else {
                if (jobDataMap != null) {
                    JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey("job" + triggerName,groupName));
                    if (jobDetail != null) {
                        jobDetail.getJobDataMap().put("data",jobDataMap);
                    }
                    TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                    triggerBuilder.withIdentity(TriggerKey.triggerKey(triggerName, groupName));
                    triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression));
                    triggerBuilder.startNow();
                    //new trigger
                    trigger = (CronTrigger) triggerBuilder.build();

                    scheduler.scheduleJob(jobDetail, Collections.singleton(trigger), true);
                    return true;
                }

                if (StringUtils.equals(trigger.getCronExpression(), cronExpression)) {
                    return true;
                }
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                triggerBuilder.withIdentity(trigger.getKey());
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression));
                triggerBuilder.startNow();
                //new trigger
                trigger = (CronTrigger) triggerBuilder.build();

                scheduler.rescheduleJob(trigger.getKey(), trigger);
                return true;
            }
        } catch (SchedulerException e) {
            log.error("Fail to create or reschedule. " + e);
            return false;
        }
    }

    /**
     * create trigger or reschedule
     *
     * @param triggerName trigger name
     * @param groupName group name
     * @param cronExpression cron expression
     * @param jobClass job class extends job
     * @return true/false
     */
    public boolean createOrReschedule(String triggerName, String groupName, String cronExpression, Class<? extends Job> jobClass) {
        if (StringUtils.isBlank(groupName)) {
            groupName = Scheduler.DEFAULT_GROUP;
        }

        try {
            CronTrigger trigger = (CronTrigger) getTrigger(triggerName, groupName);
            if (trigger == null) {
                /*
                 * job detail
                 */
                JobBuilder jobBuilder = JobBuilder.newJob(jobClass);
                jobBuilder.withIdentity("job" + triggerName, groupName);
                JobDetail jobDetail = jobBuilder.build();

                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                triggerBuilder.withIdentity(TriggerKey.triggerKey(triggerName, groupName));
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression));
                triggerBuilder.startNow();
                //new trigger
                trigger = (CronTrigger) triggerBuilder.build();

                scheduler.scheduleJob(jobDetail, trigger);
                return false;
            } else {
                if (StringUtils.equals(trigger.getCronExpression(), cronExpression)) {
                    return true;
                }
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                triggerBuilder.withIdentity(trigger.getKey());
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression));
                triggerBuilder.startNow();
                //new trigger
                trigger = (CronTrigger) triggerBuilder.build();

                scheduler.rescheduleJob(trigger.getKey(), trigger);
                return true;
            }
        } catch (SchedulerException e) {
            log.error("Fail to create or reschedule. " + e);
            return false;
        }
    }
}

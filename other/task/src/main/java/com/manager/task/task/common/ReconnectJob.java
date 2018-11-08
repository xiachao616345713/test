package com.manager.task.task.common;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * wss reconnect
 *
 * @author chao
 */
@Slf4j
@DisallowConcurrentExecution
public class ReconnectJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        WebSocketClient client = (WebSocketClient) context.getJobDetail().getJobDataMap().get("data");
        if (client.isClosed()) {
            client.reconnect();
            log.info(context.getTrigger().getKey().getName() + " webSocket reconnect");
        }
    }
}

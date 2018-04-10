package com.manager.mybatis;

import com.github.pagehelper.PageHelper;
import java.util.Properties;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * @author chao
 * @since 2018-04-10
 */
//@Configuration
public class MybatisConfigurer {
//
//    @Resource
//    private DataSource dataSource;
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        bean.setTypeAliasesPackage("cn.potato.orm.model");
//
//        //分页插件
//        PageHelper pageHelper = new PageHelper();
//        Properties properties = new Properties();
//        properties.setProperty("reasonable", "true");
//        properties.setProperty("supportMethodsArguments", "true");
//        properties.setProperty("returnPageInfo", "check");
//        properties.setProperty("params", "count=countSql");
//        pageHelper.setProperties(properties);
//
//        //添加插件
//        bean.setPlugins(new Interceptor[]{pageHelper});
//
//        //添加XML目录
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
//        return bean.getObject();
//    }
//
//    @Configuration
//    @AutoConfigureAfter(MybatisConfigurer.class)
//    public static class MyBatisMapperScannerConfigurer {
//
//        @Bean
//        public MapperScannerConfigurer mapperScannerConfigurer() {
//            MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//            mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
//            mapperScannerConfigurer.setBasePackage("cn.potato.orm.mapper");
//            //配置通用mappers
//            Properties properties = new Properties();
//            properties.setProperty("mappers", "cn.potato.orm.core.Mapper");
//            properties.setProperty("notEmpty", "false");
//            properties.setProperty("IDENTITY", "MYSQL");
//            mapperScannerConfigurer.setProperties(properties);
//
//            return mapperScannerConfigurer;
//        }
//
//    }
}

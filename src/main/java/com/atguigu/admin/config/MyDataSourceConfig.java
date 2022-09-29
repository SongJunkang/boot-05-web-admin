package com.atguigu.admin.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

@Configuration
//@Deprecated//过时的，不赞成的意思，把这些直接注释，用starter来启动。
public class MyDataSourceConfig {


    //数据源的总体设计。
    @ConfigurationProperties("spring.datasource")
    @Bean
    public DataSource dataSource() throws SQLException {

        DruidDataSource druidDataSource = new DruidDataSource();

        //加入监控功能。he 防火墙功能。
//        druidDataSource.setFilters("stat,wall");
        druidDataSource.setMaxActive(100);
        return druidDataSource;
    }

    /**
     * 配置Druid的监控页功能
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){


        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(statViewServlet, "/druid/*");

        registrationBean.addInitParameter("loginUsername","admin");
        registrationBean.addInitParameter("loginPassword","abc123");
        return  registrationBean;
    }


    /**
     * WebStatFilter用于采集web-jdcb关联监控的数据。
     * @return
     */

    @Bean
    public FilterRegistrationBean webStatFilter(){


        WebStatFilter webStatFilter = new WebStatFilter();

        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>(webStatFilter);

        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;

    }


}

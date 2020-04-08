package com.springboot.supermarket.config;/*
 @author yanziming
 @date 2020/4/7 - 15:53
 */

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    public DataSource druid(){

        return new DruidDataSource();
    }

    //配置监控
    //  配置监控
    //1）、配置一个管理后台的servlet

    //这里注册一个servlet到容器中
    //主要是处理StatViewServlet 这个servlet已经由druid配置了一个后台了 它的父servelt是ResourceServlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*" );
        //配置一些初始化参数
        Map<String,String> initParams=new HashMap<String, String>();

        //登录后台的账号和密码
        //这些属性在它的父servelt ：ResourceServlet 中
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123");

        //允许谁访问
        //initParams.put("allow","localhost"); 允许本地访问
        initParams.put("allow","");//默认允许所有访问

        //拒绝谁访问
        // initParams.put(" deny","ip地址");


        bean.setInitParameters(initParams);
        return bean;
    }




    //2）、配置一个监控的filter
    //主要是拦截这个Filter:  WebStatFilter
    @Bean
    public FilterRegistrationBean webStatFilter(){

        FilterRegistrationBean bean = new FilterRegistrationBean();
        //拦截这个Filter
        bean.setFilter( new WebStatFilter() );

        //配置一些初始化参数
        Map<String,String> initParams=new HashMap<String, String>();

        //不拦截哪些请求
        initParams.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);

        //拦截请求
        bean.setUrlPatterns(Arrays.asList("/*"));

        return bean;


    }
}

package com.myproj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.myproj")
@ImportResource("classpath*:spring/spring.xml")
@MapperScan("com.myproj.dao") //用于扫描jar包中的mapper
public class PortalApplication
{

    public static void main(String[] args)
    {

        SpringApplication.run(PortalApplication.class, args);
    }

}


package com.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 服务注册
 * @Author LettleCadet
 * @Date 2019/2/21
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.registerService")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RegisterApplication
{
    private static final Logger logger = LoggerFactory.getLogger(RegisterApplication.class);

    public static void main(String[] args)
    {

        SpringApplication.run(RegisterApplication.class, args);

    }

}


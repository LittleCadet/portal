package com.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 发现服务
 * LittleCadet
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.discoveryservice")
@SpringBootApplication
public class DiscoveryApplication
{
    private static final Logger logger = LoggerFactory.getLogger(DiscoveryApplication.class);

    public static void main(String[] args)
    {

        SpringApplication.run(DiscoveryApplication.class, args);

    }

}


package com.application;

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
    public static void main(String[] args)
    {

        SpringApplication.run(DiscoveryApplication.class, args);

    }

}


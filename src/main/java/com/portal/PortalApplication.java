package com.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.portal")
@SpringBootApplication
public class PortalApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(PortalApplication.class, args);
    }

}


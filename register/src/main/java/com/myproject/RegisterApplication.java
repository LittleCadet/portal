package com.myproject;

import com.myproject.entity.ZkClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class RegisterApplication
{

    public static void main(String[] args)
    {
        //ApplicationContext context = SpringApplication.run(RegisterApplication.class, args);

        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/spring.xml"});
        ZkClient client = (ZkClient)context.getBean("zkClient");
        client.init();
        client.register();
    }

}


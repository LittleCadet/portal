package com.myproject;

import com.myproject.entity.ZkClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RegisterApplication
{

    public static void main(String[] args)
    {
        ApplicationContext context = SpringApplication.run(RegisterApplication.class, args);

        ZkClient client = context.getBean(ZkClient.class);
        client.register();
    }

}


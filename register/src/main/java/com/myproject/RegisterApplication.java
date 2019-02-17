package com.myproject;

import com.myproject.entity.CuratorClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class RegisterApplication
{

    /**
     *
     * 通过Curator完成服务注册成功后，zk会立刻同步节点信息到注册的主机上，但是如果直接在linux上，zk不会同步节点信息
     * @param args
     */
    public static void main(String[] args)
    {
        //ApplicationContext context = SpringApplication.run(RegisterApplication.class, args);

        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/spring.xml"});
        CuratorClient client = (CuratorClient)context.getBean("curatorClient");
        client.init();
        client.getServiceDiscovery();
        client.register();
    }

}


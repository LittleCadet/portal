package com.example.demo;

import com.google.gson.GsonBuilder;
import com.myproject.entity.CuratorClient;
import com.myproject.entity.ServerPayload;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceInstance;
import org.omg.CORBA.ServiceDetail;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

@SpringBootApplication
public class DemoApplication
{

    public static void main(String[] args)
    {
        //SpringApplication.run(DemoApplication.class, args);

        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/spring.xml"});
        CuratorClient client = (CuratorClient)context.getBean("curatorClient");
        client.init();
        client.getServiceDiscovery();
        try
        {
            Collection<ServiceInstance<ServerPayload>> myServices = client.queryForInstances("prometheus");
            if(!CollectionUtils.isEmpty(myServices))
            {
                for(ServiceInstance<ServerPayload> si : myServices)
                {
                    System.out.println("myService:" + new GsonBuilder().create().toJson(si));
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("curator查找服务失败");
        }
    }

}


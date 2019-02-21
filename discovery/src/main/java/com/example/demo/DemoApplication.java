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
import java.util.List;

@SpringBootApplication
public class DemoApplication
{

    public static void main(String[] args)
    {
        //SpringApplication.run(DemoApplication.class, args);

        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/spring.xml"});
        CuratorClient client = (CuratorClient)context.getBean("curatorClient");
        CuratorFramework curatorClient = client.init();
        client.getServiceDiscovery();

        try
        {
            List<String> list = curatorClient.getChildren().forPath("/myServices/prometheus");
            System.out.println("list.size():" + list.size());
            for (String li : list)
            {
                System.out.println("服务名：" + li);
            }
        }
        catch (Exception e)
        {
            System.out.println("遍历节点失败：e" + e);
        }

        /*try
        {
            byte[] datas = curatorClient.getData().forPath("/myServices/prometheus/prometheus_ftp_20190218_2");
            System.out.println("datas:" + datas.length);
            for(byte data : datas)
            {
                System.out.println("data:" + data);
            }

        }
        catch (Exception e)
        {
            System.out.println("获取节点失败：e:"+e);
        }*/
    }

}


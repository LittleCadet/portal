package com.example.demo;

import com.myproject.RegisterApplication;
import com.myproject.entity.CuratorClient;
import com.myproject.tools.CuratorTools;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.CollectionUtils;
import java.io.IOException;
import java.util.List;

/**
 * 发现服务
 * LittleCadet
 */
@SpringBootApplication
public class DiscoveryApplication
{
    private static final Logger logger = LoggerFactory.getLogger(DiscoveryApplication.class);

    public static void main(String[] args)
    {
        String nodePath = "/myServices/prometheus";

        SpringApplication.run(DiscoveryApplication.class, args);
        CuratorClient client= new CuratorClient();
        CuratorFramework curatorClient = null;
        ServiceDiscovery serviceDiscovery = null;

        try
        {
            curatorClient = client.init();

            if(logger.isDebugEnabled())
            {
                logger.debug("CuratorFramework was started !");
            }

            serviceDiscovery = CuratorTools.getServiceDiscovery(curatorClient);

            if(logger.isDebugEnabled())
            {
                logger.debug("ServiceDiscovery was started !");
            }

            List<String> list = CuratorTools.getServices(curatorClient,nodePath);

            System.out.println("服务名：" + list.toString());

            if(logger.isDebugEnabled())
            {
                logger.debug("serviceName in node path are :" + list.toString());
            }
        }
        catch (Exception e)
        {
            logger.error("get serviceName failed ! e" + e);
        }
        finally
        {
            try
            {
                CuratorTools.closeResource(curatorClient,serviceDiscovery);

                if(logger.isDebugEnabled())
                {
                    logger.debug("CuratorClient and ServiceDiscovery were closed");
                }
            }
            catch (IOException e)
            {
                logger.error("IO exception when close CuratorClient and ServiceDiscovery, exception : " + e);
            }
        }

    }

}


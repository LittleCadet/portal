package com.discoveryservice.controller;

import com.discoveryservice.entity.CuratorClient;
import com.registerservice.tools.CuratorTools;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.util.List;

/**
 * @Author LettleCadet
 * @Date 2019/2/24
 */
@Controller
public class DiscoveryController
{
    private static final Logger logger = LoggerFactory.getLogger(DiscoveryController.class);

    @Autowired
    private CuratorClient client;

    @Value("${zookeeper.nodePath}")
    private String nodePath;

    @RequestMapping("/discoveryService")
    @ResponseBody
    public String discoveryService()
    {
        List<String> list = null;
        CuratorFramework curatorClient = null;
        ServiceDiscovery serviceDiscovery = null;

        try
        {
            curatorClient = client.init();

            if(logger.isDebugEnabled())
            {
                logger.debug("DiscoveryController.discoveryService():CuratorFramework was started !");
            }

            serviceDiscovery = CuratorTools.getServiceDiscovery(curatorClient,client.getRootNode());

            if(logger.isDebugEnabled())
            {
                logger.debug("DiscoveryController.discoveryService():ServiceDiscovery was started !");
            }

            list = CuratorTools.getServices(curatorClient,nodePath);

            if(logger.isDebugEnabled())
            {
                logger.debug("DiscoveryController.discoveryService():serviceName in node path are :" + list.toString());
            }
        }
        catch (Exception e)
        {
            logger.error("DiscoveryController.discoveryService():get serviceName failed ! e" + e);

            return "get register service failed !";
        }
        finally
        {
            try
            {
                CuratorTools.closeResource(curatorClient,serviceDiscovery);

                if(logger.isDebugEnabled())
                {
                    logger.debug("DiscoveryController.discoveryService():CuratorClient and ServiceDiscovery were closed");
                }
            }
            catch (IOException e)
            {
                logger.error("DiscoveryController.discoveryService():IO exception when close CuratorClient and ServiceDiscovery, exception : " + e);
            }
        }

        return "services names：" + list.toString();
    }
}

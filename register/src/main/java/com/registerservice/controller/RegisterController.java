package com.registerservice.controller;

import com.registerservice.entity.CuratorClient;
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

/**
 * @Author LettleCadet
 * @Date 2019/2/24
 */
@Controller
public class RegisterController
{
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private CuratorClient client;

    @Value("${zookeeper.childNodePath}")
    private String childNodePath;

    @Value("${zookeeper.serviceName}")
    private String serviceName;

    /**
     *
     * 通过Curator完成服务注册成功后，zk会立刻同步节点信息到注册的主机上，但是如果直接在linux上，zk不会同步节点信息
     */
    @RequestMapping("/registerService")
    @ResponseBody
    public String registerService()
    {
        logger.debug("debug is ok!");

        logger.info("info is ok!");

        logger.warn("warn is ok!");

        logger.error("error is ok!");


        CuratorFramework curatorClient = null;
        ServiceDiscovery serviceDiscovery = null;
        try
        {
            curatorClient = client.init();

            if(logger.isDebugEnabled())
            {
                logger.debug("RegisterController.registerService():CuratorFramework was started !");
            }

            serviceDiscovery = CuratorTools.getServiceDiscovery(curatorClient,client.getRootNode());

            if(logger.isDebugEnabled())
            {
                logger.debug("RegisterController.registerService():ServiceDiscovery was started !");
            }

            //除了根节点以外的节点路径
            CuratorTools.register(childNodePath + serviceName,client.getRootNode());

            if(logger.isDebugEnabled())
            {
                logger.debug("RegisterController.registerService():register service succeed !,serviceName is" + serviceName );
            }
        }
        catch (Exception e)
        {
            logger.error("RegisterController.registerService():something wrong with curator , exception : " + e);

            return "service register failed in zk ! ";
        }
        finally
        {
            try
            {
                CuratorTools.closeResource(curatorClient,serviceDiscovery);

                if(logger.isDebugEnabled())
                {
                    logger.debug("RegisterController.registerService():CuratorClient and ServiceDiscovery were closed");
                }
            }
            catch (IOException e)
            {
                logger.error("RegisterController.registerService():IO exception when close CuratorClient and ServiceDiscovery, exception : " + e);

                return "service register failed in zk ! ";
            }
        }

        return "service register success in zk !";
    }

}

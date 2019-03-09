package com.portal.serviceimpl;

import com.portal.entity.CuratorClient;
import com.portal.service.DiscoveryService;
import com.portal.util.CuratorUtil;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import java.io.IOException;
import java.util.List;

/**
 * 从zk发现服务的实现类
 *
 * @Author LettleCadet
 * @Date 2019/3/9
 */
public class DiscoveryServiceImpl implements DiscoveryService
{
    private static final Logger logger = LoggerFactory.getLogger(DiscoveryServiceImpl.class);

    @Autowired
    private CuratorClient client;

    @Value("${zookeeper.nodePath}")
    private String nodePath;

    /**
     * 从zk发现服务
     *
     * @return
     */
    @Override
    public Boolean discoveryService(String serviceInstance)
    {
        List<String> list = null;
        CuratorFramework curatorClient = null;
        ServiceDiscovery serviceDiscovery = null;

        try
        {
            //获取zk客户端并启动
            curatorClient = client.init();

            if (logger.isDebugEnabled())
            {
                logger.debug("DiscoveryController.discoveryService():CuratorFramework was started !");
            }

            //获取ServiceDiscovery并启动
            serviceDiscovery = CuratorUtil.getServiceDiscovery(curatorClient, client.getRootNode());

            if (logger.isDebugEnabled())
            {
                logger.debug("DiscoveryController.discoveryService():ServiceDiscovery was started !");
            }

            //获取服务列表
            list = CuratorUtil.getServices(curatorClient, nodePath);

            if (logger.isDebugEnabled())
            {
                logger.debug("DiscoveryController.discoveryService():serviceName in node path are :" + list.toString());
            }
        }
        catch (Exception e)
        {
            logger.error("DiscoveryController.discoveryService():get serviceName failed ! e" + e);

            return false;
        }
        finally
        {
            try
            {
                CuratorUtil.closeResource(curatorClient, serviceDiscovery);

                if (logger.isDebugEnabled())
                {
                    logger.debug("DiscoveryController.discoveryService():CuratorClient and ServiceDiscovery were closed");
                }
            }
            catch (IOException e)
            {
                logger.error("DiscoveryController.discoveryService():IO exception when close CuratorClient and ServiceDiscovery, exception : " + e);

                return false;
            }
        }

        return list.contains(serviceInstance);
    }
}

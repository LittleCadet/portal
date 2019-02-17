package com.myproject.entity;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceInstanceBuilder;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.omg.CORBA.ServiceDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * zk实体类
 *
 * @Author LettleCadet
 * @Date 2019/2/13$
 */
public class CuratorClient
{
    private static final Logger logger = LoggerFactory.getLogger(CuratorClient.class);

    private CuratorFramework zkClient;

    private ServiceDiscovery<ServerPayload> serviceDiscovery;

    //zk服务器ip
    @Value("${zookeeper.server}")
    private String zkServer = "47.99.112.38:2181";

    //会话超时时间【至关重要：如果会话时间短了，可能连注册服务都不行】
    @Value(("${zookeeper.sessionTimeOutMs}"))
    private Integer sessionTimeOutMs = 10*1000*60;

    //连接超时时间
    @Value("${zookeeper.connectionTimeOutMs}")
    private Integer connectTimeOutMs = 1000;

    //重连次数【针对会话超时】
    @Value("${zookeeper.reTryTimes}")
    private Integer reTryTimes = 3;

    @Value("${zookeeper.baseSleepTimeMs}")
    private Integer baseSleepTimeMs = 1000;

    private String separator = "/";

    private String rootNode = "myServices";

    public void init()
    {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(baseSleepTimeMs, reTryTimes);
        zkClient = CuratorFrameworkFactory.builder().connectString(zkServer).retryPolicy(retryPolicy)
            .sessionTimeoutMs(sessionTimeOutMs).connectionTimeoutMs(connectTimeOutMs).build();
        zkClient.start();
    }

    public CuratorFramework getClient()
    {
        return zkClient;
    }

    /**
     * 关闭客户端
     */
    public void stop()
    {
        zkClient.close();
    }

    /**
     * 获取serviceDiscovery
     */
    public void getServiceDiscovery()
    {
        serviceDiscovery = ServiceDiscoveryBuilder.builder(ServerPayload.class)
            .client(zkClient)
            .serializer(new JsonInstanceSerializer<ServerPayload>(ServerPayload.class))
            .basePath(rootNode)
            .build();

        try
        {
            serviceDiscovery.start();
        }
        catch (Exception e)
        {
            System.out.println("serviceDiscovery启动失败");
        }
    }

    /**
     * 注册服务
     */
    public void register()
    {
        try
        {
            String rootPath = separator + rootNode;
            //String hostAddress = InetAddress.getLocalHost().getHostAddress();
            String serviceInstance = "prometheus/prometheus" + "_" + "ftp" + "_" + "20190217_2";
            /*zkClient.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(rootPath + separator + serviceInstance);
            System.out.println("节点创建成功，节点名为：" + serviceInstance);*/

            /**
             * 指定服务的 地址，端口，名称
             */
            ServiceInstanceBuilder<ServerPayload> sib = ServiceInstance.builder();
            sib.address("47.99.112.38");
            sib.port(2181);
            sib.name(serviceInstance);

            //byte[] b = {1,2};
            sib.payload(new ServerPayload("ftp", 5));

            ServiceInstance<ServerPayload> instance = sib.build();
            //服务注册
            serviceDiscovery.registerService(instance);
        }
        catch (UnknownHostException e)
        {
            logger.error("主机");
            System.out.println("服务器地址不存在");
        }
        catch (Exception e)
        {
            System.out.println("节点创建失败，即为服务注册失败");
        }
        finally
        {
            try
            {
                serviceDiscovery.close();
                zkClient.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * 改服务
     * @param instance
     * @throws Exception
     */
    public void updateService(ServiceInstance<ServerPayload> instance) throws Exception {
        serviceDiscovery.updateService(instance);
    }

    /**
     * 注册服务
     * @param instance
     * @throws Exception
     */
    public void registerService(ServiceInstance<ServerPayload> instance) throws Exception {
        serviceDiscovery.registerService(instance);
    }

    /**
     * 删除服务
     * @param instance
     * @throws Exception
     */
    public void unregisterService(ServiceInstance<ServerPayload> instance) throws Exception {
        serviceDiscovery.unregisterService(instance);
    }

    /**
     * 查服务
     * @param name
     * @return
     * @throws Exception
     */
    public Collection<ServiceInstance<ServerPayload>> queryForInstances(String name) throws Exception {
        return serviceDiscovery.queryForInstances(name);
    }

    /**
     * 查服务
     * @param name
     * @param id
     * @return
     * @throws Exception
     */
    public ServiceInstance<ServerPayload> queryForInstance(String name, String id) throws Exception {
        return serviceDiscovery.queryForInstance(name, id);
    }

    /**
     * spring bean注入，依靠无参构造
     */
    public CuratorClient()
    {
    }

    public CuratorClient(CuratorFramework zkClient, String zkServer, Integer reTryTimes)
    {
        this.zkClient = zkClient;
        this.zkServer = zkServer;
        this.reTryTimes = reTryTimes;
    }

    public CuratorClient(String zkServer, Integer sessionTimeOutMs, Integer connectTimeOutMs, Integer reTryTimes,
        int baseSleepTimeMs)
    {
        this.zkServer = zkServer;
        this.sessionTimeOutMs = sessionTimeOutMs;
        this.connectTimeOutMs = connectTimeOutMs;
        this.reTryTimes = reTryTimes;
        this.baseSleepTimeMs = baseSleepTimeMs;
    }

    public String getZkServer()
    {
        return zkServer;
    }

    public void setZkServer(String zkServer)
    {
        this.zkServer = zkServer;
    }

    public CuratorFramework getZkClient()
    {
        return zkClient;
    }

    public void setZkClient(CuratorFramework zkClient)
    {
        this.zkClient = zkClient;
    }

    public Integer getSessionTimeOutMs()
    {
        return sessionTimeOutMs;
    }

    public void setSessionTimeOutMs(Integer sessionTimeOutMs)
    {
        this.sessionTimeOutMs = sessionTimeOutMs;
    }

    public Integer getConnectTimeOutMs()
    {
        return connectTimeOutMs;
    }

    public void setConnectTimeOutMs(Integer connectTimeOutMs)
    {
        this.connectTimeOutMs = connectTimeOutMs;
    }

    public Integer getReTryTimes()
    {
        return reTryTimes;
    }

    public void setReTryTimes(Integer reTryTimes)
    {
        this.reTryTimes = reTryTimes;
    }

    public int getBaseSleepTimeMs()
    {
        return baseSleepTimeMs;
    }

    public void setBaseSleepTimeMs(int baseSleepTimeMs)
    {
        this.baseSleepTimeMs = baseSleepTimeMs;
    }
}

package com.myproject.entity;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkImpl;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.apache.zookeeper.CreateMode;
import org.omg.CORBA.ServiceDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * zk实体类
 *
 * @Author LettleCadet
 * @Date 2019/2/13$
 */
public class ZkClient
{
    private static final Logger logger = LoggerFactory.getLogger(ZkClient.class);

    private CuratorFramework zkClient;

    //zk服务器ip
    @Value("${zookeeper.server}")
    private String zkServer = "47.99.112.38:2181";

    //会话超时时间
    @Value(("${zookeeper.sessionTimeOutMs}"))
    private Integer sessionTimeOutMs = 10000;

    //连接超时时间
    @Value("${zookeeper.connectionTimeOutMs}")
    private Integer connectTimeOutMs = 10000;

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
     * 注册服务
     */
    public void register()
    {
        try
        {
            String rootPath = separator + rootNode;
            //String hostAddress = InetAddress.getLocalHost().getHostAddress();
            String serviceInstance = "prometheus" + "_" + "ftp" + "_" + "20190214";
            zkClient.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(rootPath + separator + serviceInstance);
            System.out.println("节点创建成功，节点名为：" + serviceInstance);


            ServiceDiscovery<ServiceDetail> serviceDiscovery = ServiceDiscoveryBuilder.builder(ServiceDetail.class)
                .client(zkClient)
                .serializer(new JsonInstanceSerializer<ServiceDetail>(ServiceDetail.class))
                .basePath(rootNode)
                .build();

            //serviceDiscovery.registerService();
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
    }

    /**
     * 获取子节点
     *
     * @param path 父节点的路径
     * @return
     */
    public List<String> getChildNode(String path)
    {
        List<String> childNodes = new ArrayList<String>();
        try
        {
            childNodes = zkClient.getChildren().forPath(path);
        }
        catch (Exception e)
        {
            System.out.println("获取子节点失败");
        }

        return childNodes;
    }

    /**
     * 获取子节点个数
     *
     * @param path 父节点路径
     * @return
     */
    public Integer getChildNodeCount(String path)
    {
        return getChildNode(path).size();
    }

    public ZkClient()
    {
    }

    public ZkClient(CuratorFramework zkClient, String zkServer, Integer reTryTimes)
    {
        this.zkClient = zkClient;
        this.zkServer = zkServer;
        this.reTryTimes = reTryTimes;
    }

    public ZkClient(String zkServer, Integer sessionTimeOutMs, Integer connectTimeOutMs, Integer reTryTimes,
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

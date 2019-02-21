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
import org.springframework.context.annotation.Bean;

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
 * @Date 2019/2/13
 */
public class CuratorClient
{
    private CuratorFramework curatorClient;

    //zk服务器ip
    @Value("${zookeeper.server}")
    private String zkServer = "47.99.112.38:2181";

    //会话超时时间【至关重要：如果会话时间短了，可能连注册服务都不行】
    @Value(("${zookeeper.sessionTimeOutMs}"))
    private Integer sessionTimeOutMs = 10 * 1000 * 60;

    //连接超时时间
    @Value("${zookeeper.connectionTimeOutMs}")
    private Integer connectTimeOutMs = 1000;

    //重连次数【针对会话超时】
    @Value("${zookeeper.reTryTimes}")
    private Integer reTryTimes = 3;

    @Value("${zookeeper.baseSleepTimeMs}")
    private Integer baseSleepTimeMs = 1000;

    public CuratorFramework init()
    {
        //4种重连策略:
        //1.RetryUntilElapsed(int maxElapsedTimeMs, int sleepMsBetweenRetries) ：指定时间内，每隔一段时间重连一次，超过指定时间，即放弃重连
        //2.RetryNTimes(int n, int sleepMsBetweenRetries)：指定重连次数
        //3.RetryOneTime(int sleepMsBetweenRetry)：重试一次
        //4.ExponentialBackoffRetry
        //ExponentialBackoffRetry(int baseSleepTimeMs, int maxRetries) ：每隔多久，重连几次
        //ExponentialBackoffRetry(int baseSleepTimeMs, int maxRetries, int maxSleepMs)
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(baseSleepTimeMs, reTryTimes);
        curatorClient = CuratorFrameworkFactory.builder()
            .connectString(zkServer)
            .retryPolicy(retryPolicy)
            .sessionTimeoutMs(sessionTimeOutMs)
            .connectionTimeoutMs(connectTimeOutMs)
            .build();
        curatorClient.start();

        return curatorClient;
    }

    public CuratorFramework getClient()
    {
        return curatorClient;
    }

    /**
     * 关闭客户端
     */
    public void stop()
    {
        curatorClient.close();
    }


    /**
     * spring bean注入，依靠无参构造
     */
    public CuratorClient()
    {
    }

    public CuratorClient(CuratorFramework curatorClient, String zkServer, Integer reTryTimes)
    {
        this.curatorClient = curatorClient;
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
        return curatorClient;
    }

    public void setZkClient(CuratorFramework curatorClient)
    {
        this.curatorClient = curatorClient;
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

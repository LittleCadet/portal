package com.myproject.tools;

import com.myproject.entity.ZkClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * zk的工具类：实现zk的服务注册，超时重连等功能
 * @Author LettleCadet
 * @Date 2019/2/13$
 */
public class ZkTools
{
    private String separator = "/";

    private String rootNode;
}

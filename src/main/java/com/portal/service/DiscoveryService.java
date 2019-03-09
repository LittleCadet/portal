package com.portal.service;

/**
 * 从zk发现服务
 *
 * @Author LettleCadet
 * @Date 2019/3/9
 */
public interface DiscoveryService
{
    /**
     * 从zk发现服务
     * @return
     */
    Boolean discoveryService(String serviceInstance);
}

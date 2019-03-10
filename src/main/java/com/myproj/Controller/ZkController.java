package com.myproj.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author LettleCadet
 * @Date 2019/2/24
 */
@Controller
public class ZkController
{
    private static final Logger logger = LoggerFactory.getLogger(ZkController.class);

    @Value("${zookeeper.zkserver}")
    public String zkserver;

    @RequestMapping("/test")
    @ResponseBody
    public String test()
    {
        return "zkserver:" + zkserver;
    }
}

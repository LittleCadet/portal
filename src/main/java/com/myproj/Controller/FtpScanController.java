package com.myproj.Controller;

import com.myproj.entity.Scan;
import com.myproj.service.DiscoveryService;
import com.myproj.service.DownloadService;
import com.myproj.service.ScanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * 单点扫描器
 * LittleCadet
 * 2019/2/27
 **/
@Controller
@RequestMapping("/scan")
public class FtpScanController
{
    private static final Logger logger = LoggerFactory.getLogger(FtpScanController.class);

    @Autowired
    private ScanService scanService;

    @Autowired
    private DiscoveryService discoveryService;

    private String serviceInstance = "scanService";

    private String succeedPage = "succeedPage";

    private String failedPage = "failedPage";

    private String scanPage = "scanPage";

    /**
     * 跳转到上传页面
     * @return
     */
    @RequestMapping("/scanPage")
    public String scanPage(Scan scan)
    {
        return scanPage;
    }

    /**
     * 校验扫描表单，调用扫描接口
     * 用Hibernate validate校验表单填写结果
     * @return
     */
    @PostMapping("/getScanResult")
    public String getScanResult(@Valid Scan scan, BindingResult bindingResult)
    {
        if(logger.isDebugEnabled())
        {
            logger.debug("enter into FtpScanController.getScanResult(),scan:" + scan);
        }


        if(bindingResult.hasErrors())
        {
            return scanPage;
        }

        //构建随机userId
        scan.setUserId(String.valueOf((int)Math.random()*1000));

        if (discoveryService.discoveryService(serviceInstance))
        {
            if(logger.isDebugEnabled())
            {
                logger.debug("exit from FtpScanController.getScanResult(),userId:" + scan.getUserId());
            }

            return scanService.insert(scan) == 0 ? succeedPage : failedPage;
        }
        else
        {
            logger.error("FtpScanController.getScanResult(), zookeeper dont have the serviceInstance:" + serviceInstance);
            return failedPage;
        }
    }
}

package com.myproj.Controller;

import com.myproj.constants.PortalConstants;
import com.myproj.entity.Download;
import com.myproj.service.DeleteService;
import com.myproj.service.DiscoveryService;
import com.myproj.service.DownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

/**
 * 单点下载器
 * LittleCadet
 * 2019/2/26
 **/
@Controller
@RequestMapping("/download")
public class FtpDownloadController
{
    private static final Logger logger = LoggerFactory.getLogger(FtpDownloadController.class);

    @Autowired
    private DownloadService downloadService;

    @Autowired
    private DiscoveryService discoveryService;

    private String serviceInstance = "downloadService";

    /**
     * 跳转到下载页面
     * @return
     */
    @GetMapping("/downloadPage")
    public String downloadPage(Download download)
    {
        return PortalConstants.Page.DOWNLOAD_PAGE;
    }

    /**
     * 校验下载表单，调用下载接口
     * 用Hibernate validate校验表单填写结果
     * @return
     */
    @PostMapping("/getDownloadResult")
    public String getDownloadResult(@Valid Download download,BindingResult bindingResult)
    {
        if(logger.isDebugEnabled())
        {
            logger.debug("enter into FtpDownloadController.getDownloadResult(),download:" + download);
        }


        //如果展示表单验证的错误的结果，则直接跳转到原页面即可
        if(bindingResult.hasErrors())
        {
            return PortalConstants.Page.DOWNLOAD_PAGE;
        }

        //构建随机userId
        download.setUserId(String.valueOf((int)Math.random()*1000));

        if (discoveryService.discoveryService(serviceInstance))
        {
            if(logger.isDebugEnabled())
            {
                logger.debug("exit from FtpDownloadController.getDownloadResult(),userId:" + download.getUserId());
            }

            return downloadService.insert(download) == 0 ? PortalConstants.Page.SUCCESSED_PAGE : PortalConstants.Page.FAILED_PAGE;
        }
        else
        {
            logger.error("FtpDownloadController.getDownloadResult(), zookeeper dont have the serviceInstance:" + serviceInstance);
            return PortalConstants.Page.ZKDOWN_PAGE;
        }
    }
}

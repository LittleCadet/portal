package com.myproj.Controller;

import com.myproj.constants.PortalConstants;
import com.myproj.entity.BatchDownload;
import com.myproj.service.BatchDeleteService;
import com.myproj.service.BatchDownloadService;
import com.myproj.service.DiscoveryService;
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
 * 批量下载器
 * LittleCadet
 * 2019/2/27
 **/
@Controller
@RequestMapping("/batchDownload")
public class FtpBatchDownloadController
{
    private static final Logger logger = LoggerFactory.getLogger(FtpBatchDownloadController.class);

    @Autowired
    private BatchDownloadService batchDownloadService;

    @Autowired
    private DiscoveryService discoveryService;

    private String serviceInstance = "batchDownloadService";

    /**
     * 跳转到下载页面
     * @return
     */
    @GetMapping("/batchDownloadPage")
    public String batchDownloadPage(BatchDownload batchDownload)
    {
        return PortalConstants.Page.BATCH_DOWNLOAD_PAGE;
    }

    /**
     * 校验批量下载表单，调用批量下载接口
     * 用Hibernate validate校验表单填写结果
     * @return
     */
    @PostMapping("/getBatchDownloadResult")
    public String getBatchDownloadResult(@Valid BatchDownload batchDownload, BindingResult bindingResult)
    {
        if(logger.isDebugEnabled())
        {
            logger.debug("enter into FtpBatchDownloadController.getBatchDownloadResult(),batchDownload:" + batchDownload.toString());
        }


        //如果展示表单验证的错误的结果，则直接跳转到原页面即可
        if(bindingResult.hasErrors())
        {
            return PortalConstants.Page.BATCH_DOWNLOAD_PAGE;
        }

        //构建随机userId
        batchDownload.setUserId(String.valueOf((int)(Math.random()*1000)));

        if (discoveryService.discoveryService(serviceInstance))
        {
            if(logger.isDebugEnabled())
            {
                logger.debug("exit from FtpBatchDownloadController.getBatchDownloadResult(),userId:" + batchDownload.getUserId());
            }

            return batchDownloadService.insert(batchDownload) == 0 ? PortalConstants.Page.SUCCESSED_PAGE : PortalConstants.Page.FAILED_PAGE;
        }
        else
        {
            logger.error("FtpBatchDownloadController.getBatchDownloadResult(), zookeeper dont have the serviceInstance:" + serviceInstance);
            return PortalConstants.Page.ZKDOWN_PAGE;
        }
    }
}

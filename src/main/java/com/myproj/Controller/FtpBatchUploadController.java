package com.myproj.Controller;

import com.myproj.entity.BatchUpload;
import com.myproj.service.BatchDownloadService;
import com.myproj.service.BatchUploadService;
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
 * 批量上传器
 * LittleCadet
 * 2019/2/27
 **/
@Controller
@RequestMapping("/batchUpload")
public class FtpBatchUploadController
{
    private static final Logger logger = LoggerFactory.getLogger(FtpBatchUploadController.class);

    @Autowired
    private BatchUploadService batchUploadService;

    @Autowired
    private DiscoveryService discoveryService;

    private String serviceInstance = "batchUploadService";

    private String succeedPage = "succeedPage";

    private String failedPage = "failedPage";

    private String batchUploadPage = "batchUploadPage";

    /**
     * 跳转到下载页面
     * @return
     */
    @GetMapping("/batchUploadPage")
    public String batchUploadPage(BatchUpload batchUpload)
    {
        return batchUploadPage;
    }

    /**
     * 校验批量上传表单，调用批量上传接口
     * 用Hibernate validate校验表单填写结果
     * @return
     */
    @PostMapping("/getBatchUploadResult")
    public String getBatchUploadResult(@Valid BatchUpload batchUpload, BindingResult bindingResult)
    {
        if(logger.isDebugEnabled())
        {
            logger.debug("enter into FtpBatchUploadController.getBatchUploadResult(),batchUpload:" + batchUpload.toString());
        }


        //如果展示表单验证的错误的结果，则直接跳转到原页面即可
        if(bindingResult.hasErrors())
        {
            return batchUploadPage;
        }

        //构建随机userId
        batchUpload.setUserId(String.valueOf((int)Math.random()*1000));

        if (discoveryService.discoveryService(serviceInstance))
        {
            if(logger.isDebugEnabled())
            {
                logger.debug("exit from FtpBatchUploadController.getBatchUploadResult(),userId:" + batchUpload.getUserId());
            }

            return batchUploadService.insert(batchUpload) == 0 ? succeedPage : failedPage;
        }
        else
        {
            logger.error("FtpBatchUploadController.getBatchUploadResult(), zookeeper dont have the serviceInstance:" + serviceInstance);
            return failedPage;
        }
    }
}

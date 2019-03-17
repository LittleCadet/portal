package com.myproj.Controller;

import com.myproj.constants.PortalConstants;
import com.myproj.entity.Upload;
//import com.myproj.service.UploadServcie;
import com.myproj.service.DiscoveryService;
import com.myproj.service.UploadServcie;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * 单点上传器
 * LittleCadet
 * 2019/2/27
 **/
@Controller
@RequestMapping("/upload")
public class FtpUploadController
{
    private static final Logger logger = LoggerFactory.getLogger(FtpUploadController.class);

    @Autowired
    private UploadServcie uploadServcie;

    @Autowired
    private DiscoveryService discoveryService;

    private String serviceInstance = "uploadService";


    /**
     * 跳转到上传页面
     * @return
     */
    @GetMapping("/uploadPage")
    public String uploadPage(Upload upload)
    {
        return PortalConstants.Page.UPLOAD_PAGE;
    }

    /**
     * 校验上传表单，调用上传接口
     * 用Hibernate validate校验表单填写结果
     * @return
     */
    @PostMapping("/getUploadResult")
    public String getUploadResult(@Valid Upload upload, BindingResult bindingResult)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("enter into FtpUploadController.getUploadResult(),upload:" + upload.toString());
        }

        //表单校验
        if (bindingResult.hasErrors())
        {
            return PortalConstants.Page.UPLOAD_PAGE;
        }

        //构建随机userId
        upload.setUserId(String.valueOf((int)(Math.random() * 10000)));

        if (discoveryService.discoveryService(serviceInstance))
        {
            if (logger.isDebugEnabled())
            {
                logger.debug("exit from FtpUploadController.getUploadResult(),userId:" + upload.getUserId());
            }

            return uploadServcie.insert(upload) == 0 ? PortalConstants.Page.SUCCESSED_PAGE : PortalConstants.Page.FAILED_PAGE;
        }
        else
        {
            logger.error("FtpUploadController.getUploadResult(), zookeeper dont have the serviceInstance:" + serviceInstance);
            return PortalConstants.Page.ZKDOWN_PAGE;
        }
    }
}

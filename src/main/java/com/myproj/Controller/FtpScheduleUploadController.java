package com.myproj.Controller;

import com.myproj.constants.PortalConstants;
import com.myproj.entity.ScheduleUpload;
import com.myproj.service.DiscoveryService;
import com.myproj.service.ScheduleDownloadService;
import com.myproj.service.ScheduleUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * 单点上传器
 * LittleCadet
 * 2019/2/27
 **/
@Controller
@RequestMapping("/scheduleUpload")
public class FtpScheduleUploadController
{
    private static final Logger logger = LoggerFactory.getLogger(FtpScheduleUploadController.class);

    @Autowired
    private ScheduleUploadService scheduleUploadService;

    @Autowired
    private DiscoveryService discoveryService;

    private String serviceInstance = "scheduleUploadService";

    /**
     * 跳转到上传页面
     * @return
     */
    @RequestMapping("/scheduleUploadPage")
    public String scheduleUploadPage(ScheduleUpload scheduleUpload)
    {
        return PortalConstants.Page.SCHEDULE_UPLOAD_PAGE;
    }

    /**
     * 校验定时上传表单，调用定时上传接口
     * 用Hibernate validate校验表单填写结果
     * @return
     */
    @PostMapping("/getScheduleUploadResult")
    public String getScheduleUploadResult(@Valid ScheduleUpload scheduleUpload, BindingResult bindingResult)
    {
        if(logger.isDebugEnabled())
        {
            logger.debug("enter into FtpScheduleUploadController.getScheduleUploadResult(),scheduleUpload:" + scheduleUpload);
        }


        if(bindingResult.hasErrors())
        {
            return PortalConstants.Page.SCHEDULE_UPLOAD_PAGE;
        }

        //构建随机userId
        scheduleUpload.setUserId(String.valueOf((int)Math.random()*1000));

        if (discoveryService.discoveryService(serviceInstance))
        {
            if(logger.isDebugEnabled())
            {
                logger.debug("exit from FtpScheduleUploadController.getScheduleUploadResult(),userId:" + scheduleUpload.getUserId());
            }

            return scheduleUploadService.insert(scheduleUpload) == 0 ? PortalConstants.Page.SUCCESSED_PAGE : PortalConstants.Page.FAILED_PAGE;
        }
        else
        {
            logger.error("FtpScheduleUploadController.getScheduleUploadResult(), zookeeper dont have the serviceInstance:" + serviceInstance);
            return PortalConstants.Page.ZKDOWN_PAGE;
        }
    }
}

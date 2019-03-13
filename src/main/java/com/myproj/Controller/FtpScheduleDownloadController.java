package com.myproj.Controller;

import com.myproj.constants.PortalConstants;
import com.myproj.entity.ScheduleDownload;
import com.myproj.service.DiscoveryService;
import com.myproj.service.ScanService;
import com.myproj.service.ScheduleDownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * 定时下载器
 * LittleCadet
 * 2019/2/27
 **/
@Controller
@RequestMapping("/scheduleDownload")
public class FtpScheduleDownloadController
{
    private static final Logger logger = LoggerFactory.getLogger(FtpScheduleDownloadController.class);

    @Autowired
    private ScheduleDownloadService scheduleDownloadService;

    @Autowired
    private DiscoveryService discoveryService;

    private String serviceInstance = "scheduleDownloadService";

    /**
     * 跳转到上传页面
     * @return
     */
    @RequestMapping("/scheduleDownloadPage")
    public String scheduleDownloadPage(ScheduleDownload scheduleDownload)
    {
        return PortalConstants.Page.SCHEDULE_DOWNLOAD_PAGE;
    }

    /**
     * 校验定时下载表单，调用定时下载接口
     * 用Hibernate validate校验表单填写结果
     * @return
     */
    @PostMapping("/getScheduleDownloadResult")
    public String getScheduleDownloadResult(@Valid ScheduleDownload scheduleDownload, BindingResult bindingResult)
    {
        if(logger.isDebugEnabled())
        {
            logger.debug("enter into FtpScheduleDownloadController.getScheduleDownloadResult(),scheduleDownload:" + scheduleDownload);
        }


        if(bindingResult.hasErrors())
        {
            return PortalConstants.Page.SCHEDULE_DOWNLOAD_PAGE;
        }

        //构建随机userId
        scheduleDownload.setUserId(String.valueOf((int)Math.random()*1000));

        if (discoveryService.discoveryService(serviceInstance))
        {
            if(logger.isDebugEnabled())
            {
                logger.debug("exit from FtpScheduleDownloadController.getScheduleDownloadResult(),userId:" + scheduleDownload.getUserId());
            }

            return scheduleDownloadService.insert(scheduleDownload) == 0 ? PortalConstants.Page.SUCCESSED_PAGE : PortalConstants.Page.FAILED_PAGE;
        }
        else
        {
            logger.error("FtpScheduleDownloadController.getScheduleDownloadResult(), zookeeper dont have the serviceInstance:" + serviceInstance);
            return PortalConstants.Page.ZKDOWN_PAGE;
        }
    }
}

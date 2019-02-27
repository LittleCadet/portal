package com.portal.Controller;

import com.portal.entity.ScheduleDownload;
import com.portal.entity.ScheduleUpload;
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

    /**
     * 跳转到上传页面
     * @return
     */
    @RequestMapping("/scheduleDownloadPage")
    public String scheduleDownloadPage(ScheduleDownload scheduleDownload)
    {
        return "scheduleDownloadPage";
    }

    /**
     * 校验上传表单
     * 用Hibernate validate校验表单填写结果
     * @return
     */
    @PostMapping("/getScheduleDownloadResult")
    public String getScheduleDownloadResult(@Valid ScheduleDownload scheduleDownload, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "scheduleDownloadPage";
        }
        return "getResult";
    }
}

package com.portal.Controller;

import com.myproj.entity.ScheduleUpload;
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

    /**
     * 跳转到上传页面
     * @return
     */
    @RequestMapping("/scheduleUploadPage")
    public String scheduleUploadPage(ScheduleUpload scheduleUpload)
    {
        return "scheduleUploadPage";
    }

    /**
     * 校验上传表单
     * 用Hibernate validate校验表单填写结果
     * @return
     */
    @PostMapping("/getScheduleUploadResult")
    public String getScheduleUploadResult(@Valid ScheduleUpload scheduleUpload, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "scheduleUploadPage";
        }
        return "getResult";
    }
}

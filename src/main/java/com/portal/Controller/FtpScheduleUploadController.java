package com.portal.Controller;

import com.myproj.entity.ScheduleUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private String getResultPage = "getResultPage";

    private String scheduleUploadPage = "scheduleUploadPage";

    /**
     * 跳转到上传页面
     *
     * @return
     */
    @RequestMapping("/scheduleUploadPage")
    public String scheduleUploadPage(ScheduleUpload scheduleUpload)
    {
        return scheduleUploadPage;
    }

    /**
     * 校验上传表单
     * 用Hibernate validate校验表单填写结果
     *
     * @return
     */
    @PostMapping("/getScheduleUploadResult")
    public String getScheduleUploadResult(@Valid ScheduleUpload scheduleUpload, BindingResult bindingResult)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("enter int FtpScheduleUploadController.getScheduleUploadResult(),scheduleUpload:" + scheduleUpload);
        }

        if (bindingResult.hasErrors())
        {
            return scheduleUploadPage;
        }
        return getResultPage;
    }
}

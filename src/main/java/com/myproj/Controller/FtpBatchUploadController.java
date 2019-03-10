package com.myproj.Controller;

import com.myproj.entity.BatchUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private String procssingPage = "procssingPage";

    private String batchUploadPage = "batchUploadPage";

    /**
     * 跳转到下载页面
     * @return
     */
    @GetMapping("/batchUploadPage")
    public String batchUploadPage(BatchUpload batchUpload)
    {
        return "batchUploadPage";
    }

    /**
     * 校验下载表单
     * 用Hibernate validate校验表单填写结果
     * @return
     */
    @PostMapping("/getBatchUploadResult")
    public String getBatchUploadResult(@Valid BatchUpload batchUpload, BindingResult bindingResult)
    {
        //如果展示表单验证的错误的结果，则直接跳转到原页面即可
        if(bindingResult.hasErrors())
        {
            return "batchUploadPage";
        }
        return procssingPage;
    }
}

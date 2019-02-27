package com.portal.Controller;

import com.portal.entity.BatchDownload;
import com.portal.entity.Download;
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
    /**
     * 跳转到下载页面
     * @return
     */
    @GetMapping("/batchDownloadPage")
    public String batchDownloadPage(BatchDownload batchDownload)
    {
        return "batchDownloadPage";
    }

    /**
     * 校验下载表单
     * 用Hibernate validate校验表单填写结果
     * @return
     */
    @PostMapping("/getBatchDownloadResult")
    public String getBatchDownloadResult(@Valid BatchDownload batchDownload, BindingResult bindingResult)
    {
        //如果展示表单验证的错误的结果，则直接跳转到原页面即可
        if(bindingResult.hasErrors())
        {
            return "batchDownloadPage";
        }
        return "getResult";
    }
}

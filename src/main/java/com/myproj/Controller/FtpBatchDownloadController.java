package com.myproj.Controller;

import com.myproj.entity.BatchDownload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private String procssingPage = "procssingPage";

    private String batchDownloadPage = "batchDownloadPage";

    /**
     * 跳转到下载页面
     *
     * @return
     */
    @GetMapping("/batchDownloadPage")
    public String batchDownloadPage(BatchDownload batchDownload)
    {
        return batchDownloadPage;
    }

    /**
     * 校验下载表单
     * 用Hibernate validate校验表单填写结果
     *
     * @return
     */
    @PostMapping("/getBatchDownloadResult")
    public String getBatchDownloadResult(@Valid BatchDownload batchDownload, BindingResult bindingResult)
    {
        if(logger.isDebugEnabled())
        {
            logger.debug("enter int FtpBatchDownloadController.getBatchDownloadResult(),batchDelete:" + batchDownload);
        }

        //如果展示表单验证的错误的结果，则直接跳转到原页面即可
        if (bindingResult.hasErrors())
        {
            return batchDownloadPage;
        }
        return procssingPage;
    }
}

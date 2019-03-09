package com.portal.Controller;

import com.myproj.entity.Download;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

/**
 * 单点下载器
 * LittleCadet
 * 2019/2/26
 **/
@Controller
@RequestMapping("/download")
public class FtpDownloadController
{
    private static final Logger logger = LoggerFactory.getLogger(FtpDownloadController.class);

    private String downloadPage = "downloadPage";

    private String getResultPage = "getResultPage";

    /**
     * 跳转到下载页面
     * @return
     */
    @GetMapping("/downloadPage")
    public String downloadPage(Download download)
    {
        return downloadPage;
    }

    /**
     * 校验下载表单
     * 用Hibernate validate校验表单填写结果
     * @return
     */
    @PostMapping("/getDownloadResult")
    public String getDownloadResult(@Valid Download download,BindingResult bindingResult)
    {
        if(logger.isDebugEnabled())
        {
            logger.debug("enter int FtpDownloadController.getDownloadResult(),download:" + download);
        }

        //如果展示表单验证的错误的结果，则直接跳转到原页面即可
        if(bindingResult.hasErrors())
        {
            return downloadPage;
        }
        return getResultPage;
    }

    @GetMapping("/ftpWebDownload")
    public void ftpWebDownload()
    {

    }
}

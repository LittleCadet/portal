package com.myproj.Controller;

import com.myproj.entity.Download;
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
    private String defaultPage = "downloadPage";

    private String upload = "FTP单点上传";

    private String uploadPage = "uploadPage";

    private String batchDownload = "FTP批量下载";

    private String batchDownloadPage = "batchDownloadPage";

    private String batchUpload = "FTP批量上传";

    private String batchUploadPage = "batchUploadPage";

    private String scan = "FTP单点扫描";

    private String scanPage = "scan";

    private String scheduleDownload = "FTP定时单点下载";

    private String scheduleDownloadPage = "scheduleDownloadPage";

    private String scheduleUpload = "FTP定时单点上传";

    private String scheduleUploadPage = "scheduleUploadPage";

    private String procssingPage = "procssingPage";

    /**
     * 跳转到下载页面
     * @return
     */
    @GetMapping("/downloadPage")
    public String downloadPage(Download download)
    {
        return "downloadPage";
    }

    /**
     * 校验下载表单
     * 用Hibernate validate校验表单填写结果
     * @return
     */
    @PostMapping("/getDownloadResult")
    public String getDownloadResult(@Valid Download download,BindingResult bindingResult)
    {
        //如果展示表单验证的错误的结果，则直接跳转到原页面即可
        if(bindingResult.hasErrors())
        {
            return "downloadPage";
        }
        return procssingPage;
    }

    @GetMapping("/ftpWebDownload")
    public void ftpWebDownload()
    {

    }
}

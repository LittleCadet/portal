package com.portal.Controller;

import com.portal.entity.Download;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * LittleCadet
 * 2019/2/26
 **/
@Controller
public class FtpController
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

    /**
     * 获取服务
     *
     * @return
     */
    @GetMapping("/getService")
    public String getService()
    {
        return "getService";
    }

    /**
     * 获取表单
     * FtpEntity:这个属性必须有，否则，出现thymeleaf解析异常
     * @return
     */
    @PostMapping("/getForm")
    public String getForm(@RequestParam("service")String service, Download ftpEntity)
    {
        if(upload.equals(service))
        {
            return uploadPage;
        }
        return defaultPage;
    }

    /**
     * 用Hibernate validate校验表单填写结果
     * @return
     */
    @PostMapping("/getResult")
    public String getResult(@Valid Download ftpEntity,BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "getService";
        }
        return "getResult";
    }
}

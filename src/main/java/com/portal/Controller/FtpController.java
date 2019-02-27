package com.portal.Controller;

import com.portal.entity.Download;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 总调度器
 * LittleCadet
 * 2019/2/27
 **/
@Controller
public class FtpController
{
    private String upload = "FTP单点上传";

    private String delete = "FTP单点删除";

    private String batchUpload = "FTP批量上传";

    private String batchDownload = "FTP批量下载";

    private String batchDelete = "FTP批量删除";

    private String scheduleDownload = "FTP定时单点下载";

    private String scheduleUpload = "FTP定时单点上传";

    private String scan = "FTP单点扫描";

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
    public String getForm(@RequestParam("service")String service, Download download)
    {
        if (upload.equals(service))
        {
            return "redirect:/upload/uploadPage";
        }
        else if (delete.equals(service))
        {
            return "redirect:/delete/deletePage";
        }
        else if (batchUpload.equals(service))
        {
            return "redirect:/batchUpload/batchUploadPage";
        }
        else if(batchDownload.equals(service))
        {
            return "redirect:/batchDownload/batchDownloadPage";
        }
        else if(batchDelete.equals(service))
        {
            return "redirect:/batchDelete/batchDeletePage";
        }
        else if(scheduleUpload.equals(service))
        {
            return "redirect:/scheduleUpload/scheduleUploadPage";
        }
        else if(scheduleDownload.equals(service))
        {
            return "redirect:/scheduleDownload/scheduleDownloadPage";
        }
        else if(scan.equals(service))
        {
            return "redirect:/scan/scanPage";
        }
        return "redirect:/download/downloadPage";
    }
}

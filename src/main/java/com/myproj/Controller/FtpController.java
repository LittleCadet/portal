package com.myproj.Controller;

import com.myproj.entity.Download;
import com.myproj.entity.FtpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 总调度器
 * LittleCadet
 * 2019/2/27
 **/
@Controller
@RequestMapping("/portal")
public class FtpController
{
    private static final Logger logger = LoggerFactory.getLogger(FtpController.class);

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
    @RequestMapping("/getService")
    public String getService()
    {
        System.out.println("1111");

        //目前使用临时服务选择页面（少了6项服务）
        return "getTemporaryServicePage";
    }

    /**
     * 获取表单
     * Download:这个属性必须有，否则，出现thymeleaf解析异常
     * @return
     */
    @PostMapping("/getForm")
    public String getForm(@RequestParam("service")String service, FtpService ftpService)
    {
        if(logger.isDebugEnabled())
        {
            logger.debug("enter into FtpController.getForm(),service:" + service);
        }

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

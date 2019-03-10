package com.myproj.Controller;

import com.myproj.entity.Upload;
//import com.myproj.service.UploadServcie;
import com.myproj.service.DiscoveryService;
import com.myproj.service.UploadServcie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

/**
 * 单点上传器
 * LittleCadet
 * 2019/2/27
 **/
@Controller
@RequestMapping("/upload")
public class FtpUploadController
{
    private static final Logger logger = LoggerFactory.getLogger(FtpUploadController.class);

    @Autowired
    private UploadServcie uploadServcie;

    /*@Autowired
    private DiscoveryService discoveryService;*/

    private String serviceInstance = "uploadServcie";

    private String procssingPage = "procssingPage";

    private String uploadPage = "uploadPage";

    private String succeedPage = "succeedPage";

    private String failedPage = "failedPage";

    /**
     * 跳转到上传页面
     *
     * @return
     */
    @RequestMapping("/uploadPage")
    public String uploadPage(Upload upload)
    {
        return uploadPage;
    }

    /**
     * 校验上传表单
     * 用Hibernate validate校验表单填写结果
     *
     * @return
     */
    @PostMapping("/getUploadResult")
    public String getUploadResult(@Valid Upload upload, BindingResult bindingResult)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("enter int FtpUploadController.getUploadResult(),upload:" + upload);
        }

        if (bindingResult.hasErrors())
        {
            return uploadPage;
        }
        upload.setUserId(upload.getAccount());
        return uploadServcie.insert(upload) == 1?succeedPage:failedPage;
        //return "redirect:/upload/uploadService";
    }

    /**
     * 调用ftpweb的uploadService服务
     * @param upload
     * @return
     */
    @GetMapping("/uploadService")
    public String uploadService(Upload upload)
    {

        upload.setUserId("123456");
        return uploadServcie.insert(upload) == 1?succeedPage:failedPage;

        /*if(discoveryService.discoveryService(serviceInstance))
        {
            return uploadServcie.insert(upload) == 1?succeedPage:failedPage;
        }*/
        //return succeedPage;
    }
}

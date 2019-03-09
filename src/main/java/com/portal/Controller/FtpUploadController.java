package com.portal.Controller;

import com.myproj.entity.Upload;
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
@RequestMapping("/upload")
public class FtpUploadController
{
    private static final Logger logger = LoggerFactory.getLogger(FtpUploadController.class);

    private String getResultPage = "getResultPage";

    private String uploadPage = "uploadPage";

    private UploadServcie uploadServcie;

    //public PasswordEncryptConfigurer scanMapper;

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
        return getResultPage;
    }
}

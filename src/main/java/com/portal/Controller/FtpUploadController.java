package com.portal.Controller;

import com.portal.entity.Upload;
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

    /**
     * 跳转到上传页面
     * @return
     */
    @RequestMapping("/uploadPage")
    public String uploadPage(Upload upload)
    {
        return "uploadPage";
    }

    /**
     * 校验上传表单
     * 用Hibernate validate校验表单填写结果
     * @return
     */
    @PostMapping("/getUploadResult")
    public String getUploadResult(@Valid Upload upload, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "uploadPage";
        }
        return "getResult";
    }
}

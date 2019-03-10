package com.myproj.Controller;

import com.myproj.entity.Scan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * 单点扫描器
 * LittleCadet
 * 2019/2/27
 **/
@Controller
@RequestMapping("/scan")
public class FtpScanController
{
    private static final Logger logger = LoggerFactory.getLogger(FtpScanController.class);

    private String procssingPage = "procssingPage";

    private String scanPage = "scanPage";

    /**
     * 跳转到上传页面
     * @return
     */
    @RequestMapping("/scanPage")
    public String scanPage(Scan scan)
    {
        return "scanPage";
    }

    /**
     * 校验上传表单
     * 用Hibernate validate校验表单填写结果
     * @return
     */
    @PostMapping("/getScanResult")
    public String getScanResult(@Valid Scan scan, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "scanPage";
        }
        return procssingPage;
    }
}

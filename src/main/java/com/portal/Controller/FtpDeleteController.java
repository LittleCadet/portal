package com.portal.Controller;

import com.myproj.entity.Delete;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * 单点删除器
 * @Author LettleCadet
 * @Date 2019/2/27
 */
@Controller
@RequestMapping("/delete")
public class FtpDeleteController
{
    /**
     * 跳转到下载页面
     * @return
     */
    @GetMapping("/deletePage")
    public String deletePage(Delete delete)
    {
        return "deletePage";
    }

    /**
     * 校验下载表单
     * 用Hibernate validate校验表单填写结果
     * @return
     */
    @PostMapping("/getDeleteResult")
    public String getDeleteResult(@Valid Delete delete, BindingResult bindingResult)
    {
        //如果展示表单验证的错误的结果，则直接跳转到原页面即可
        if(bindingResult.hasErrors())
        {
            return "deletePage";
        }
        return "getResult";
    }
}

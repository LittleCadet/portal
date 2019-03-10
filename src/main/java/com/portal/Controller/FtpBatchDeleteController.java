package com.portal.Controller;

import com.myproj.entity.BatchDelete;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

/**
 * 批量删除器
 * @Author LettleCadet
 * @Date 2019/2/27
 */
@Controller
@RequestMapping("/batchDelete")
public class FtpBatchDeleteController
{
    /**
     * 跳转到下载页面
     * @return
     */
    @GetMapping("/batchDeletePage")
    public String batchDeletePage(BatchDelete batchDelete)
    {
        return "batchDeletePage";
    }

    /**
     * 校验下载表单
     * 用Hibernate validate校验表单填写结果
     * @return
     */
    @PostMapping("/getBatchDeleteResult")
    public String getBatchDeleteResult(@Valid BatchDelete batchDelete, BindingResult bindingResult)
    {
        //如果展示表单验证的错误的结果，则直接跳转到原页面即可
        if(bindingResult.hasErrors())
        {
            return "batchDeletePage";
        }
        return "getResult";
    }
}

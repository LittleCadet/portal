package com.portal.Controller;

import com.portal.entity.FtpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * LittleCadet
 * 2019/2/26
 **/
@Controller
public class FtpController
{
    /**
     * 获取服务
     *
     * @return
     */
    @GetMapping("/getService")
    public String getService(FtpEntity ftpEntity)
    {
        return "getService";
    }

    /**
     * 获取表单
     *
     * @return
     */
    @GetMapping("/getForm")
    public String getForm(FtpEntity ftpEntity)
    {
        return "getForm";
    }

    /**
     * 用Hibernate validate校验表单填写结果
     * @return
     */
    @PostMapping("/getForm")
    public String getResult(FtpEntity ftpEntity,BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "getForm";
        }
        return "redirect:/getResult";
    }


}

package com.myproj.Controller;

import com.myproj.constants.PortalConstants;
import com.myproj.entity.Delete;
import com.myproj.service.BatchUploadService;
import com.myproj.service.DeleteService;
import com.myproj.service.DiscoveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final Logger logger = LoggerFactory.getLogger(FtpDeleteController.class);

    @Autowired
    private DeleteService deleteService;

    @Autowired
    private DiscoveryService discoveryService;

    private String serviceInstance = "batchUploadService";

    private String deletePage = "deletePage";

    /**
     * 跳转到下载页面
     * @return
     */
    @GetMapping("/deletePage")
    public String deletePage(Delete delete)
    {
        return PortalConstants.Page.DETELE_PAGE;
    }

    /**
     * 校验删除表单，调用删除接口
     * 用Hibernate validate校验表单填写结果
     * @return
     */
    @PostMapping("/getDeleteResult")
    public String getDeleteResult(@Valid Delete delete, BindingResult bindingResult)
    {
        if(logger.isDebugEnabled())
        {
            logger.debug("enter into FtpDeleteController.getDeleteResult(),delete:" + delete);
        }


        //如果展示表单验证的错误的结果，则直接跳转到原页面即可
        if(bindingResult.hasErrors())
        {
            return PortalConstants.Page.DETELE_PAGE;
        }

        //构建随机userId
        delete.setUserId(String.valueOf((int)Math.random()*1000));

        if (discoveryService.discoveryService(serviceInstance))
        {
            if(logger.isDebugEnabled())
            {
                logger.debug("exit from FtpDeleteController.getDeleteResult(),userId:" + delete.getUserId());
            }

            return deleteService.insert(delete) == 0 ? PortalConstants.Page.SUCCESSED_PAGE : PortalConstants.Page.FAILED_PAGE;
        }
        else
        {
            logger.error("FtpDeleteController.getDeleteResult(), zookeeper dont have the serviceInstance:" + serviceInstance);
            return PortalConstants.Page.ZKDOWN_PAGE;
        }
    }
}

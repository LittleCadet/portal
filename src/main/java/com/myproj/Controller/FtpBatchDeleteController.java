package com.myproj.Controller;

import com.myproj.constants.PortalConstants;
import com.myproj.entity.BatchDelete;
import com.myproj.service.BatchDeleteService;
import com.myproj.service.DiscoveryService;
import com.myproj.service.UploadServcie;
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
 * 批量删除器
 * @Author LettleCadet
 * @Date 2019/2/27
 */
@Controller
@RequestMapping("/batchDelete")
public class FtpBatchDeleteController
{
    private static final Logger logger = LoggerFactory.getLogger(FtpBatchDeleteController.class);

    @Autowired
    private BatchDeleteService batchDeleteService;

    @Autowired
    private DiscoveryService discoveryService;

    private String serviceInstance = "batchDeleteService";

    /**
     * 跳转到下载页面
     * @return
     */
    @GetMapping("/batchDeletePage")
    public String batchDeletePage(BatchDelete batchDelete)
    {
        return PortalConstants.Page.BATCH_DALETE_PAGE;
    }

    /**
     * 校验批量删除表单，调用批量删除接口
     * 用Hibernate validate校验表单填写结果
     * @return
     */
    @PostMapping("/getBatchDeleteResult")
    public String getBatchDeleteResult(@Valid BatchDelete batchDelete, BindingResult bindingResult)
    {
        if(logger.isDebugEnabled())
        {
            logger.debug("enter into FtpBatchDeleteController.getBatchDeleteResult(),batchDelete:" + batchDelete.toString());
        }


        //如果展示表单验证的错误的结果，则直接跳转到原页面即可
        if(bindingResult.hasErrors())
        {
            return PortalConstants.Page.BATCH_DALETE_PAGE;
        }

        //构建随机userId
        batchDelete.setUserId(String.valueOf((int)(Math.random()*1000)));

        if (discoveryService.discoveryService(serviceInstance))
        {
            if(logger.isDebugEnabled())
            {
                logger.debug("exit from FtpBatchDeleteController.getBatchDeleteResult(),userId:" + batchDelete.getUserId());
            }

            return batchDeleteService.insert(batchDelete) == 0 ? PortalConstants.Page.SUCCESSED_PAGE : PortalConstants.Page.FAILED_PAGE;
        }
        else
        {
            logger.error("FtpBatchDeleteController.getBatchDeleteResult(), zookeeper dont have the serviceInstance:" + serviceInstance);
            return PortalConstants.Page.ZKDOWN_PAGE;
        }
    }
}

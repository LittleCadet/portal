package com.portal.entity;

import javax.validation.constraints.NotNull;

/**
 * @Author LettleCadet
 * @Date 2019/2/26
 */
public class BatchDelete extends FtpService
{
    //ftp批量删除路径
    @NotNull
    private String batchDeleteFilePath;

    public BatchDelete()
    {
    }

    public BatchDelete(@NotNull String batchDeleteFilePath)
    {
        this.batchDeleteFilePath = batchDeleteFilePath;
    }

    public String getBatchDeleteFilePath()
    {
        return batchDeleteFilePath;
    }

    public void setBatchDeleteFilePath(String batchDeleteFilePath)
    {
        this.batchDeleteFilePath = batchDeleteFilePath;
    }

    @Override
    public String toString()
    {
        return "BatchDelete{" +
            "batchDeleteFilePath='" + batchDeleteFilePath + '\'' +
            '}';
    }
}

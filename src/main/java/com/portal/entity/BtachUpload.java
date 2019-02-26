package com.portal.entity;

import javax.validation.constraints.NotNull;

/**
 * @Author LettleCadet
 * @Date 2019/2/26
 */
public class BtachUpload extends FtpService
{
    //ftp本地上传路径
    @NotNull
    private String localUploadFilePath;

    //ftp批量上传路径
    @NotNull
    private String batchUploadFilePath;

    public BtachUpload()
    {
    }

    public BtachUpload(@NotNull String localUploadFilePath,
        @NotNull String batchUploadFilePath)
    {
        this.localUploadFilePath = localUploadFilePath;
        this.batchUploadFilePath = batchUploadFilePath;
    }

    public String getLocalUploadFilePath()
    {
        return localUploadFilePath;
    }

    public void setLocalUploadFilePath(String localUploadFilePath)
    {
        this.localUploadFilePath = localUploadFilePath;
    }

    public String getBatchUploadFilePath()
    {
        return batchUploadFilePath;
    }

    public void setBatchUploadFilePath(String batchUploadFilePath)
    {
        this.batchUploadFilePath = batchUploadFilePath;
    }

    @Override
    public String toString()
    {
        return "BtachUpload{" +
            "localUploadFilePath='" + localUploadFilePath + '\'' +
            ", batchUploadFilePath='" + batchUploadFilePath + '\'' +
            '}';
    }
}

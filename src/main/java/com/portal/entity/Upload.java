package com.portal.entity;

import javax.validation.constraints.NotNull;

/**
 * @Author LettleCadet
 * @Date 2019/2/26
 */
public class Upload extends FtpService
{
    //ftp远程上传路径
    @NotNull
    private String remoteUploadFilePath;

    //ftp本地上传路径
    @NotNull
    private String localUploadFilePath;

    public Upload()
    {
    }

    public Upload(@NotNull String remoteUploadFilePath,
        @NotNull String localUploadFilePath)
    {
        this.remoteUploadFilePath = remoteUploadFilePath;
        this.localUploadFilePath = localUploadFilePath;
    }

    public String getRemoteUploadFilePath()
    {
        return remoteUploadFilePath;
    }

    public void setRemoteUploadFilePath(String remoteUploadFilePath)
    {
        this.remoteUploadFilePath = remoteUploadFilePath;
    }

    public String getLocalUploadFilePath()
    {
        return localUploadFilePath;
    }

    public void setLocalUploadFilePath(String localUploadFilePath)
    {
        this.localUploadFilePath = localUploadFilePath;
    }

    @Override
    public String toString()
    {
        return "Upload{" +
            "remoteUploadFilePath='" + remoteUploadFilePath + '\'' +
            ", localUploadFilePath='" + localUploadFilePath + '\'' +
            '}';
    }
}

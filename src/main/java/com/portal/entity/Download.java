package com.portal.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * LittleCadet
 * 2019/2/26
 **/
@EntityScan
public class Download extends FtpService
{
    //ftp远程下载路径
    @NotBlank
    private String remoteDownloadFilePath;

    //ftp本地下载路径
    @NotBlank
    private String localDownloadFilePath;

    public Download()
    {
    }

    public Download(@NotBlank String remoteDownloadFilePath,
        @NotBlank String localDownloadFilePath)
    {
        this.remoteDownloadFilePath = remoteDownloadFilePath;
        this.localDownloadFilePath = localDownloadFilePath;
    }

    public String getRemoteDownloadFilePath()
    {
        return remoteDownloadFilePath;
    }

    public void setRemoteDownloadFilePath(String remoteDownloadFilePath)
    {
        this.remoteDownloadFilePath = remoteDownloadFilePath;
    }

    public String getLocalDownloadFilePath()
    {
        return localDownloadFilePath;
    }

    public void setLocalDownloadFilePath(String localDownloadFilePath)
    {
        this.localDownloadFilePath = localDownloadFilePath;
    }

    @Override
    public String toString()
    {
        return "Download{" +
            "remoteDownloadFilePath='" + remoteDownloadFilePath + '\'' +
            ", localDownloadFilePath='" + localDownloadFilePath + '\'' +
            '}';
    }
}

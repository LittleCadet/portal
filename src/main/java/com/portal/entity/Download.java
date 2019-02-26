package com.portal.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 沈燮
 * 2019/2/26
 **/
@EntityScan
public class Download extends FtpService
{
    //ftp远程下载路径
    @NotNull
    private String remoteDownloadFilePath;

    //ftp本地下载路径
    @NotNull
    private String localDownloadFilePath;

    public Download()
    {
    }

    public Download(@NotNull String remoteDownloadFilePath,
        @NotNull String localDownloadFilePath)
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

package com.portal.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author LettleCadet
 * @Date 2019/2/26
 */
public class ScheduleDownload extends FtpService
{
    //ftp本地下载路径
    @NotBlank
    private String localDownloadFilePath;

    //ftp远程定时下载路径
    @NotBlank
    private String remoteScheduleDownloadFilePath;

    public ScheduleDownload()
    {
    }

    public ScheduleDownload(@NotBlank String localDownloadFilePath,
        @NotBlank String remoteScheduleDownloadFilePath)
    {
        this.localDownloadFilePath = localDownloadFilePath;
        this.remoteScheduleDownloadFilePath = remoteScheduleDownloadFilePath;
    }

    public String getLocalDownloadFilePath()
    {
        return localDownloadFilePath;
    }

    public void setLocalDownloadFilePath(String localDownloadFilePath)
    {
        this.localDownloadFilePath = localDownloadFilePath;
    }

    public String getRemoteScheduleDownloadPath()
    {
        return remoteScheduleDownloadFilePath;
    }

    public void setRemoteScheduleDownloadPath(String remoteScheduleDownloadFilePath)
    {
        this.remoteScheduleDownloadFilePath = remoteScheduleDownloadFilePath;
    }

    @Override
    public String toString()
    {
        return "ScheduleDownload{" +
            "localDownloadFilePath='" + localDownloadFilePath + '\'' +
            ", remoteScheduleDownloadFilePath='" + remoteScheduleDownloadFilePath + '\'' +
            '}';
    }
}

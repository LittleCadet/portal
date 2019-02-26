package com.portal.entity;

import javax.validation.constraints.NotNull;

/**
 * @Author LettleCadet
 * @Date 2019/2/26
 */
public class Scan extends FtpService
{
    //ftp本地下载路径
    @NotNull
    private String localDownloadFilePath;

    //ftp远程扫描路径
    @NotNull
    private String remoteScanFilePath;

    public Scan()
    {
    }

    public Scan(@NotNull String localDownloadFilePath,
        @NotNull String remoteScanFilePath)
    {
        this.localDownloadFilePath = localDownloadFilePath;
        this.remoteScanFilePath = remoteScanFilePath;
    }

    public String getLocalDownloadFilePath()
    {
        return localDownloadFilePath;
    }

    public void setLocalDownloadFilePath(String localDownloadFilePath)
    {
        this.localDownloadFilePath = localDownloadFilePath;
    }

    public String getRemoteScanFilePath()
    {
        return remoteScanFilePath;
    }

    public void setRemoteScanFilePath(String remoteScanFilePath)
    {
        this.remoteScanFilePath = remoteScanFilePath;
    }

    @Override
    public String toString()
    {
        return "Scan{" +
            "localDownloadFilePath='" + localDownloadFilePath + '\'' +
            ", remoteScanFilePath='" + remoteScanFilePath + '\'' +
            '}';
    }
}

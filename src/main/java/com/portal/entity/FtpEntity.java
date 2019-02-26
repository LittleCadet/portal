package com.portal.entity;

import javax.validation.constraints.NotNull;

/**
 * 沈燮
 * 2019/2/26
 **/
public class FtpEntity
{
    //服务器ip
    @NotNull
    private String host;

    //用户名
    @NotNull
    private String account;

    //密码
    @NotNull
    private String password;

    //重试次数
    private String reTryTimes;

    //ftp会话超时时间
    private String timeOut;

    //ftp远程上传路径
    @NotNull
    private String remoteUploadFilePath;

    //ftp本地上传路径
    @NotNull
    private String localUploadFilePath;

    //ftp远程下载路径
    @NotNull
    private String remoteDownloadFilePath;

    //ftp本地下载路径
    @NotNull
    private String localDownloadFilePath;

    //ftp远程删除路径
    @NotNull
    private String remoteDeleteFilePath;

    //ftp批量上传路径
    @NotNull
    private String batchUploadFilePath;

    //ftp批量下载路径
    @NotNull
    private String batchDownloadFilePath;

    //ftp批量删除路径
    @NotNull
    private String batchDeleteFilePath;

    public FtpEntity()
    {
    }

    public FtpEntity(String host, String account, String password)
    {
        this.host = host;
        this.account = account;
        this.password = password;
    }

    public FtpEntity(String host, String account, String password, String reTryTimes, String timeOut)
    {
        this.host = host;
        this.account = account;
        this.password = password;
        this.reTryTimes = reTryTimes;
        this.timeOut = timeOut;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getReTryTimes()
    {
        return reTryTimes;
    }

    public void setReTryTimes(String reTryTimes)
    {
        this.reTryTimes = reTryTimes;
    }

    public String getTimeOut()
    {
        return timeOut;
    }

    public void setTimeOut(String timeOut)
    {
        this.timeOut = timeOut;
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

    public String getRemoteDeleteFilePath()
    {
        return remoteDeleteFilePath;
    }

    public void setRemoteDeleteFilePath(String remoteDeleteFilePath)
    {
        this.remoteDeleteFilePath = remoteDeleteFilePath;
    }

    public String getBatchUploadFilePath()
    {
        return batchUploadFilePath;
    }

    public void setBatchUploadFilePath(String batchUploadFilePath)
    {
        this.batchUploadFilePath = batchUploadFilePath;
    }

    public String getBatchDownloadFilePath()
    {
        return batchDownloadFilePath;
    }

    public void setBatchDownloadFilePath(String batchDownloadFilePath)
    {
        this.batchDownloadFilePath = batchDownloadFilePath;
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
        return "FtpEntity{" +
            "host='" + host + '\'' +
            ", account='" + account + '\'' +
            ", password='" + password + '\'' +
            ", reTryTimes='" + reTryTimes + '\'' +
            ", timeOut='" + timeOut + '\'' +
            ", remoteUploadFilePath='" + remoteUploadFilePath + '\'' +
            ", localUploadFilePath='" + localUploadFilePath + '\'' +
            ", remoteDownloadFilePath='" + remoteDownloadFilePath + '\'' +
            ", localDownloadFilePath='" + localDownloadFilePath + '\'' +
            ", remoteDeleteFilePath='" + remoteDeleteFilePath + '\'' +
            ", batchUploadFilePath='" + batchUploadFilePath + '\'' +
            ", batchDownloadFilePath='" + batchDownloadFilePath + '\'' +
            ", batchDeleteFilePath='" + batchDeleteFilePath + '\'' +
            '}';
    }
}

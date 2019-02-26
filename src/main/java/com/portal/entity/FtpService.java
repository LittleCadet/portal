package com.portal.entity;

import javax.validation.constraints.NotNull;

/**
 * @Author LettleCadet
 * @Date 2019/2/26
 */
public class FtpService
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

    public FtpService()
    {
    }

    public FtpService(@NotNull String host, @NotNull String account,
        @NotNull String password)
    {
        this.host = host;
        this.account = account;
        this.password = password;
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

    @Override
    public String toString()
    {
        return "FtpService{" +
            "host='" + host + '\'' +
            ", account='" + account + '\'' +
            ", password='" + password + '\'' +
            ", reTryTimes='" + reTryTimes + '\'' +
            ", timeOut='" + timeOut + '\'' +
            '}';
    }
}

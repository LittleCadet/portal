package com.portal.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author LettleCadet
 * @Date 2019/2/26
 */
public class Delete extends FtpService
{
    //ftp远程删除路径
    @NotBlank
    private String remoteDeleteFilePath;

    public Delete()
    {
    }

    public Delete(@NotBlank String remoteDeleteFilePath)
    {
        this.remoteDeleteFilePath = remoteDeleteFilePath;
    }

    public String getRemoteDeleteFilePath()
    {
        return remoteDeleteFilePath;
    }

    public void setRemoteDeleteFilePath(String remoteDeleteFilePath)
    {
        this.remoteDeleteFilePath = remoteDeleteFilePath;
    }

    @Override
    public String toString()
    {
        return "Delete{" +
            "remoteDeleteFilePath='" + remoteDeleteFilePath + '\'' +
            '}';
    }
}
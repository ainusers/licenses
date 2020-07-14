package com.data.consumer.entity;

import java.io.Serializable;
import java.util.List;

/*
 * @Author: tianyong
 * @Date: 2020/7/13 17:27
 * @Description: license额外请求校验参数
 */
public class LicenseParam implements Serializable {

    // 是否认证IP
    private boolean isIpCheck;
    // 允许的IP
    private List<String> ips;
    // 是否认证MAC
    private boolean isMacCheck;
    // 允许的MAC
    private List<String> macs;
    // 是否认证CPU
    private boolean isCpuCheck;
    // 允许的CPU
    private String cpus;
    // 是否认证主板
    private boolean isBoardCheck;
    // 允许的主板
    private String Boards;


    public List<String> getIps() {
        return ips;
    }

    public void setIps(List<String> ips) {
        this.ips = ips;
    }

    public List<String> getMacs() {
        return macs;
    }

    public void setMacs(List<String> macs) {
        this.macs = macs;
    }

    public String getCpus() {
        return cpus;
    }

    public void setCpus(String cpus) {
        this.cpus = cpus;
    }

    public String getBoards() {
        return Boards;
    }

    public void setBoards(String boards) {
        Boards = boards;
    }

    public boolean isIpCheck() {
        return isIpCheck;
    }

    public boolean isMacCheck() {
        return isMacCheck;
    }

    public boolean isCpuCheck() {
        return isCpuCheck;
    }

    public boolean isBoardCheck() {
        return isBoardCheck;
    }

}

package com.data.consumer.controller;

import com.data.consumer.entity.LicenseParam;
import com.data.consumer.service.LinuxInfoAbstract;
import com.data.consumer.service.ServerInfoAbstract;
import com.data.consumer.service.WindowInfoAbstract;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*
 * @Author: tianyong
 * @Date: 2020/7/13 16:47
 * @Description: 生成license接口
 */
@RestController
@RequestMapping("/service/v1/license")
public class LicenseController {

    /*
     * @Author: tianyong
     * @Date: 2020/7/13 16:49
     * @Description: 获取当前服务器硬件信息
     */
    @ResponseBody
    @RequestMapping("/server/info")
    public LicenseParam serverInfo(){
        // 获取操作系统类型
        String osName = System.getProperty("os.name").toLowerCase();
        // 根据当前操作系统获取相关系统参数
        ServerInfoAbstract serverInfoAbstract;
        serverInfoAbstract = osName.startsWith("windows") ? new WindowInfoAbstract() : new LinuxInfoAbstract();
        return serverInfoAbstract.getServerInfo();
    }


}

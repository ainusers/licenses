package com.data.consumer.core;


import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

/*
 * @Author: tianyong
 * @Date: 2020/7/15 17:19
 * @Description: license安装
 */
@Component
@Order(value = 1)
public class LicenseInstall {

    @PostConstruct
    private void init(){
        System.out.println("++++++++++++测试项目启动+++++++++++++");
        System.exit(0);
    }


    //
    public  LicenseVerifyParam getVerifyParam(){
        LicenseVerifyParam param = new LicenseVerifyParam();
        param.setSubject(subject);
        param.setPublicAlias(publicAlias);
        param.setStorePass(storePass);
        param.setLicensePath(licensePath);
        param.setPublicKeysStorePath(publicKeysStorePath);
        return param;
    }

}

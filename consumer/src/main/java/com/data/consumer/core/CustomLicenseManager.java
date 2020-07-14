package com.data.consumer.core;

import de.schlichtherle.license.ftp.LicenseManager;

/*
 * @Author: tianyong
 * @Date: 2020/7/13 16:33
 * @Description: 自定义lecense管理器 (单例设计模式)
 */
public class CustomLicenseManager extends LicenseManager {

    // 定义变量
    private static volatile CustomLicenseManager instance;

    // 有参数构造 (参数后期补全)
    public CustomLicenseManager() {}

    // 单例设计模式生成对象
    public static CustomLicenseManager getInstance(){
        if(instance == null){
            synchronized (CustomLicenseManager.class){
                if(instance == null){
                    instance = new CustomLicenseManager();
                }
            }
        }
        return instance;
    }


}

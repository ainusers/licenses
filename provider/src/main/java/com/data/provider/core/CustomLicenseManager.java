package com.data.provider.core;

import com.data.provider.utils.Utils;
import de.schlichtherle.license.*;
import de.schlichtherle.xml.GenericCertificate;

/*
 * @Author: tianyong
 * @Date: 2020/7/13 16:33
 * @Description: 自定义lecense管理器 (单例设计模式)
 */
public class CustomLicenseManager extends LicenseManager {


    // 定义变量
    private static volatile CustomLicenseManager instance;


    // 有参数构造
    public CustomLicenseManager(LicenseParam param) {super(param);}


    // 单例设计模式生成对象
    public static CustomLicenseManager getInstance(LicenseParam param){
        if(instance == null){
            synchronized (CustomLicenseManager.class){
                if(instance == null){
                    instance = new CustomLicenseManager(param);
                }
            }
        }
        return instance;
    }


    // 重写LicenseManager的create方法
    @Override
    protected synchronized byte[] create(LicenseContent content,LicenseNotary notary) throws Exception {
        this.initialize(content);
        // 验证自定义变量
        Utils.validateCreate(content);
        final GenericCertificate certificate = notary.sign(content);
        return getPrivacyGuard().cert2key(certificate);
    }



}

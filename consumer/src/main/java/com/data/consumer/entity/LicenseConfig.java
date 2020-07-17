package com.data.consumer.entity;

import java.io.Serializable;

/*
 * @Author: tianyong
 * @Date: 2020/7/16 15:13
 * @Description: license配置参数
 */
public class LicenseConfig implements Serializable {

    // 证书名称
    private String subject;

    // 公钥别名
    private String publicAlias;

    // 访问公钥库的密码
    private String storePass;

    // 证书生成路径
    private String licensePath;

    // 密钥库存储路径
    private String publicKeysStorePath;


}

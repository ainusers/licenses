# licenses
<h4>项目整体思路</h4>

<h5>provider</h5>
1. 生成公钥，私钥 <br/>
    1.0. 注意：
         1.0.1 进入java/bin目录执行keytool目录
         1.0.2 storepass/keypass密码复杂度至少要满足：数字+字母
    1.0. 参数概要
         # alias: 别名
         # validity: 3650表示10年有效
         # keystore: 指定私钥库文件的名称(生成在当前目录)
         # storepass：指定私钥库的密码(获取keystore信息所需的密码)
         # keypass：指定别名条目的密码(私钥的密码)
    1.1. 首先要用KeyTool工具来生成私匙库： <br/>
         keytool -genkey -alias privatekey -keystore privateKeys.store -keysize 1024 -validity 2920 -storepass "hongyi2020" -keypass "zhimakaimen2020" -dname "CN=BigData, OU=HongYi, O=InDass, L=BJ, ST=BJ, C=CN" <br/>
          
    1.2. 然后把私匙库内的公匙导出到一个文件当中： <br/>
         keytool -export -alias privatekey -file license.cer -keystore privateKeys.store -storepass "hongyi2020" <br/>
          
    1.3. 然后再把这个证书文件导入到公匙库： <br/>
         keytool -import -alias publiccert -file license.cer -keystore publicCerts.store -storepass "hongyi2020" <br/>
          
    1.4. 最后生成文件privateKeys.store、publicCerts.store拷贝出来备用 <br/>
    
2. 

#### 项目介绍
在基于Spring的项目中使用 `TrueLicense `生成和验证`License证书`（服务器许可）的示例代码

#### 技术依赖：
* `Spring Boot`：项目基础架构
* `TrueLicense `：基于`Java`实现的生成和验证服务器许可的简单框架

#### 环境依赖：
* `JDK8+`

#### 两个子项目说明： ####

- `ServerDemo`：用于**开发者**给客户生成`License证书`的示例代码
- `ClientDemo`：**模拟需要给客户部署的业务项目**

#### ServerDemo项目： ####

对外发布了两个RESTful接口：

（1）获取服务器硬件信息 ：

请求地址：`http://127.0.0.1:7000/license/getServerInfos`

![获取服务器硬件信息](https://www.zifangsky.cn/wp-content/uploads/2018/07/20180710140711.png)

（2）生成证书 ：

请求地址：`http://127.0.0.1:7000/license/generateLicense`

请求时需要在Header中添加一个 **Content-Type** ，其值为：**application/json;charset=UTF-8**。请求参数如下： 

```json
{
	"subject": "license_demo",
	"privateAlias": "privateKey",
	"keyPass": "private_password1234",
	"storePass": "public_password1234",
	"licensePath": "C:/Users/zifangsky/Desktop/license_demo/license.lic",
	"privateKeysStorePath": "C:/Users/zifangsky/Desktop/license_demo/privateKeys.keystore",
	"issuedTime": "2018-07-10 00:00:01",
	"expiryTime": "2019-12-31 23:59:59",
	"consumerType": "User",
	"consumerAmount": 1,
	"description": "这是证书描述信息",
	"licenseCheckModel": {
		"ipAddress": ["192.168.245.1", "10.0.5.22"],
		"macAddress": ["00-50-56-C0-00-01", "50-7B-9D-F9-18-41"],
		"cpuSerial": "BFEBFBFF000406E3",
		"mainBoardSerial": "L1HF65E00X9"
	}
}
```

![生成证书](https://www.zifangsky.cn/wp-content/uploads/2018/07/20180710141528.png)

#### ClientDemo项目： ####

项目启动时安装证书，通过`cn/zifangsky/license/LicenseCheckListener.java`类实现。用户登录时校验证书的可用性，通过`cn/zifangsky/license/LicenseCheckInterceptor.java`类实现。

#### 特别说明： ####

详细开发思路可以参考我写的这篇文章：[https://www.zifangsky.cn/1277.html](https://www.zifangsky.cn/1277.html)


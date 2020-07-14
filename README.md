# licenses
<h4>项目整体思路</h4>

<h5>provider</h5>
1. 生成公钥，私钥 <br/>
    注意：进入java/bin目录执行keytool目录
    1.0. 参数概要
         # alias: 别名
         # validity: 3650表示10年有效
         # keystore: 指定私钥库文件的名称(生成在当前目录)
         # storepass：指定私钥库的密码(获取keystore信息所需的密码)
         # keypass：指定别名条目的密码(私钥的密码)
    1.1. 首先要用KeyTool工具来生成私匙库： <br/>
         keytool -genkey -alias privatekey -keystore privateKeys.store -validity 2920 -storepass "hongyi2020" -keypass "zhimakaimen" -dname "CN=HongYi, OU=BeiJing, O=InDass, L=GX, ST=GX, C=CN" <br/>
          
    1.2. 然后把私匙库内的公匙导出到一个文件当中： <br/>
         keytool -export -alias privatekey -file license.cer -keystore privateKeys.store -storepass "hongyi2020" <br/>
          
    1.3. 然后再把这个证书文件导入到公匙库： <br/>
         keytool -import -alias publiccert -file license.cer -keystore publicCerts.store -storepass "hongyi2020" <br/>
          
    1.4. 最后生成文件privateKeys.store、publicCerts.store拷贝出来备用 <br/>
    
2. 


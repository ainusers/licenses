package com.data.provider.core;

import com.data.provider.entity.SubjectVerify;
import com.data.provider.utils.Utils;
import de.schlichtherle.license.LicenseContent;
import de.schlichtherle.license.LicenseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * @Author: tianyong
 * @Date: 2020/7/21 15:13
 * @Description: license验证
 */
@Component
@Order(value = 1)
public class LicenseVerify {


    /* 成员变量 */
    private static Logger log = LoggerFactory.getLogger(LicenseVerify.class);


    // 证书subject
    @Value("${license.subject}")
    private String subject;
    // 公钥别称
    @Value("${license.publicAlias}")
    private String publicAlias;
    // 访问公钥库的密码
    @Value("${license.storePass}")
    private String storePass;
    // 证书生成路径
    @Value("${license.licensePath}")
    private String licensePath;
    // 密钥库存储路径
    @Value("${license.publicKeysStorePath}")
    private String publicKeysStorePath;


    /*
     * @Author: tianyong
     * @Date: 2020/9/27 13:38
     * @Description: 证书校验
     * @Description: 0表示首次执行任务的延迟时间，40表示每次执行任务的间隔时间，TimeUnit.MILLISECONDS执行的时间间隔数值单位
     */
    @PostConstruct
    public void getSubjectVerify(){
        SubjectVerify sv = new SubjectVerify();
        sv.setSubject(subject);
        sv.setPublicAlias(publicAlias);
        sv.setStorePass(storePass);
        sv.setLicensePath(licensePath);
        sv.setPublicKeysStorePath(publicKeysStorePath);

        //校验证书
        ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(2);
        scheduled.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(new Date());
                try{
                    boolean flag = verify(sv);
                    if(!flag) System.exit(0);
                }catch (Exception e){
                    log.error("证书巡检失败！",e);
                }finally {
                    System.out.println("check: " + date);
                }
            }
        }, 0, 1, TimeUnit.HOURS);
    }


    // 校验License证书
    public synchronized boolean verify(SubjectVerify param){
        LicenseManager licenseManager = new CustomLicenseManager(Utils.initLicenseParam(param));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            LicenseContent licenseContent = licenseManager.verify();
            // log.info(MessageFormat.format("证书校验通过，证书有效期：{0} - {1}",format.format(licenseContent.getNotBefore()),format.format(licenseContent.getNotAfter())));
            return true;
        }catch (Exception e){
            log.error("证书校验失败！",e);
            return false;
        }
    }

}

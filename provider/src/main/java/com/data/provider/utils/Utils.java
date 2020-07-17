package com.data.provider.utils;

import de.schlichtherle.license.LicenseContent;
import de.schlichtherle.license.LicenseContentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Date;

/*
 * @Author: tianyong
 * @Date: 2020/7/15 13:47
 * @Description: provider工具类
 */
public class Utils {


    /* 变量 */
    private static Logger log = LogManager.getLogger(Utils.class);


    // 校验生成证书的参数信息
    public static synchronized void validateCreate(final LicenseContent content) throws LicenseContentException {
        // 声明变量
        final Date now = new Date();
        final Date notBefore = content.getNotBefore();
        final Date notAfter = content.getNotAfter();
        final String consumerType = content.getConsumerType();

        if (null != notAfter && now.after(notAfter)){
            log.error("证书失效时间不能早于当前时间");
            throw new LicenseContentException("证书失效时间不能早于当前时间");
        }
        if (null != notBefore && null != notAfter && notAfter.before(notBefore)){
            log.error("证书生效时间不能晚于证书失效时间");
            throw new LicenseContentException("证书生效时间不能晚于证书失效时间");
        }
        if (null == consumerType){
            log.error("用户类型不能为空");
            throw new LicenseContentException("用户类型不能为空");
        }
    }

}

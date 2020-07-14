package com.data.consumer.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/*
 * @Author: tianyong
 * @Date: 2020/7/14 10:00
 * @Description: 项目工具类
 */
public class Utils {


    /* 变量 */
    private static Logger log = LogManager.getLogger(Utils.class);


    // 获取当前服务器所有符合条件的InetAddress
    public static List<InetAddress> getLocalAllInetAddress(){
        List<InetAddress> result = new ArrayList<>(4);
        try {
            // 遍历所有的网络接口
            for (Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces(); networkInterfaces.hasMoreElements(); ) {
                NetworkInterface ni = (NetworkInterface) networkInterfaces.nextElement();
                // 在所有的接口下再遍历IP
                for (Enumeration addresses = ni.getInetAddresses(); addresses.hasMoreElements(); ) {
                    InetAddress address = (InetAddress) addresses.nextElement();
                    //排除LoopbackAddress、SiteLocalAddress、LinkLocalAddress、MulticastAddress类型的IP地址
                    if(!address.isLoopbackAddress() && !address.isLinkLocalAddress() && !address.isMulticastAddress()){
                        result.add(address);
                    }
                }
            }
        } catch (SocketException e) {
            log.error("获取当前服务器所有符合条件的InetAddress失败!",e);
        }
        return result;
    }


    // 获取某个网络地址对应的Mac地址
    public static String getMacByInetAddress(InetAddress inetAddr){
        try {
            byte[] mac = NetworkInterface.getByInetAddress(inetAddr).getHardwareAddress();
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<mac.length;i++){
                if(i != 0) {
                    sb.append("-");
                }
                //将十六进制byte转化为字符串
                String temp = Integer.toHexString(mac[i] & 0xff);
                if(temp.length() == 1){
                    sb.append("0" + temp);
                }else{
                    sb.append(temp);
                }
            }
            return sb.toString().toUpperCase();
        } catch (SocketException e) {
            log.error("获取某个网络地址对应的Mac地址失败!",e);
        }
        return null;
    }

}

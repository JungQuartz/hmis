package com.jung.hmis.common.utils;

import java.security.MessageDigest;

/**
 * Created by jimlly on 2017/8/1.
 */
public class Md5Utils {

    public  static final String MD5(String str) {
        String ret = "";
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(str.getBytes("utf-8"));
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] arr = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte b = md[i];
                arr[k++] = hexDigits[b >>> 4 & 0xf];
                arr[k++] = hexDigits[b & 0xf];
            }
            ret = new String(arr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret.toLowerCase();
    }
}

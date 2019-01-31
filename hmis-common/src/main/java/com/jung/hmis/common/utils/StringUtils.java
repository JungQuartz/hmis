package com.jung.hmis.common.utils;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by jimlly on 2017/7/28.
 */
public class StringUtils {

    public static boolean isEmpty(Object value) {
        return value == null ||"".equals(value);
    }

    public static String getUID(){
       return UUID.randomUUID().toString().toUpperCase();
    }

    public static String generateVerificationCode(int length){
        char[] _c = new char[length];
        for (int i=0;i<length;i++){
            _c[i] = String.valueOf(ThreadLocalRandom.current().nextInt(10)).charAt(0);
        }
        return String.valueOf(_c);
    }

    /**
     * 首字母转小写
     * @param string
     * @return
     */
    public static String toUpperFristChar(String string) {
        char[] charArray = string.toCharArray();
        charArray[0] += 32;
        return String.valueOf(charArray);
    }


    public static String getValue(Object value){
        return value==null?"":value.toString();
    }

    public static String join(String[] arr) {
        return "";
    }
}

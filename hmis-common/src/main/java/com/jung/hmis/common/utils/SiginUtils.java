package com.jung.hmis.common.utils;

import com.jung.hmis.common.constants.Constants;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jimlly
 * @createtime: 2018/4/9下午5:19
 */
public class SiginUtils {

    public static String sign(Map<String, String> params, String secret, String signMethod) throws IOException {
        // 第一步：检查参数是否已经排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        // 第二步：把所有参数名和参数值串在一起
        StringBuilder query = new StringBuilder();
        if (Constants.SIGN_METHOD_MD5.equals(signMethod)) {
            query.append(secret);
        }
        for (String key : keys) {
            String value = params.get(key);
            if (!StringUtils.isEmpty(value)) {
                query.append(key).append(value);
            }
        }

        // 第三步：使用MD5/HMAC加密
        byte[] bytes;
        if (Constants.SIGN_METHOD_HMAC.equals(signMethod)) {
            bytes = encryptHMAC(query.toString(), secret);
        } else {
            query.append(secret);
            bytes = encryptMD5(query.toString());
        }

        // 第四步：把二进制转化为大写的十六进制(正确签名应该为32大写字符串，此方法需要时使用)
        return byte2hex(bytes);
    }
    public static byte[] encryptHMAC(String data, String secret) throws IOException {
        byte[] bytes = null;
        try {
            SecretKey secretKey = new SecretKeySpec(secret.getBytes(Constants.ENCODING_UTF8), "HmacMD5");
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            bytes = mac.doFinal(data.getBytes(Constants.ENCODING_UTF8));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.toString());
        }
        return bytes;
    }
    public static byte[] encryptMD5(String data) {
// 获得MD5摘要算法的 MessageDigest 对象
        MessageDigest mdInst = null;
        try {
            mdInst = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 使用指定的字节更新摘要
        try {
            mdInst.update(data.getBytes(Constants.ENCODING_UTF8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 获得密文
        byte[] md = mdInst.digest();
        return md;
    }

    public static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    public static void main(String[] args) throws IOException {
        String secret = "test";
        Map<String, String> parma = new HashMap<>();
        parma.put("timestamp", "2018-04-09 17:20:30");
        parma.put("nonce", "12121212");
        parma.put("token", "test");

        String biz_content = "%7B%22loanNo%22%3A%2263754m%22%2C%amount%22%3A%22234.01%22%2C%repayDate%22%3A%2018-04-09%22%2C%repayMode%22%3A%NM%22%2C%22platform%22%3A%22CAPPU%22%7D";

        parma.put("biz_content", biz_content);
//cf130c8423d7d67a79755549ed593e17
        String sigin = sign(parma, secret, Constants.SIGN_METHOD_HMAC);
        System.out.printf("sigin " + sigin);
    }
}

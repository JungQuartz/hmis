
package com.jung.hmis.common.utils;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
  
/** */

/**
 * <p> 
 * RSA公钥/私钥/签名工具包 
 * <p> 
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/> 
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/> 
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全 
 * </p> 
 *
 */  
public class RSAUtils {  
  
    /** *//** 
     * 加密算法RSA 
     */  
    public static final String KEY_ALGORITHM = "RSA";  
      
    /** *//** 
     * 签名算法 
     */  
    public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";  
  
      
    /** *//** 
     * <p> 
     * 用私钥对信息生成数字签名 
     * </p> 
     *  
     * @param data 已加密数据 
     * @param privateKey 私钥(BASE64编码) 
     *  
     * @return 
     * @throws Exception 
     */  
    public static String sign(byte[] data, String privateKey) throws Exception {  
        byte[] keyBytes = Base64Utils.decode(privateKey);  
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);  
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);  
        signature.initSign(privateK);  
        signature.update(data);  
        return Base64Utils.encode(signature.sign());  
    }  
  
    /** *//** 
     * <p> 
     * 校验数字签名 
     * </p> 
     *  
     * @param data 已加密数据 
     * @param publicKey 公钥(BASE64编码) 
     * @param sign 数字签名 
     *  
     * @return 
     * @throws Exception 
     *  
     */  
    public static boolean verify(byte[] data, String publicKey, String sign)  
            throws Exception {  
        byte[] keyBytes = Base64Utils.decode(publicKey);  
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        PublicKey publicK = keyFactory.generatePublic(keySpec);  
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);  
        signature.initVerify(publicK);  
        signature.update(data);  
        return signature.verify(Base64Utils.decode(sign));  
    }


    public static void main(String[] args) throws Exception {
        testSign();

    }
    static void testSign() throws Exception {

       String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFRxmqdUFQ9XV1TglJ9txa+Zo1Oho2nRtxgUIQ1JyyvqXxWOZkGR/Geq4VpYIUq617JbWwM3X9Scol643DZ86HX1dreBzXauQNWyvI8paH4p7cuPK/2g29hpYTCH62kQARKoC7+Tbt3pHfC/e44aK7OmFYdmiOs9rX+TO9lwqyfwIDAQAB";

       String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMVHGap1QVD1dXVOCUn23Fr5mjU6GjadG3GBQhDUnLK+pfFY5mQZH8Z6rhWlghSrrXsltbAzdf1JyiXrjcNnzodfV2t4HNdq5A1bK8jylofinty48r/aDb2GlhMIfraRABEqgLv5Nu3ekd8L97jhors6YVh2aI6z2tf5M72XCrJ/AgMBAAECgYBxw3OwkdetuS8YC31d0ub165Sa9zugnO7zgKe5jvIiPcDyMeMCfF6PlbWINB6UjwAG0RM6XiK/gur4/SKOOfGlqaNCBj4qSgaBR7RxLgoa01boOI5xASS5tnX0cTgdgY7iFzO2mTPpLtUtKMiRedXhjnVuM2HRVaKtsS9O8vYfWQJBAO2vYb/bqJ7TqXuTVWxxRESvHhpKmddtCVqeIjoLIldBCXFdcsnc5CDbk+iSqrJzKj7ZJ032ICETbp973PHyf30CQQDUeqLIV0nngzrly502jxdY99QjBJzu0P6pTGxkZeQhfltbpeKMwFpRAuzGBwAQqu1Tn+VJXgSN8UHqJ53CV9KrAkEAhsBOl7oNz/XJ+WBfJrrUbGgnldpDe8m6auCKIQQc9QDPPLExGdackzBJm6pDEiaOhdERInbuYjIlHk/XxGep9QJBALO6+K8WhwE6VDvFPDrGVy1+lQb6A/VO+9shNh7GwW5Df+vKKhR9MOiN3NK1YSy4KQAvpLemu5uFlAgJcrzNpPkCQQC2QbQ7Dv3OEjIP/C4vZWznYr+gtvi/QModpjXmp5Y23+HBn4b4nXrWMec1lTkhO5UfYzK2/4UEdkcMIg3pRwRu";

        System.out.println("私钥签名——公钥验证签名");
        String msg = "charset=UTF-8&dataobject={\"amount\":\"110000.00\",\"instCustId\":\"566052055683812\",\"entityCode\":\"210114198508103035\",\"custIpRoleId\":\"226610000051691409237\",\"applicationCode\":\"2016092810102500100001879823B\",\"email\":null,\"status\":\"ACCEPTED\",\"date\":\"2016-09-28\",\"entityType\":\"PERSON\",\"ipRoleId\":\"226610000051684391598\",\"mobile\":\"18510778644\",\"entityName\":\"李承文\"}&key=applicationEvent&service=com.mybank.bkmportal.didi";
        byte[] encodedData = msg.getBytes();
        String sign = RSAUtils.sign(encodedData, privateKey);
        System.out.println("签名:\r" + sign);
        boolean status = RSAUtils.verify(encodedData, publicKey, sign);

        String signResult = "AUJDrB2ZDTJw6VcRbs2ijMWDvuv0qx5q2P7tWHBOoieTvbMTZ6btlffSZb/N0pDAU44rxv2qACEVZt8wonwgWwmQFDTUSXf1nq9E8q0DX697kpStGiv/5M8fW+x1qutqX3AhDtAJHHgJs/LSbD+OWMZMsifhPNIiDAIwSMA4bAk=";
        System.out.println(signResult.equals(sign));
        System.out.println("验证结果:\r" + status);
    }

  
}  
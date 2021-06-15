package cn.basic.security.demo;

import cn.hutool.core.util.HexUtil;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author dragon
 * @date 2021/6/15
 */
public class MacDemo {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        hMacMD5();
        testHMacSHA526();
    }

    public static void testHMacSHA526() throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] keyBytes = initHMacSHA256();
        String content = "我就是我";
        String result = hMacSha256(content.getBytes(StandardCharsets.UTF_8), keyBytes);

        String anotherResult = hMacSha256(content.getBytes(StandardCharsets.UTF_8), keyBytes);

        System.out.println(result.equals(anotherResult));
    }

    public static void hMacMD5() throws NoSuchAlgorithmException, InvalidKeyException {
        String content = "hello world";
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
        // 产生秘钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获得秘钥
        byte[] keyEncoded = secretKey.getEncoded();

        Mac mac = Mac.getInstance("HmacMD5");

        mac.init(secretKey);

        byte[] bytes = mac.doFinal(content.getBytes(StandardCharsets.UTF_8));
        System.out.println(HexUtil.encodeHexStr(bytes));

    }

    public static String hMacSha256(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec keySpec = new SecretKeySpec(key, "HmacSHA256");
        Mac mac = Mac.getInstance(keySpec.getAlgorithm());

        mac.init(keySpec);

        return HexUtil.encodeHexStr(mac.doFinal(data));
    }

    public static byte[] initHMacSHA256() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        // 产生秘钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获得秘钥
        byte[] keyEncoded = secretKey.getEncoded();

        return keyEncoded;
    }


}

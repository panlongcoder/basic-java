package cn.basic.demo;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author dragon
 * @date 2021/6/15
 */
public class SHADemo {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        sha256();
        sha512();
    }

    public static void sha256() throws NoSuchAlgorithmException {
        String content = "潘龙你好!";
        MessageDigest messageDigest = MessageDigest.getInstance("sha-256");
        byte[] digest = messageDigest.digest(content.getBytes(StandardCharsets.UTF_8));
        System.out.println("sha-256字节数:" + digest.length); // 32
    }

    public static void sha512() throws NoSuchAlgorithmException {
        String content = "潘龙你好!";
        MessageDigest messageDigest = MessageDigest.getInstance("sha-512");
        byte[] digest = messageDigest.digest(content.getBytes(StandardCharsets.UTF_8));
        System.out.println("sha-512字节数:" + digest.length); // 64
    }



}

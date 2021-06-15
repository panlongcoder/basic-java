package cn.basic.security.demo;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.HexUtil;

import java.io.*;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author dragon
 * @date 2021/6/15
 */
public class DigestInputStreamDemo {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        test1();
        test2();
    }

    public static void test1() {
        try (InputStream asStream = ClassLoader.getSystemResourceAsStream("mysql-8.0.25-macos11-x86_64.tar.gz");
             ByteArrayOutputStream bas = new ByteArrayOutputStream(1024 * 1024)) {
            byte[] buffer = new byte[1024 * 1024];
            int affectRows = -1;
            while ((affectRows = asStream.read(buffer)) != -1) {
                bas.write(buffer, 0, affectRows);
            }
            byte[] bytes = bas.toByteArray();

            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            byte[] digest = messageDigest.digest(bytes);
            StringBuilder result = new StringBuilder();
            for (byte b : digest) {
                result.append(Integer.toHexString((b & 0xff)));
            }
            // 5db6d3f773c6d1fabc791eaa288fd164

            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void test2() throws NoSuchAlgorithmException, IOException {
        InputStream asStream = ClassLoader.getSystemResourceAsStream("mysql-8.0.25-macos11-x86_64.tar.gz");
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        DigestInputStream digestInputStream = new DigestInputStream(asStream, messageDigest);

        byte[] buffer = new byte[1024 * 1024];
        while (digestInputStream.read(buffer) != -1) {

        }
        byte[] digest = digestInputStream.getMessageDigest().digest();

        System.out.println(HexUtil.encodeHexStr(digest));
    }
}

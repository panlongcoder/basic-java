package cn.basic.security;

import cn.hutool.core.io.FileUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author dragon
 * @date 2021/6/10
 */
public class RSADemo {

    private static final String ALGORITHM = "RSA";

    private static KeyPairGenerator keyPairGenerator;

    static {
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("get keyPairGenerator instance error");
        }
    }


    /**
     * 根据给定的公私钥文件路径 构建 公私钥对
     *
     * @param pubKeyFilePath 公钥文件路径
     * @param priKeyFilePath 私钥文件路径
     * @return 返回公私钥对
     */
    public static KeyPair getKeyPair(String pubKeyFilePath, String priKeyFilePath) {
        String pubKeyStr = FileUtil.readUtf8String(pubKeyFilePath);
        String priKeyStr = FileUtil.readUtf8String(priKeyFilePath);
        try {
            // 秘钥工厂
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            // 创建私钥规范
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(priKeyStr.getBytes(StandardCharsets.UTF_8));
            PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(pubKeyStr.getBytes(StandardCharsets.UTF_8));
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

            return new KeyPair(publicKey, privateKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("key factory instance error");
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            throw new RuntimeException("invalid key specification");
        }
    }
    public static void main(String[] args) throws NoSuchAlgorithmException {
        File file = new File("./");
        System.out.println(file.getAbsoluteFile());
        generateKeyToFile("./rsa.pub", "./rsa");
    }


    /**
     * RSA算法生成公私钥保存到文件中
     *
     * @param pubKeyFilePath 公钥文件路径
     * @param priKeyFilePath 私钥文件路径
     */
    public static void generateKeyToFile(String pubKeyFilePath, String priKeyFilePath) {
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        Base64.Encoder encoder = Base64.getEncoder();
        // 私钥
        String privateKeyStr = new String(encoder.encode(privateKey.getEncoded()));
        // 公钥
        String publicKeyStr = new String(encoder.encode(publicKey.getEncoded()));

        // 写入私钥
        FileUtil.writeUtf8String(privateKeyStr, priKeyFilePath);
        // 写入公钥
        FileUtil.writeUtf8String(publicKeyStr, pubKeyFilePath);
    }

}

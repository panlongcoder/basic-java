package cn.basic;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 对称加密 工具类
 *
 * @author dragon
 * @date 2021/6/10
 */
public class SymmetricCipherUtils {

    enum Transformation {
        DES_CBC_NoPadding("DES/CBC/NoPadding", "DES"),
        DES_CBC_PKCS5Padding("DES/CBC/PKCS5Padding", "DES"),
        DES_ECB_NoPadding("DES/ECB/NoPadding", "DES"),
        DES_ECB_PKCS5Padding("DES/ECB/PKCS5Padding", "DES"),

        AES_CBC_NoPadding("AES/CBC/NoPadding", "AES"),
        AES_CBC_PKCS5Padding("AES/CBC/PKCS5Padding", "AES"),
        AES_ECB_NoPadding("AES/ECB/NoPadding", "AES"),
        AES_ECB_PKCS5Padding("AES/ECB/PKCS5Padding", "AES");

        /**
         * 转换方式
         */
        private String transformation;

        /**
         * 算法
         */
        private String algorithm;

        Transformation(String transformation, String algorithm) {
            this.transformation = transformation;
            this.algorithm = algorithm;
        }

        public String getTransformation() {
            return transformation;
        }

        public void setTransformation(String transformation) {
            this.transformation = transformation;
        }

        public String getAlgorithm() {
            return algorithm;
        }

        public void setAlgorithm(String algorithm) {
            this.algorithm = algorithm;
        }
    }

    /**
     * 加密
     *
     * @param content        加密内容
     * @param key            秘钥
     * @param transformation 转换格式
     * @return base64加密密文
     * @throws NoSuchPaddingException    找不到填充模式
     * @throws NoSuchAlgorithmException  找不到算法
     * @throws InvalidKeyException       秘钥长度要为8位
     * @throws IllegalBlockSizeException 非法的块大小错误
     * @throws BadPaddingException       非法的填充模式
     */
    public static String encrypt(String content, String key, Transformation transformation) throws
            NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, InvalidAlgorithmParameterException {
        // 获取加密解密实例对象
        Cipher cipher = Cipher.getInstance(transformation.getTransformation());
        // 构建秘钥
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8),
                transformation.getAlgorithm());
        IvParameterSpec iv = null;
        if (isCBC(transformation)) {
            // 构建初始化向量
            iv = new IvParameterSpec(key.getBytes(StandardCharsets.UTF_8));
        }
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);

        byte[] data = cipher.doFinal(content.getBytes());

        Base64.Encoder encoder = Base64.getEncoder();

        return new String(encoder.encode(data));
    }

    /**
     * 解密
     *
     * @param base64Content  base64格式密文
     * @param key            秘钥
     * @param transformation 转换格式
     * @return 原文
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String decrypt(String base64Content, String key, Transformation transformation) throws
            NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, InvalidAlgorithmParameterException {
        // 获取加密解密实例对象
        Cipher cipher = Cipher.getInstance(transformation.getTransformation());
        // 构建秘钥
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8),
                transformation.getAlgorithm());
        IvParameterSpec iv = null;
        if (isCBC(transformation)) {
            // 构建初始化向量
            iv = new IvParameterSpec(key.getBytes(StandardCharsets.UTF_8));
        }
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] data = cipher.doFinal(decoder.decode(base64Content));

        return new String(data);
    }

    /**
     * 判断是否是 CBC密码模式
     *
     * @param transformation 转换格式
     * @return boolean
     */
    private static boolean isCBC(Transformation transformation) {
        return Transformation.DES_CBC_NoPadding.equals(transformation) ||
                Transformation.DES_CBC_PKCS5Padding.equals(transformation) ||
                Transformation.AES_CBC_NoPadding.equals(transformation) ||
                Transformation.AES_CBC_PKCS5Padding.equals(transformation);
    }


}

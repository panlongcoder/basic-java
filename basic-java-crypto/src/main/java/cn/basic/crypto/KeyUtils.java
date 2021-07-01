package cn.basic.crypto;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 对称加密key的algorithm
 * 算法见: https://docs.oracle.com/javase/9/docs/specs/security/standard-names.html#keygenerator-algorithms
 * <p>
 * 非对称加密的keyPair的algorithm
 * 算法见: https://docs.oracle.com/javase/9/docs/specs/security/standard-names.html#keypairgenerator-algorithms
 *
 * @author dragon
 * @date 2021/6/21
 */
public class KeyUtils {

    private static final String SIGN_RSA = "withRSA";

    private static final String SIGN_DSA = "withDSA";


    /**
     * 仅用于对称加密的秘钥生成
     *
     * @param algorithm    秘钥算法
     * @param keySize      秘钥长度(位)
     * @param secureRandom 安全随机数
     * @return 秘钥对象
     */
    public static SecretKey generateKey(String algorithm, int keySize, SecureRandom secureRandom) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
            if (keySize > 0) {
                keyGenerator.init(keySize);
            }
            if (secureRandom != null) {
                keyGenerator.init(secureRandom);
            }

            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new CryptoException(e);
        }
    }

    /**
     * 生成秘钥,默认的秘钥长度
     *
     * @param algorithm 秘钥生成算法
     * @return 秘钥对象
     */
    public static SecretKey generateKey(String algorithm) {
        return generateKey(algorithm, -1, null);
    }

    /**
     * 根据算法默认长度生成秘钥(Base64编码形式)
     *
     * @param algorithm 算法
     * @return base64编码秘钥字符串
     */
    public static String generateKeyBase64(String algorithm) {
        return generateKeyBase64(algorithm, -1, null);
    }

    /**
     * 生成秘钥(Base64编码形式)
     *
     * @param algorithm    算法
     * @param keySize      长度
     * @param secureRandom 随机数
     * @return base64编码秘钥字符串
     */
    public static String generateKeyBase64(String algorithm, int keySize, SecureRandom secureRandom) {
        return Base64.encodeBase64String(generateKey(algorithm, keySize, secureRandom).getEncoded());
    }

    /**
     * 生成秘钥(Base64编码形式)
     *
     * @param algorithm 算法
     * @param keySize   长度
     * @return base64编码秘钥字符串
     */
    public static String generateKeyBase64(String algorithm, int keySize) {
        return generateKeyBase64(algorithm, keySize, null);
    }

    /**
     * 生成秘钥(十六进制形式)
     *
     * @param algorithm    算法
     * @param keySize      长度
     * @param secureRandom 随机数
     * @return 十六进制的秘钥字符串
     */
    public static String generateKeyHex(String algorithm, int keySize, SecureRandom secureRandom) {
        return Hex.encodeHexString(generateKey(algorithm, keySize, secureRandom).getEncoded());
    }

    /**
     * 生成秘钥(十六进制形式)
     *
     * @param algorithm 算法
     * @param keySize   长度
     * @return 十六进制的秘钥字符串
     */
    public static String generateKeyHex(String algorithm, int keySize) {
        return Hex.encodeHexString(generateKey(algorithm, keySize, null).getEncoded());
    }

    /**
     * 根据算法默认长度生成秘钥(十六进制形式)
     *
     * @param algorithm 算法
     * @return 十六进制的秘钥字符串
     */
    public static String generateKeyHex(String algorithm) {
        return Hex.encodeHexString(generateKey(algorithm, -1, null).getEncoded());
    }

    /**
     * 生成向量
     *
     * @param ivSize 向量长度(字节)
     * @return 字节数组
     */
    public static byte[] generateIv(int ivSize) {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.generateSeed(ivSize);
    }

    /**
     * 生成 rsa 秘钥对
     *
     * @param keySize 秘钥长度(位)
     * @return 秘钥对
     */
    public static KeyPair generateRSAKeyPair(int keySize) {
        return generateKeyPair("RSA", keySize);
    }

    /**
     * 生成秘钥对
     * <p>
     * 秘钥长度默认为1024位
     *
     * @return 秘钥对
     */
    public static KeyPair generateRSAKeyPair() {
        return generateRSAKeyPair(1024);
    }

    /**
     * 根据算法名称与秘钥长度 生成秘钥对
     *
     * @param algorithm 算法名称
     * @param keySize   秘钥长度(位)
     * @return 秘钥对对象
     */
    public static KeyPair generateKeyPair(String algorithm, int keySize) {
        try {
            if (algorithm.contains(SIGN_RSA)) {
                algorithm = "RSA";
            }
            if (algorithm.contains(SIGN_DSA)) {
                algorithm = "DSA";
            }
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
            if (keySize > 0) {
                keyPairGenerator.initialize(keySize);
            }
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new CryptoException(e);
        }
    }

    /**
     * 根据算法名称 生成秘钥对
     *
     * @param algorithm 算法名称
     * @return 秘钥对对象
     */
    public static KeyPair generateKeyPair(String algorithm) {
        return generateKeyPair(algorithm, -1);
    }

    /**
     * 根据 算法名与 秘钥字节 构建 私钥对象
     *
     * @param algorithm 算法名称
     * @param key       秘钥字节
     * @return 私钥对象
     */
    public static PrivateKey generatePrivateKey(String algorithm, byte[] key) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(key);

            return keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new CryptoException(e);
        }
    }

    /**
     * 通过 key字节数组构建 私钥对象
     *
     * @param key 私钥字节数组
     * @return 构建私钥对象
     */
    public static PrivateKey generateRSAPrivateKey(byte[] key) {
        return generatePrivateKey("RSA", key);
    }

    /**
     * 通过 key字节数组构建 公钥对象
     *
     * @param key 字节数组
     * @return 构建公钥对象
     */
    public static PublicKey generateRSAPublicKey(byte[] key) {
        return generatePublicKey("RSA", key);
    }

    /**
     * 根据 算法名与 秘钥字节 构建 公钥对象
     *
     * @param algorithm 算法名称
     * @param key       秘钥字节
     * @return 公钥对象
     */
    public static PublicKey generatePublicKey(String algorithm, byte[] key) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(key, algorithm);

            return keyFactory.generatePublic(x509EncodedKeySpec);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new CryptoException(e);
        }
    }

    /**
     * 生成RSA公私钥 并写入到文件中
     *
     * @param publicKeyFile  公钥文件路径
     * @param privateKeyFile 私钥文件路径
     */
    public static void generateRSAKeyPairBase64ToFile(File publicKeyFile, File privateKeyFile) {
        generateKeyPairBase64ToFile("RSA", 1024, publicKeyFile, privateKeyFile);
    }

    /**
     * 生成RSA公私钥 并写入到文件中
     *
     * @param keySize        RSA 模长度(位)
     * @param publicKeyFile  公钥文件路径
     * @param privateKeyFile 私钥文件路径
     */
    public static void generateRSAKeyPairBase64ToFile(int keySize, File publicKeyFile, File privateKeyFile) {
        generateKeyPairBase64ToFile("RSA", keySize, publicKeyFile, privateKeyFile);
    }

    /**
     * 生成公私钥 并写入到文件中
     *
     * @param algorithm      算法
     * @param publicKeyFile  公钥文件路径
     * @param privateKeyFile 私钥文件路径
     */
    public static void generateKeyPairBase64ToFile(String algorithm, int keySize, File publicKeyFile, File privateKeyFile) {
        KeyPair keyPair = generateKeyPair(algorithm, keySize);

        try {
            FileUtils.writeStringToFile(publicKeyFile, Base64.encodeBase64String(keyPair.getPublic().getEncoded()),
                    StandardCharsets.UTF_8);

            FileUtils.writeStringToFile(privateKeyFile, Base64.encodeBase64String(keyPair.getPrivate().getEncoded()),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new CryptoException(e);
        }
    }


}

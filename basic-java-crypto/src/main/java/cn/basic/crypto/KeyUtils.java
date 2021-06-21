package cn.basic.crypto;

import cn.basic.crypto.symmetric.CryptoException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author dragon
 * @date 2021/6/21
 */
public class KeyUtils {


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
}

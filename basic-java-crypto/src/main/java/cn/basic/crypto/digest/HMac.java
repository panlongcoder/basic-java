package cn.basic.crypto.digest;

import cn.basic.crypto.CryptoException;
import cn.basic.crypto.KeyUtils;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Message Authentication Code(消息认证码)
 * <p>
 * MAC算法主要集合了MD和SHA两大系列消息摘要算法。
 * <p>
 * MD系列算法有HmacMD2、HmacMD4和HmacMD5三种算法；
 * SHA系列算法有HmacSHA1、HmacSHA224、HmacSHA256、HmacSHA384和HmacSHA512五种算法。
 *
 * @author dragon
 * @date 2021/6/29
 */
public class HMac {

    /**
     * 消息认证引用
     */
    private Mac mac;

    /**
     * 秘钥
     */
    private SecretKey secretKey;

    public HMac(HMacAlgorithm algorithm, byte[] key) {
        this(algorithm, new SecretKeySpec(key, algorithm.getAlgorithm()));
    }

    public HMac(HMacAlgorithm algorithm, SecretKey secretKey) {
        try {
            this.mac = Mac.getInstance(algorithm.getAlgorithm());
            this.secretKey = secretKey;
            mac.init(secretKey);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new CryptoException(e);
        }
    }

    /**
     * 执行摘要
     *
     * @param data 原始数据
     * @return 消息摘要
     */
    public byte[] digest(byte[] data) {
        mac.reset();

        return mac.doFinal(data);
    }

    /**
     * 执行摘要 返回 十六进制字符串
     *
     * @param data 原始数据
     * @return 消息摘要(十六进制形式¬)
     */
    public String digestHexString(byte[] data) {
        mac.reset();

        return Hex.encodeHexString(mac.doFinal(data));
    }


}

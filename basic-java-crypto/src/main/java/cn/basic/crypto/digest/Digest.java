package cn.basic.crypto.digest;

import cn.basic.crypto.CryptoException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.ArrayUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

/**
 * 摘要算法
 *
 * @author dragon
 * @date 2021/6/25
 */
public class Digest {

    /**
     * 消息摘要
     */
    private MessageDigest messageDigest;

    /**
     * 盐
     */
    private byte[] salt;

    /**
     * 加盐的位置
     */
    private int saltPosition;

    /**
     * 散列次数
     */
    private int hashCount;

    public Digest(DigestAlgorithm algorithm) {
        init(algorithm, null);
    }

    public Digest(DigestAlgorithm algorithm, Provider provider, byte[] salt, int saltPosition, int hashCount) {
        this.salt = salt;
        this.saltPosition = saltPosition;
        this.hashCount = hashCount;
        init(algorithm, provider);
    }

    private void init(DigestAlgorithm algorithm, Provider provider) {
        if (algorithm == null) {
            throw new CryptoException("algorithm must not be null");
        }
        try {
            if (provider == null) {
                this.messageDigest = MessageDigest.getInstance(algorithm.getAlgorithm());
            } else {
                this.messageDigest = MessageDigest.getInstance(algorithm.getAlgorithm(), provider);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new CryptoException(e);
        }
    }

    /**
     * 摘要
     *
     * @param data 原始数据
     * @return 消息摘要
     */
    public byte[] digest(byte[] data) {
        return resetAndRepeatDigest(doDigest(data));
    }

    /**
     * 摘要最后生成16进制字符串
     *
     * @param data 原始数据
     * @return 十六进制摘要信息
     */
    public String digestHex(byte[] data) {
        return Hex.encodeHexString(digest(data));
    }

    /**
     * 摘要最后生成base64编码字符串
     *
     * @param data 原始数据
     * @return base64编码摘要信息
     */
    public String digestBase64(byte[] data) {
        return Base64.encodeBase64String(digest(data));
    }

    /**
     * 生成摘要
     *
     * @param data 摘要原文
     * @return 散列字节数组
     */
    private byte[] doDigest(byte[] data) {
        if (ArrayUtils.isEmpty(data)) {
            return messageDigest.digest(data);
        }
        if (saltPosition <= 0) {
            messageDigest.update(this.salt);
            messageDigest.update(data);
        } else if (saltPosition >= data.length) {
            messageDigest.update(data);
            messageDigest.update(this.salt);
        } else {
            messageDigest.update(data, 0, saltPosition);
            messageDigest.update(this.salt);
            messageDigest.update(data, saltPosition, data.length - saltPosition);
        }

        return messageDigest.digest();
    }

    /**
     * 重置 重复 摘要
     *
     * @param data 第一次hash后的数据
     * @return hashCount次hash后的摘要数据
     */
    private byte[] resetAndRepeatDigest(byte[] data) {
        messageDigest.reset();
        for (int i = 1; i < hashCount; i++) {
            data = messageDigest.digest(data);
            messageDigest.reset();
        }

        return data;
    }


    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public void setSaltPosition(int saltPosition) {
        this.saltPosition = saltPosition;
    }

    public void setHashCount(int hashCount) {
        this.hashCount = hashCount;
    }
}

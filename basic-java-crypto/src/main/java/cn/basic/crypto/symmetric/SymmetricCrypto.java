package cn.basic.crypto.symmetric;


import cn.basic.crypto.CryptoException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.AlgorithmParameterSpec;
/**
 * 对称加密
 *
 * @author dragon
 * @date 2021/6/18
 */
public class SymmetricCrypto implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 7920217109451119722L;

    /**
     * 加解密 秘钥
     */
    private SecretKey secretKey;

    /**
     * 加解密器
     */
    private Cipher cipher;

    /**
     * 加解密 参数(向量)
     */
    private AlgorithmParameterSpec algorithmParam;

    /**
     * 填充模式 是否是用 零 来填充
     */
    private boolean isZeroPadding;

    private int zeroPaddingCount;

    public SymmetricCrypto(Algorithm algorithm, SecretKey key) {
        this(algorithm, key, null);
    }

    public SymmetricCrypto(Algorithm algorithm, SecretKey key, AlgorithmParameterSpec algorithmParam) {
        this(algorithm.getTransformation(), key, algorithmParam);
    }

    public SymmetricCrypto(String transformation, SecretKey key) {
        this(transformation, key, null);
    }


    public SymmetricCrypto(String transformation, SecretKey key, AlgorithmParameterSpec algorithmParam) {
        if (StringUtils.isBlank(transformation)) {
            throw new IllegalArgumentException("transformation must not be blank");
        }

        this.secretKey = key;
        if (algorithmParam != null) {
            this.algorithmParam = algorithmParam;
        }

        if (transformation.contains(AlgorithmPadding.ZeroPadding.name())) {
            transformation = StringUtils.replace(transformation, AlgorithmPadding.ZeroPadding.name(),
                    AlgorithmPadding.NoPadding.name());
            isZeroPadding = true;
        }

        try {
            this.cipher = Cipher.getInstance(transformation);
        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }

    /**
     * 初始化{@link Cipher}为加密或者解密模式
     *
     * @param mode 模式
     * @return {@link Cipher}
     * @throws InvalidAlgorithmParameterException 无效的参数
     * @throws InvalidKeyException                无效的key
     */
    private Cipher initCipher(int mode) throws InvalidAlgorithmParameterException, InvalidKeyException {
        cipher.init(mode, secretKey, algorithmParam);

        return cipher;
    }

    /**
     * 加密
     *
     * @param data 加密前的bytes
     * @return 加密后的bytes
     */
    public byte[] encrypt(byte[] data) {
        try {
            Cipher cipher = initCipher(Cipher.ENCRYPT_MODE);
            return cipher.doFinal(paddingDataWithZero(data, cipher.getBlockSize()));
        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }

    /**
     * 加密 返回 十六进制小写字符数组
     *
     * @param data 加密原始数据
     * @return 十六进制小写字符数组
     */
    public char[] encryptHex(byte[] data) {
        return encryptHex(data, true);
    }

    /**
     * 加密 返回十六进制字符数组
     *
     * @param data        数据
     * @param toLowerCase 是否小写
     * @return 加密后的数据 已十六进制字符数组展示
     */
    public char[] encryptHex(byte[] data, boolean toLowerCase) {
        return Hex.encodeHex(encrypt(data), toLowerCase);
    }

    /**
     * 加密 返回十六进制字符串
     *
     * @param data 数据
     * @return 小写十六进制字符串
     */
    public String encryptHexString(byte[] data) {
        return encryptHexString(data, true);
    }

    /**
     * 加密 返回十六进制字符串
     *
     * @param data        数据
     * @param toLowerCase 是否小写
     * @return 十六进制字符串
     */
    public String encryptHexString(byte[] data, boolean toLowerCase) {
        return Hex.encodeHexString(encrypt(data), toLowerCase);
    }

    /**
     * 加密 返回Base64编码的字节数组
     *
     * @param data 加密前的bytes
     * @return 加密后的Base64编码的字节数组
     */
    public byte[] encryptBase64(byte[] data) {
        return Base64.encodeBase64(encrypt(data));
    }

    /**
     * 加密 返回Base64编码字符串
     *
     * @param data 加密前的bytes
     * @return 加密后的Base64编码
     */
    public String encryptBase64String(byte[] data) {
        return Base64.encodeBase64String(encrypt(data));
    }

    /**
     * 解密 返回字节数组
     *
     * @param data 密文
     * @return 原文字节数组
     */
    public byte[] decrypt(byte[] data) {
        try {
            Cipher cipher = initCipher(Cipher.DECRYPT_MODE);

            return removePadding(cipher.doFinal(data));
        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }

    /**
     * 解密 返回字符串
     *
     * @param data    密文
     * @param charset 编码方式
     * @return 原文字符串形式
     */
    public String decryptString(byte[] data, Charset charset) {
        return new String(decrypt(data), charset);
    }

    /**
     * 解密 返回字符串  utf-8 解密形式
     *
     * @param data 密文
     * @return 原文字符串形式
     */
    public String decryptString(byte[] data) {
        return new String(decrypt(data), StandardCharsets.UTF_8);
    }


    /**
     * 数据按照blockSize的整数倍 用零 来填充数据
     *
     * @param data      原始数据
     * @param blockSize 块大小
     * @return 填充后的数据, 如果isZeroPadding=false或者数据长度刚刚好,则返回原数据
     */
    private byte[] paddingDataWithZero(byte[] data, int blockSize) {
        if (this.isZeroPadding) {
            int remainLength = data.length % blockSize;
            if (remainLength > 0) {
                this.zeroPaddingCount = blockSize - remainLength;
                byte[] dest = new byte[data.length + zeroPaddingCount];
                System.arraycopy(data, 0, dest, 0, data.length);

                return dest;
            }
        }

        return data;
    }

    /**
     * 解密 去除多余的零
     *
     * @param data 用零填充的原数据
     * @return 原文
     */
    private byte[] removePadding(byte[] data) {
        if (this.isZeroPadding && this.zeroPaddingCount > 0) {
            byte[] dest = new byte[data.length - zeroPaddingCount];
            System.arraycopy(data, 0, dest, 0, dest.length);

            return dest;
        }

        return data;
    }

    public void setAlgorithmParam(AlgorithmParameterSpec algorithmParam) {
        this.algorithmParam = algorithmParam;
    }
}

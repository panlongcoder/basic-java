package cn.basic.crypto.symmetric;

import cn.basic.crypto.KeyUtils;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密
 * <p>
 * AES秘钥的长度支持 128、192、256位
 *
 * @author dragon
 * @date 2021/6/21
 */
public class AES extends SymmetricCrypto {

    private static final long serialVersionUID = -6696847429998382417L;

    private static final String ALGORITHM = "AES";

    private static final String TRANSFORMATION = "AES/%s/%s";


    /**
     * 构造
     *
     * @param mode    加密模式
     * @param padding 填充模式
     * @param key     秘钥
     * @param iv      加密向量
     */
    public AES(AlgorithmMode mode, AlgorithmPadding padding, byte[] key, byte[] iv) {
        super(String.format(TRANSFORMATION, mode, padding), new SecretKeySpec(key, ALGORITHM),
                iv != null ? new IvParameterSpec(iv) : null);
    }

    /**
     * 构造
     *
     * @param mode    加密模式
     * @param padding 填充模式
     * @param key     秘钥
     */
    public AES(AlgorithmMode mode, AlgorithmPadding padding, byte[] key) {
        this(mode, padding, key, null);
    }

    /**
     * 构造
     *
     * @param mode    加密模式
     * @param padding 填充模式
     */
    public AES(AlgorithmMode mode, AlgorithmPadding padding) {
        this(mode, padding, KeyUtils.generateKey(ALGORITHM).getEncoded());
    }

    /**
     * 构造
     *
     * @param transformation 转换方式(算法/加密模式/填充模式)
     * @param key            秘钥
     */
    public AES(String transformation, byte[] key) {
        super(transformation, new SecretKeySpec(key, ALGORITHM));
    }

    /**
     * 构造
     *
     * @param transformation 转换方式(算法/加密模式/填充模式)
     */
    public AES(String transformation) {
        this(transformation, KeyUtils.generateKey(ALGORITHM).getEncoded());
    }

    /**
     * 构造
     *
     * @param key 秘钥
     */
    public AES(byte[] key) {
        this(Algorithm.AES.getTransformation(), key);
    }

    /**
     * 构造
     * <p>
     * 默认转换方式与秘钥
     */
    public AES() {
        this(Algorithm.AES.getTransformation(), KeyUtils.generateKey(ALGORITHM).getEncoded());
    }

}

package cn.basic.crypto.symmetric;

import cn.basic.crypto.KeyUtils;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * DES加密
 * <p>
 * AES秘钥的长度支持 64位
 *
 * @author dragon
 * @date 2021/6/21
 */
public class DES extends SymmetricCrypto {

    private static final long serialVersionUID = -6696847429998382417L;

    private static final String ALGORITHM = "DES";

    private static final String TRANSFORMATION = "DES/%s/%s";


    /**
     * 构造
     *
     * @param mode    加密模式
     * @param padding 填充模式
     * @param key     秘钥
     * @param iv      加密向量
     */
    public DES(AlgorithmMode mode, AlgorithmPadding padding, byte[] key, IvParameterSpec iv) {
        super(String.format(TRANSFORMATION, mode, padding), new SecretKeySpec(key, ALGORITHM), iv);
    }

    /**
     * 构造
     *
     * @param mode    加密模式
     * @param padding 填充模式
     * @param key     秘钥
     */
    public DES(AlgorithmMode mode, AlgorithmPadding padding, byte[] key) {
        this(mode, padding, key, null);
    }

    /**
     * 构造
     *
     * @param transformation 转换方式(算法/加密模式/填充模式)
     * @param key            秘钥
     */
    public DES(String transformation, byte[] key) {
        super(transformation, new SecretKeySpec(key, ALGORITHM));
    }

    /**
     * 构造
     *
     * @param key 秘钥
     */
    public DES(byte[] key) {
        this(Algorithm.DES.getTransformation(), key);
    }

    /**
     * 构造
     * <p>
     * 默认转换方式与秘钥
     */
    public DES() {
        this(Algorithm.DES.getTransformation(), KeyUtils.generateKey(ALGORITHM).getEncoded());
    }

}

package cn.basic.crypto.symmetric;

import cn.basic.crypto.KeyUtils;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * DESede是由DES对称加密算法改进后的一种对称加密算法，又名3DES、TripleDES。<br>
 * 使用 168 位的密钥对资料进行三次加密的一种机制；它通常（但非始终）提供极其强大的安全性。<br>
 * 如果三个 56 位的子元素都相同，则三重 DES 向后兼容 DES。<br>
 * Java中默认实现为：DESede/ECB/PKCS5Padding
 *
 * @author dragon
 * @date 2021/6/21
 */
public class DESede extends SymmetricCrypto {

    private static final long serialVersionUID = -6696847429998382417L;

    private static final String ALGORITHM = "DESede";

    private static final String TRANSFORMATION = "DESede/%s/%s";


    /**
     * 构造
     *
     * @param mode    加密模式
     * @param padding 填充模式
     * @param key     秘钥
     * @param iv      加密向量
     */
    public DESede(AlgorithmMode mode, AlgorithmPadding padding, byte[] key, IvParameterSpec iv) {
        super(String.format(TRANSFORMATION, mode, padding), new SecretKeySpec(key, ALGORITHM), iv);
    }

    /**
     * 构造
     *
     * @param mode    加密模式
     * @param padding 填充模式
     * @param key     秘钥
     */
    public DESede(AlgorithmMode mode, AlgorithmPadding padding, byte[] key) {
        this(mode, padding, key, null);
    }

    /**
     * 构造
     *
     * @param transformation 转换方式(算法/加密模式/填充模式)
     * @param key            秘钥
     */
    public DESede(String transformation, byte[] key) {
        super(transformation, new SecretKeySpec(key, ALGORITHM));
    }

    /**
     * 构造
     *
     * @param key 秘钥
     */
    public DESede(byte[] key) {
        this(Algorithm.DESede.getTransformation(), key);
    }

    /**
     * 构造
     * <p>
     * 默认转换方式与秘钥
     */
    public DESede() {
        this(Algorithm.DESede.getTransformation(), KeyUtils.generateKey(ALGORITHM).getEncoded());
    }

}

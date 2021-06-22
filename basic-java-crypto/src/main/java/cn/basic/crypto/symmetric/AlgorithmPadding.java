package cn.basic.crypto.symmetric;

/**
 * 填充模式
 *
 * @author dragon
 * @date 2021/6/18
 */
public enum AlgorithmPadding {

    /**
     * 无补码
     */
    NoPadding,
    /**
     * 0补码，即不满block长度时使用0填充
     */
    ZeroPadding,
    /**
     * PKCS#5 密码加密模式
     */
    PKCS5Padding;
}

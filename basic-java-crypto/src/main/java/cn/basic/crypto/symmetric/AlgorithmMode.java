package cn.basic.crypto.symmetric;

/**
 * 密码模式
 *
 * ECB 无初始化向量, 每次加密均产生独立的密文分组,每组的加密结果不会对其他分组产生影响,相同的明文加密后产生相同的密文
 * CBC 有初始化向量, 不会产生相同的密文, 不利于并行计算
 *
 * @author dragon
 * @date 2021/6/18
 */
public enum AlgorithmMode {

    /**
     * 无模式
     */
    NONE,

    /**
     * 电子密码本模式
     */
    ECB,

    /**
     * 密文链接模式
     */
    CBC,

    /**
     * 密文反馈模式
     */
    CFB,

    /**
     * 输出反馈模式
     */
    OFB,

    /**
     * 计数器模式
     */
    CTR;
}

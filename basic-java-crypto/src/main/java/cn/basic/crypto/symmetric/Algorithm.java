package cn.basic.crypto.symmetric;

/**
 * 算法
 *
 * @author dragon
 * @date 2021/6/18
 */
public enum Algorithm {

    DES("DES/ECB/PKCS5Padding"),

    AES("AES/ECB/PKCS5Padding"),

    DESede("DESede/CBC/PKCS5Padding");

    /**
     * 转换格式 算法/加密模式/填充模式
     * 都是默认的转换格式
     */
    private String transformation;

    Algorithm(String transformation) {
        this.transformation = transformation;
    }

    public String getTransformation() {
        return transformation;
    }

    public void setTransformation(String transformation) {
        this.transformation = transformation;
    }

}

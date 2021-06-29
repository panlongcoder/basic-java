package cn.basic.crypto.digest;

/**
 * 摘要算法
 *
 * @author dragon
 * @date 2021/6/22
 */
public enum DigestAlgorithm {

    MD5("MD5"),
    SHA1("SHA-1"),
    SHA256("SHA-256"),
    SHA512("SHA-512");

    private String algorithm;

    DigestAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}

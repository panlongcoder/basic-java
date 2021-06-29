package cn.basic.crypto.digest;

/**
 * @author dragon
 * @date 2021/6/29
 */
public enum HMacAlgorithm {

    HmacMD5("HmacMD5"),
    HmacSHA1("HmacSHA1"),
    HmacSHA256("HmacSHA256"),
    HmacSHA384("HmacSHA384"),
    HmacSHA512("HmacSHA512");

    private String algorithm;

    HMacAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAlgorithm() {
        return algorithm;
    }
}

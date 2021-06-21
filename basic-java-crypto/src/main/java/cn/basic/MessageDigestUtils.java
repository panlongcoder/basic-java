package cn.basic;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 消息摘要 demo
 *
 * @author dragon
 * @date 2021/6/10
 */
public class MessageDigestUtils {

    /**
     * 算法
     */
    enum Algorithm {
        MD5("MD5"),
        SHA1("SHA-1"),
        SHA256("SHA-256"),
        SHA512("SHA-512");

        private String algorithm;

        Algorithm(String algorithm) {
            this.algorithm = algorithm;
        }

        public String getAlgorithm() {
            return algorithm;
        }

        public void setAlgorithm(String algorithm) {
            this.algorithm = algorithm;
        }
    }


    public static void main(String[] args) throws NoSuchAlgorithmException {
        String content = "dragon";
        System.out.println(toMessageDigest(content, Algorithm.MD5));
        System.out.println(toMessageDigest(content, Algorithm.SHA1));
        System.out.println(toMessageDigest(content, Algorithm.SHA256));
        System.out.println(toMessageDigest(content, Algorithm.SHA512));
    }

    /**
     * 根据hash算法计算 固定长度的hash值(消息摘要)
     *
     * @param content   内容
     * @param algorithm 算法
     * @return 消息摘要
     * @throws NoSuchAlgorithmException 找不到算法异常
     */
    public static String toMessageDigest(String content, Algorithm algorithm) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm.getAlgorithm());
        byte[] digestBytes = messageDigest.digest(content.getBytes(StandardCharsets.UTF_8));
        StringBuilder result = new StringBuilder();
        for (byte aByte : digestBytes) {
            String hexStr = Integer.toHexString(aByte & 0xff);
            if (hexStr.length() == 1) {
                result.append(0);
            }
            result.append(hexStr);
        }

        return result.toString();
    }

}

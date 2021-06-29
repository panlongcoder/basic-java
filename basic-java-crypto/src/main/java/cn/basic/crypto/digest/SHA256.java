package cn.basic.crypto.digest;

import java.security.Provider;

/**
 * Secure Hash Algorithm 256位
 *
 * 消息摘要长度与安全强度成正比
 *
 * @author dragon
 * @date 2021/6/29
 */
public class SHA256 extends Digest{

    public SHA256() {
        this(null);
    }

    public SHA256(byte[] salt) {
        this(null, salt, 0, 1);
    }

    public SHA256(Provider provider, byte[] salt, int saltPosition, int hashCount) {
        super(DigestAlgorithm.SHA256, provider, salt, saltPosition, hashCount);
    }
}

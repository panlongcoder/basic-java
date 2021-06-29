package cn.basic.crypto.digest;

import java.security.Provider;

/**
 * Secure Hash Algorithm(安全散列算法) 256位
 *
 * @author dragon
 * @date 2021/6/29
 */
public class SHA512 extends Digest {

    public SHA512() {
        this(null);
    }

    public SHA512(byte[] salt) {
        this(null, salt, 0, 1);
    }

    public SHA512(Provider provider, byte[] salt, int saltPosition, int hashCount) {
        super(DigestAlgorithm.SHA512, provider, salt, saltPosition, hashCount);
    }
}

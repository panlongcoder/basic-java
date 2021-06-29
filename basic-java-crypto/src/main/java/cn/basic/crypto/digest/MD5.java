package cn.basic.crypto.digest;

import java.security.Provider;

/**
 * @author dragon
 * @date 2021/6/29
 */
public class MD5 extends Digest {

    public MD5() {
        this(null);
    }

    public MD5(byte[] salt) {
        this(null, salt, 0, 1);
    }

    public MD5(Provider provider, byte[] salt, int saltPosition, int hashCount) {
        super(DigestAlgorithm.MD5, provider, salt, saltPosition, hashCount);
    }
}

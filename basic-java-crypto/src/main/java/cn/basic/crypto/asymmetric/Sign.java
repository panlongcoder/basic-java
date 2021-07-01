package cn.basic.crypto.asymmetric;

import cn.basic.crypto.CryptoException;
import cn.basic.crypto.KeyUtils;
import org.bouncycastle.util.encoders.Base64;

import java.security.*;

/**
 * 数字签名
 * <p>
 * 不可抵赖 + 无法仿冒
 * <p>
 * 摘要算法 + 非对称加密算法
 *
 * <p>
 * signature algorithm
 * <p>
 * 算法见: https://docs.oracle.com/javase/9/docs/specs/security/standard-names.html#signature-algorithms
 *
 * @author dragon
 * @date 2021/7/1
 */
public class Sign {

    private final Signature signature;

    private final PublicKey publicKey;

    private final PrivateKey privateKey;


    public Sign(SignAlgorithm algorithm) {
        KeyPair keyPair = KeyUtils.generateKeyPair(algorithm.name());
        this.publicKey = keyPair.getPublic();
        this.privateKey = keyPair.getPrivate();
        try {
            this.signature = Signature.getInstance(algorithm.name());
        } catch (NoSuchAlgorithmException e) {
            throw new CryptoException(e);
        }
    }

    public Sign(SignAlgorithm algorithm, String publicKeyStr, String privateKeyStr) {
        this.publicKey = KeyUtils.generatePublicKey(algorithm.name(), Base64.decode(publicKeyStr));
        this.privateKey = KeyUtils.generatePrivateKey(algorithm.name(), Base64.decode(privateKeyStr));
        try {
            this.signature = Signature.getInstance(algorithm.name());
        } catch (NoSuchAlgorithmException e) {
            throw new CryptoException(e);
        }
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param plainBytes 原文
     * @return 签名
     */
    public byte[] sign(byte[] plainBytes) {
        try {
            this.signature.initSign(this.privateKey);
            this.signature.update(plainBytes);

            return this.signature.sign();
        } catch (InvalidKeyException | SignatureException e) {
            throw new CryptoException(e);
        }
    }

    /**
     * 用公钥 验签
     *
     * @param plain     原文
     * @param signature 数字签名
     * @return boolean
     */
    public boolean verify(byte[] plain, byte[] signature) {
        try {
            this.signature.initVerify(this.publicKey);
            this.signature.update(plain);

            return this.signature.verify(signature);
        } catch (InvalidKeyException | SignatureException e) {
            throw new CryptoException(e);
        }

    }

}

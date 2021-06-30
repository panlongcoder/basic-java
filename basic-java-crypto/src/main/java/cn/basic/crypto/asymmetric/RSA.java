package cn.basic.crypto.asymmetric;

import cn.basic.crypto.CryptoException;
import cn.basic.crypto.KeyUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;

/**
 * RSA 非对称加密
 * <p>
 * 由于非对称加密速度极其缓慢, 一般文件不使用它来加密而是使用对称加密来加密
 * <p>
 * 非对称加密可以用来对 对称加密的秘钥 进行加密,这样 就 可以 利用 对称加密对 文件进行加密,而又只有双方才知道 对称加密的秘钥
 *
 * @author dragon
 * @date 2021/6/30
 */
public class RSA {

    private Cipher cipher;

    private PrivateKey privateKey;

    private PublicKey publicKey;

    public RSA() {
        this(null, null);
    }

    public RSA(String publicKeyStr, String privateKeyStr) {
        init(publicKeyStr, privateKeyStr);
    }

    private void init(String publicKeyStr, String privateKeyStr) {
        try {
            this.cipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new CryptoException(e);
        }
        if (StringUtils.isEmpty(publicKeyStr) && StringUtils.isEmpty(privateKeyStr)) {
            KeyPair keyPair = KeyUtils.generateRSAKeyPair();
            this.privateKey = keyPair.getPrivate();
            this.publicKey = keyPair.getPublic();
        } else {
            this.publicKey = KeyUtils.generateRSAPublicKey(Base64.decode(publicKeyStr));
            this.privateKey = KeyUtils.generateRSAPrivateKey(Base64.decode(privateKeyStr));
        }
    }

    /**
     * 加密
     *
     * @param plainBytes 明文字节数组
     * @param keyType    秘钥类型
     * @return 密文字节数组
     */
    public byte[] encrypt(byte[] plainBytes, KeyType keyType) {
        Key key = getKeyByType(keyType);
        try {
            this.cipher.init(Cipher.ENCRYPT_MODE, key);

            return cipher.doFinal(plainBytes);

        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new CryptoException(e);
        }
    }

    /**
     * 解密
     *
     * @param cipherBytes 密文字节数组
     * @param keyType     秘钥类型
     * @return 明文
     */
    public byte[] decrypt(byte[] cipherBytes, KeyType keyType) {
        Key key = getKeyByType(keyType);
        try {
            this.cipher.init(Cipher.DECRYPT_MODE, key);

            return this.cipher.doFinal(cipherBytes);

        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new CryptoException(e);
        }
    }


    /**
     * 根据类型查询对应的秘钥
     *
     * @param type 秘钥类型
     * @return 秘钥对象
     */
    private Key getKeyByType(KeyType type) {
        if (type == null || KeyType.SECRET_KEY.equals(type)) {
            throw new CryptoException("Unsupported key type:" + type);
        }

        return KeyType.PUBLIC_KEY.equals(type) ? this.publicKey : this.privateKey;

    }


}

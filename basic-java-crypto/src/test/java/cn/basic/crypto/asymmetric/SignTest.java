package cn.basic.crypto.asymmetric;

import cn.basic.crypto.KeyUtils;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.security.*;

/**
 * @author dragon
 * @date 2021/7/1
 */
public class SignTest {

    @Test
    public void test1() throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        KeyPair keyPair = KeyUtils.generateKeyPair("RSA");

        PublicKey aPublic = keyPair.getPublic();
        PrivateKey aPrivate = keyPair.getPrivate();

        Signature signature = Signature.getInstance(SignAlgorithm.SHA256withRSA.name());

        signature.initSign(aPrivate);

        String plainText = "dragon12";

        signature.update(plainText.getBytes(StandardCharsets.UTF_8));

        signature.initSign(aPrivate);

        signature.update("dragon".getBytes(StandardCharsets.UTF_8));

        byte[] sign = signature.sign();

        signature.initVerify(aPublic);

        signature.update("dragon".getBytes(StandardCharsets.UTF_8));

        Assert.assertTrue(signature.verify(sign));

    }

    @Test
    public void test2() {
        Sign sign = new Sign(SignAlgorithm.SHA256withRSA);
        String plain = "helloWorld";

        byte[] signature = sign.sign(plain.getBytes(StandardCharsets.UTF_8));

        boolean verify = sign.verify(plain.getBytes(StandardCharsets.UTF_8), signature);

        Assert.assertTrue(verify);
    }
}

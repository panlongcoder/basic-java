package cn.basic.crypto.asymmetric;

import cn.basic.crypto.KeyUtils;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

/**
 * @author dragon
 * @date 2021/6/30
 */
public class RSATest {


    @Test
    public void test1() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA");

        KeyPair keyPair = KeyUtils.generateRSAKeyPair();

        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());

        String plainText = "明文.就是我";

        byte[] cipherBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());

        String otherPlainText = new String(cipher.doFinal(cipherBytes), StandardCharsets.UTF_8);

        assertEquals(true, otherPlainText.equals(plainText));
    }

    @Test
    public void test2() {
        RSA rsa = new RSA();

        String plainText = "dragon hello world";

        byte[] encryptBytes = rsa.encrypt(plainText.getBytes(StandardCharsets.UTF_8), KeyType.PUBLIC_KEY);

        byte[] decryptBytes = rsa.decrypt(encryptBytes, KeyType.PRIVATE_KEY);

        String anotherPlainText = new String(decryptBytes, StandardCharsets.UTF_8);

        assertEquals(true, plainText.equals(anotherPlainText));
    }
}

package cn.basic.crypto;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.security.*;

/**
 * @author dragon
 * @date 2021/6/29
 */
public class KeyUtilsTest {

    @Test
    public void test1() {
        String hmacSHA256 = KeyUtils.generateKeyHex("HmacSHA256");

        System.out.println(hmacSHA256);

        String hmacSHA2561 = KeyUtils.generateKeyHex("HmacSHA256");

        System.out.println(hmacSHA2561);
    }

    @Test
    public void test2() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, IOException {
        File publicKeyFile = new File("./src/main/resources/test.pub");
        File privateKeyFile = new File("./src/main/resources/test");
        KeyUtils.generateRSAKeyPairBase64ToFile(publicKeyFile, privateKeyFile);

        PublicKey publicKey = KeyUtils.generateRSAPublicKey(Base64.decodeBase64(FileUtils.readFileToByteArray(publicKeyFile)));

        PrivateKey privateKey = KeyUtils.generateRSAPrivateKey(Base64.decodeBase64(FileUtils.readFileToByteArray(privateKeyFile)));

    }
}

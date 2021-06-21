package cn.basic.crypto.symmetric;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

/**
 * @author dragon
 * @date 2021/6/21
 */
public class SymmetricCryptoTest {

    @Test
    public void aesTest() throws DecoderException {
        String data = "测试对称加密";
        String key = "dragon1911111111";
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

        SymmetricCrypto symmetricCrypto = new SymmetricCrypto(Algorithm.AES, keySpec);

        byte[] encrypt = symmetricCrypto.encrypt(data.getBytes(StandardCharsets.UTF_8));

        byte[] decrypt = symmetricCrypto.decrypt(encrypt);

        assertEquals(data, new String(decrypt, StandardCharsets.UTF_8));

        String encryptHexString = symmetricCrypto.encryptHexString(data.getBytes(StandardCharsets.UTF_8));

        String decryptStr = symmetricCrypto.decryptString(Hex.decodeHex(encryptHexString), StandardCharsets.UTF_8);

        assertEquals(data, decryptStr);

    }


}

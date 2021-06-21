package cn.basic.crypto.symmetric;

import cn.basic.crypto.KeyUtils;
import org.junit.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

/**
 * @author dragon
 * @date 2021/6/21
 */
public class AESTest {

    @Test
    public void test() {
        AES aes = new AES();
        String content = "君子当如竹 傻逼东西";
        byte[] encrypt = aes.encrypt(content.getBytes(StandardCharsets.UTF_8));
        String decrypt = aes.decryptString(encrypt);
        assertEquals(content, decrypt);

    }

    @Test
    public void test2() {
        String content = "君子当如竹 傻逼东西";

        SecretKey secretKey = KeyUtils.generateKey(Algorithm.AES.name());
        String IvParameter = "dragon1999211111";
        AES aes = new AES(AlgorithmMode.CBC, AlgorithmPadding.PKCS5Padding, secretKey.getEncoded(), IvParameter.getBytes(StandardCharsets.UTF_8));

        byte[] encrypt = aes.encrypt(content.getBytes(StandardCharsets.UTF_8));

       assertEquals(content,  aes.decryptString(encrypt));
    }

    @Test
    public void test3() {
        String content = "君子当如竹 傻逼东西";

        SecretKey secretKey = KeyUtils.generateKey(Algorithm.AES.name());
        AES aes = new AES(AlgorithmMode.ECB, AlgorithmPadding.ZeroPadding, secretKey.getEncoded());

        byte[] encrypt = aes.encrypt(content.getBytes(StandardCharsets.UTF_8));

        assertEquals(content,  aes.decryptString(encrypt));
    }

}

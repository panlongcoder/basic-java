package cn.basic.crypto.symmetric;

import cn.basic.crypto.KeyUtils;
import org.junit.Test;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

/**
 * @author dragon
 * @date 2021/6/21
 */
public class DESTest {


    @Test
    public void test() {
        DES des = new DES();
        String content = "君子当如竹 傻逼东西";

        byte[] encrypt = des.encrypt(content.getBytes(StandardCharsets.UTF_8));

        byte[] decrypt = des.decrypt(encrypt);

        assertEquals(content, new String(decrypt, StandardCharsets.UTF_8));
    }

    @Test
    public void test2() {
        String content = "君子当如竹 傻逼东西";

        SecretKey secretKey = KeyUtils.generateKey(Algorithm.DES.name());
        String IvParameter = "dragon19";
        DES des = new DES(AlgorithmMode.CBC, AlgorithmPadding.PKCS5Padding, secretKey.getEncoded(), IvParameter.getBytes(StandardCharsets.UTF_8));

        byte[] encrypt = des.encrypt(content.getBytes(StandardCharsets.UTF_8));

        assertEquals(content, des.decryptString(encrypt));
    }

    @Test
    public void test3() {
        String content = "君子当如竹 傻逼东西";

        SecretKey secretKey = KeyUtils.generateKey(Algorithm.DES.name());
        DES des = new DES(AlgorithmMode.ECB, AlgorithmPadding.ZeroPadding, secretKey.getEncoded());

        byte[] encrypt = des.encrypt(content.getBytes(StandardCharsets.UTF_8));

        assertEquals(content, des.decryptString(encrypt));
    }

    @Test
    public void test4() {
        String content = "君子当如竹 傻逼东西";
        DES aes = new DES(AlgorithmMode.CTR, AlgorithmPadding.PKCS5Padding);

        String iv = "11111111";
        // 设置向量
        aes.setAlgorithmParam(new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));

        byte[] encrypt = aes.encrypt(content.getBytes(StandardCharsets.UTF_8));

        byte[] decrypt = aes.decrypt(encrypt);

        assertEquals(content, new String(decrypt));
    }
}

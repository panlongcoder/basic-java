package cn.basic.crypto.symmetric;

import cn.basic.crypto.CryptoException;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;

import static org.junit.Assert.assertEquals;

/**
 * @author dragon
 * @date 2021/6/22
 */
public class DESedeTest {

    @Test
    public void test() {
        DESede deSede = new DESede();

        String content = "君子当如竹 傻逼东西";
        byte[] encrypt = deSede.encrypt(content.getBytes(StandardCharsets.UTF_8));

        byte[] decrypt = deSede.decrypt(encrypt);

        assertEquals(content, new String(decrypt));
    }

    @Test
    public void test2() {
        DESede deSede = new DESede(AlgorithmMode.ECB, AlgorithmPadding.ZeroPadding);
        String content = "君子当如竹 傻逼东西";

        byte[] encrypt = deSede.encrypt(content.getBytes(StandardCharsets.UTF_8));

        byte[] decrypt = deSede.decrypt(encrypt);

        assertEquals(content, new String(decrypt));
    }

    /**
     * 因为CFB模式下,需要向量,所以会抛出CryptoException
     */
    @Test(expected = CryptoException.class)
    public void test3() {
        DESede deSede = new DESede(AlgorithmMode.CFB, AlgorithmPadding.PKCS5Padding);

        String content = "君子当如竹 傻逼东西";

        byte[] encrypt = deSede.encrypt(content.getBytes(StandardCharsets.UTF_8));

        byte[] decrypt = deSede.decrypt(encrypt);

        assertEquals(content, new String(decrypt));
    }
}

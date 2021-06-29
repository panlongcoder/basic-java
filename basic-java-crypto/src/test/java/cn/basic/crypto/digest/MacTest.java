package cn.basic.crypto.digest;

import cn.basic.crypto.KeyUtils;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import javax.crypto.Mac;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author dragon
 * @date 2021/6/29
 */
public class MacTest {

    @Test
    public void test() throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmacSHA256 = Mac.getInstance("HmacSHA256");

        hmacSHA256.init(KeyUtils.generateKey("HmacSHA256"));

        byte[] bytes = hmacSHA256.doFinal("dragon".getBytes(StandardCharsets.UTF_8));
        String s = Hex.encodeHexString(bytes);
        System.out.println(s);
        System.out.println("长度位数:" + s.length());
    }

    @Test
    public void test2() {
        HMac hMac = new HMac(HMacAlgorithm.HmacSHA512, KeyUtils.generateKey(HMacAlgorithm.HmacSHA512.getAlgorithm()));

        String s = hMac.digestHexString("dragon".getBytes(StandardCharsets.UTF_8));
        System.out.println(s);

        String s1 = hMac.digestHexString("dragon".getBytes(StandardCharsets.UTF_8));

        System.out.println(s1);
    }

}

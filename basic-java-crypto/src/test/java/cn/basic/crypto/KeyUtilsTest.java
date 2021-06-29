package cn.basic.crypto;

import org.junit.Test;

/**
 * @author dragon
 * @date 2021/6/29
 */
public class KeyUtilsTest {

    @Test
    public void test() {
        String hmacSHA256 = KeyUtils.generateKeyHex("HmacSHA256");

        System.out.println(hmacSHA256);

        String hmacSHA2561 = KeyUtils.generateKeyHex("HmacSHA256");

        System.out.println(hmacSHA2561);
    }
}

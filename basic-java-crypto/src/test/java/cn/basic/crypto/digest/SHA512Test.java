package cn.basic.crypto.digest;

import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author dragon
 * @date 2021/6/29
 */
public class SHA512Test {

    @Test
    public void test() {
        SHA512 sha512 = new SHA512();

        String s = sha512.digestHex("dragon".getBytes(StandardCharsets.UTF_8));

        System.out.println(s);
        System.out.println("位数:" + (s.length() / 2 * 8));


    }
}

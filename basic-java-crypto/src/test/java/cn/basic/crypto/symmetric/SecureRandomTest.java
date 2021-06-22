package cn.basic.crypto.symmetric;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * @author dragon
 * @date 2021/6/22
 */
public class SecureRandomTest {


    @Test
    public void test() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] result = new byte[20];
        secureRandom.nextBytes(result);

        System.out.println(Arrays.toString(result));
    }

}

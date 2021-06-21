package cn.basic;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

/**
 * @author dragon
 * @date 2021/6/17
 */
public class SecureRandomDemo {


    public static void main(String[] args) {
//        testRandom();
//
//        testSecureRandom();
//
        byte[] array = new byte[20];
        SecureRandom secureRandom = new SecureRandom(array);
        byte[] result = new byte[200];
        secureRandom.nextBytes(result);

        System.out.println(Arrays.toString(result));

        byte[] bytes = secureRandom.generateSeed(10);
        System.out.println(Arrays.toString(bytes));
    }

    public static void testRandom() {
        Random random = new Random(10);
        float v = random.nextFloat();
        System.out.println(v);

        Random random1 = new Random(10);
        System.out.println(random1.nextFloat());
    }


    public static void testSecureRandom() {
        byte[] array = {1, 2,5,6 ,7,2};
        SecureRandom secureRandom = new SecureRandom(array);

        SecureRandom secureRandom1 = new SecureRandom(array);

        System.out.println(secureRandom.nextFloat());
        System.out.println(secureRandom1.nextFloat());
    }



}

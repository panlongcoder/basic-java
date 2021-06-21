package cn.basic.demo.key;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

/**
 * 秘钥算法     字节      编码格式
 *  AES        16        RAW
 *  DES        8         RAW
 *  DESede     24        RAW
 *
 *
 * @author dragon
 * @date 2021/6/17
 */
public class KeyTest {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");


        SecretKey secretKey = keyGenerator.generateKey();


        System.out.println(secretKey.getEncoded().length);
    }
}

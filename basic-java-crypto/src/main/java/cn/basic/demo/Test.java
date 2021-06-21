package cn.basic.demo;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author dragon
 * @date 2021/6/15
 */
public class Test {

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("DES");
        String data = "dragon19";
        SecretKeySpec secretKeySpec = new SecretKeySpec(data.getBytes(StandardCharsets.UTF_8), cipher.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        System.out.println("块大小" + cipher.getBlockSize());

        String transformation = "AES/%s/%s";

        String format = String.format(transformation, "dr", "d");
        System.out.println(format);
    }
}

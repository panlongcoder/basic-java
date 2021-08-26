package cn.basic.crypto.asymmetric;

import cn.basic.crypto.digest.Digest;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

/**
 * @author dragon
 * @date 2021/7/5
 */
public class Test {


    public static void main(String[] args) {
        System.out.println(Base64.encodeBase64String("user:dragon1992".getBytes(StandardCharsets.UTF_8)));
    }
}

package cn.basic.security;

/**
 * @author dragon
 * @date 2021/6/10
 */
public class SymmetricCipherUtilsTest {

    public static void main(String[] args) throws Exception {
        String content = "dragon";
        // AES加密 秘钥必须为18字节
        String key = "dragon19dragon19";
        String encrypt = SymmetricCipherUtils.encrypt(content, key,
                SymmetricCipherUtils.Transformation.AES_ECB_PKCS5Padding);
        System.out.printf("%s 加密后: %s\n", content, encrypt);

        String decrypt = SymmetricCipherUtils.decrypt(encrypt, key,
                SymmetricCipherUtils.Transformation.AES_ECB_PKCS5Padding);
        System.out.printf("%s 解密后: %s\n", encrypt, decrypt);

    }
}

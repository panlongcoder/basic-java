package cn.basic.crypto.digest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author dragon
 * @date 2021/6/25
 */
public class MessageDigestTest {


    /**
     * MessageDigest.getInstance()是工厂方法而不是单例,因此附加了大量的开销
     * 所以最好重用并避免MessageDigest,getInstance再次调用的开销
     * <p>
     * 只需要调用reset()方法,可以清除所有以前的设置
     *
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void restTest() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("md5");
        String data = "dragon";
        messageDigest.update(data.getBytes(StandardCharsets.UTF_8));

        byte[] digest = messageDigest.digest();
        String digestStr = Hex.encodeHexString(digest);

        byte[] digest1 = messageDigest.digest();
        String digestStr2 = Hex.encodeHexString(digest1);

        assertNotEquals(true, digestStr.equals(digestStr2));

    }

    /**
     * BouncyCastleProvider 算法提供者
     *
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void providerTest() throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("md5", new BouncyCastleProvider());
        byte[] digest = md5.digest("hello".getBytes(StandardCharsets.UTF_8));
        System.out.println(Base64.encodeBase64String(digest));
    }

    /**
     * clone()方法
     *
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void cloneTest() throws NoSuchAlgorithmException, CloneNotSupportedException {
        MessageDigest messageDigest = MessageDigest.getInstance("md5");
        byte[] digest = messageDigest.digest("dragon".getBytes());

        MessageDigest messageDigest1 = (MessageDigest) messageDigest.clone();
        byte[] digest1 = messageDigest1.digest("dragon".getBytes());

        assertEquals(true, Arrays.equals(digest, digest1));

    }

    /**
     * 加盐测试
     *
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void addSaltTest() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("md5");
        byte[] data = "dragon".getBytes(StandardCharsets.UTF_8);
        byte[] salt = "good".getBytes(StandardCharsets.UTF_8);
        int saltPosition = 4;
        messageDigest.update(data, 0, saltPosition);
        messageDigest.update(salt);
        messageDigest.update(data, saltPosition, data.length - saltPosition);

        byte[] digest = messageDigest.digest();

        messageDigest.reset();

        byte[] newData = new byte[data.length + salt.length];
        System.arraycopy(data, 0, newData, 0, saltPosition);
        System.arraycopy(salt, 0, newData, saltPosition, salt.length);
        System.arraycopy(data, saltPosition, newData, saltPosition + salt.length, data.length - saltPosition);
        byte[] digest1 = messageDigest.digest();


        assertNotEquals(true, Arrays.equals(digest, digest1));

    }

    /**
     * 散列次数
     *
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void digestCountTest() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("md5");
        int digestCount = 5;

        byte[] data = "hello world".getBytes(StandardCharsets.UTF_8);

        messageDigest.reset();

        for (int i = 0; i < digestCount; i++) {
            data = messageDigest.digest(data);
            messageDigest.reset();
        }

        System.out.println(Base64.encodeBase64String(data));
    }


    /**
     * 测试 一次hash
     */
    @Test
    public void digestTest() {
        Digest digest = new Digest(DigestAlgorithm.MD5);
        String s = digest.digestHex("dragon".getBytes(StandardCharsets.UTF_8));

        MD5Digest md5Digest = new MD5Digest();

        byte[] bytes = "dragon".getBytes(StandardCharsets.UTF_8);
        md5Digest.update(bytes, 0, bytes.length);

        byte[] result = new byte[16];
        md5Digest.doFinal(result, 0);

        String str = Hex.encodeHexString(result);

        assertEquals(true, str.equals(s));

    }

    /**
     * 测试 加盐 + 多次hash
     */
    @Test
    public void digestTest2() {
        Digest digest = new Digest(DigestAlgorithm.MD5);
        digest.setSalt("good".getBytes(StandardCharsets.UTF_8));
        digest.setSaltPosition(4);
        digest.setHashCount(5);

        String s = digest.digestHex("panlong".getBytes(StandardCharsets.UTF_8));
        System.out.println(s);
    }


}

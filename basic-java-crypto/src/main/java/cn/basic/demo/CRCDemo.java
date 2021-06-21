package cn.basic.demo;


import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;

/**
 * CRC（Cyclic Redundancy Check，循环冗余校验）是可以根据数据产生简短固定位数的一种散列函数，主要用来检测或校验数据传输/保存后出现的错误。
 * 生成的散列值在传输或储存之前计算出来并且附加到数据后面。在使用数据之前，对数据的完整性做校验。
 * 一般来说，循环冗余校验的值都是32位的二进制数，以8位十六进制字符串形式表示
 * 。它是一类重要的线性分组码，编码和解码方法简单，检错和纠错能力强，在通信领域广泛地用于实现差错控制。
 *
 * @author dragon
 * @date 2021/6/15
 */
public class CRCDemo {

    public static void main(String[] args) {
        String content = "测试crc32";
        CRC32 crc32 = new CRC32();

        crc32.update(content.getBytes(StandardCharsets.UTF_8));

        long crc32Value = crc32.getValue();


    }
}

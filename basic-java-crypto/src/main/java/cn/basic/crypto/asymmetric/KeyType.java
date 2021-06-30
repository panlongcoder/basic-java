package cn.basic.crypto.asymmetric;

import javax.crypto.Cipher;

/**
 * 秘钥类型
 *
 * @author dragon
 * @date 2021/6/30
 */
public enum KeyType {

    PUBLIC_KEY(Cipher.PUBLIC_KEY),

    PRIVATE_KEY(Cipher.PRIVATE_KEY),

    SECRET_KEY(Cipher.SECRET_KEY);

    private int type;

    KeyType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}

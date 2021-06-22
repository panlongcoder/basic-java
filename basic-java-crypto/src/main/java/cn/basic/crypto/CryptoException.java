package cn.basic.crypto;

/**
 * 加解密 异常
 *
 * @author dragon
 * @date 2021/6/18
 */
public class CryptoException extends RuntimeException {
    private static final long serialVersionUID = -8096663231051751463L;

    public CryptoException() {
    }


    public CryptoException(Throwable throwable) {
        super(throwable);
    }

    public CryptoException(String message) {
        super(message);
    }

    public CryptoException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CryptoException(String messageTemplate, Object... params) {
        super(String.format(messageTemplate, params));
    }

    public CryptoException(Throwable throwable, String messageTemplate, Object... params) {
        super(String.format(messageTemplate, params), throwable);
    }
}

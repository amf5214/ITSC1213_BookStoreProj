package fraley.austin.BookStore.Security;

import java.security.SecureRandom;

public final class SaltGenerator {

    private static final String symbols = "ABCDEFGJKLMNPRSTUVWXYZ0123456789";

    private final SecureRandom random = new SecureRandom();

    private final char[] buf;

    public SaltGenerator() {
        buf = new char[16];
    }

    public SaltGenerator(int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1: " + length);
        buf = new char[length];
    }

    public String nextSalt() {
        for (int idx = 0; idx < buf.length; ++idx) {
            buf[idx] = symbols.charAt(random.nextInt(symbols.length()));
        }
        buf[0] = '$';
        buf[1] = '6';
        buf[2] = '$';

        return new String(buf);

    }
}

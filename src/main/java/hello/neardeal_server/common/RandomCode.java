package hello.neardeal_server.common;

import java.security.SecureRandom;

public final class RandomCode {
    private static final SecureRandom RND = new SecureRandom();
    // 0/O, 1/l/I 같은 혼동 문자 제외
    private static final char[] CLEAR_ALPHANUM =
            "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz23456789".toCharArray();

    public static String clearAlphaNum(int len) {
        char[] out = new char[len];
        for (int i = 0; i < len; i++) {
            out[i] = CLEAR_ALPHANUM[RND.nextInt(CLEAR_ALPHANUM.length)];
        }
        return new String(out);
    }

    public static String clearAlphaNum4() {
        return clearAlphaNum(4);
    }

    public static String numeric(int len) {
        char[] digits = "0123456789".toCharArray();
        char[] out = new char[len];
        for (int i = 0; i < len; i++) {
            out[i] = digits[RND.nextInt(digits.length)];
        }
        return new String(out);
    }
}

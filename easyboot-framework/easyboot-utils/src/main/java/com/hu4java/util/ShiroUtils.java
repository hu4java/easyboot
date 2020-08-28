package com.hu4java.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.Objects;

/**
 * @author chenzhenhu
 */
public class ShiroUtils {

    public static final String ALGORITHM_NAME = Sha256Hash.ALGORITHM_NAME;
    public static final int MAX_ITERATIONS = 1024;
    public static final boolean USE_HEX = false;


    public static <T> T currentLogin() {
        if (isLogin()) {
            return (T) SecurityUtils.getSubject().getPrincipal();
        }
        return null;
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().isAuthenticated();
    }

    public static void logout() {
        if (isLogin()) {
            SecurityUtils.getSubject().logout();
        }
    }

    public static String encode(String source) {
        return encode(source, null);
    }

    public static String encode(String source, String salt) {
        SimpleHash simpleHash = new SimpleHash( ALGORITHM_NAME, source, salt, MAX_ITERATIONS);
        if (USE_HEX) {
            return simpleHash.toHex();
        }
        return simpleHash.toBase64();
    }

    public static boolean match(String source, String encoded) {
        return match(source, encoded, null);
    }

    public static boolean match(String source, String encoded, String salt) {
        SimpleHash simpleHash = new SimpleHash(ALGORITHM_NAME, source, salt, MAX_ITERATIONS);
        if (USE_HEX) {
            return Objects.equals(simpleHash.toHex(), encoded);
        } else {
            return Objects.equals(simpleHash.toBase64(), encoded);
        }
    }
}

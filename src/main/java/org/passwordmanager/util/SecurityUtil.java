package org.passwordmanager.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte bytes : hash) {
                hexString.append(String.format("%02x", bytes));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException exception) {
            throw new RuntimeException("SHA-265 unavailable...", exception);
        }
    }

}

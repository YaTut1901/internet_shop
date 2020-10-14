package main.internet.shop.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashUtil {
    public static final String ENCODING_ALGORITHM = "SHA-512";
    public static final String FORMAT_PATTERN = "%02x";
    public static final int SALT_SIZE = 16;

    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_SIZE];
        random.nextBytes(salt);
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) {
        StringBuilder hashedPassword = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ENCODING_ALGORITHM);
            messageDigest.update(salt);
            byte[] digest = messageDigest.digest(password.getBytes());
            for (byte b : digest) {
                hashedPassword.append(String.format(FORMAT_PATTERN, b));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No such algorithm!");
        }
        return hashedPassword.toString();
    }
}

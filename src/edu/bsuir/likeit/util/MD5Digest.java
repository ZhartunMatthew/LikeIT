package edu.bsuir.likeit.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Digest {
    private static final String ALGORITHM = "SHA-1";
    private static final String CHARSET_NAME = "utf8";
    private static final String STRING_EMPTY = "";
    private static final Logger LOG = LogManager.getLogger(MD5Digest.class);

    public static String encrypt(String input) {
        MessageDigest messageDigest;
        byte bytesEncoded[] = null;
        try {
            messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.update(input.getBytes(CHARSET_NAME));
            bytesEncoded = messageDigest.digest();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            LOG.warn(e);
        }
        if (bytesEncoded != null) {
            BigInteger bigInt = new BigInteger(1, bytesEncoded);
            return bigInt.toString(16);
        } else {
            return STRING_EMPTY;
        }
    }
}

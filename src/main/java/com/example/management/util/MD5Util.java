package com.example.management.util;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String Md5(String str) {
        MessageDigest md= null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] md5bytes = md.digest(str.getBytes());
        return new BigInteger(1, md5bytes).toString(16);
    }
}

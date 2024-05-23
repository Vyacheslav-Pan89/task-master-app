package com.taskmaster.taskmasterapp.security;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class PasswordHashingUtil {

    public String hashPassword(String inputPassword) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.update(inputPassword.getBytes());
        byte[] digest = md.digest();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            stringBuilder.append(String.format("%02x", digest[i]));
        }
        return stringBuilder.toString();
    }
}

package com.taskmaster.authentication.security;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class PasswordHashingUtil {

    public String hashPassword(String inputPassword) {

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            //TODO: Create your own custom exception which extends RunTimeException. It's Purpose to catch it and handle in future using ControllerAdvice
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
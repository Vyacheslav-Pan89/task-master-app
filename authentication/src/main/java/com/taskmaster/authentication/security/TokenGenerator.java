package com.taskmaster.authentication.security;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenGenerator {
    public String generateToken() {
        return UUID.randomUUID().toString();
    }
}

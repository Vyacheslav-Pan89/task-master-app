package com.taskmaster.service.security;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenGenerator {
    public String generateToken() {
        return UUID.randomUUID().toString();
    }
}

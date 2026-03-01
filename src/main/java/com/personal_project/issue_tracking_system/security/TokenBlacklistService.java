package com.personal_project.issue_tracking_system.security;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.personal_project.issue_tracking_system.entities.TokenBlacklistEntity;
import com.personal_project.issue_tracking_system.repositories.TokenBlacklistRepository;

@Service
public class TokenBlacklistService {

    private final TokenBlacklistRepository repository;

    public TokenBlacklistService(TokenBlacklistRepository repository) {
        this.repository = repository;
    }

    public void blacklistToken(String token, Duration expirationDuration) {

        TokenBlacklistEntity entity = new TokenBlacklistEntity();
        entity.setToken(token);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setExpiredAt(LocalDateTime.now().plus(expirationDuration));

        repository.save(entity);
    }

    public boolean isBlacklisted(String token) {
        return repository.existsByToken(token);
    }
    
}
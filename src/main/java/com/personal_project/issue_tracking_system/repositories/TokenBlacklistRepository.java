package com.personal_project.issue_tracking_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal_project.issue_tracking_system.entities.TokenBlacklistEntity;

public interface TokenBlacklistRepository extends JpaRepository<TokenBlacklistEntity, Long> {

    boolean existsByToken(String token);
}

package com.mini3team.boo_market.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {
    Optional<EmailVerification> findTopByEmailOrderByCreatedAtDesc(String email);
    Optional<EmailVerification> findTopByIsVerifiedTrueOrderByCreatedAtDesc();
}

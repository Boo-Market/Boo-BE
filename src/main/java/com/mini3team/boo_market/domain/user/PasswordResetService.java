package com.mini3team.boo_market.domain.user;

import com.mini3team.boo_market.common.exception.ApiException;
import com.mini3team.boo_market.dto.request.PasswordEmailSendRequest;
import com.mini3team.boo_market.dto.request.PasswordResetRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class PasswordResetService {
    private final UserRepository userRepository;
    private final EmailVerificationRepository emailVerificationRepository;
    private final PasswordEncoder passwordEncoder;

    public void sendPasswordResetEmail(PasswordEmailSendRequest request) {
        if (!userRepository.existsByEmail(request.email())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "email", "가입되지 않은 이메일입니다.");
        }
        emailVerificationRepository.save(new EmailVerification(request.email(), createCode()));
    }

    public void resetPassword(PasswordResetRequest request) {
        EmailVerification verification = emailVerificationRepository.findTopByIsVerifiedTrueOrderByCreatedAtDesc()
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "verification_code", "이메일 인증이 필요합니다."));

        if (verification.isExpired()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "verification_code", "인증번호가 만료되었습니다.");
        }

        User user = userRepository.findByEmail(verification.getEmail())
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "email", "가입되지 않은 이메일입니다."));

        user.update(null, null, passwordEncoder.encode(request.newPassword()));
    }

    private String createCode() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }
}

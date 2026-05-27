package com.mini3team.boo_market.domain.user;

import com.mini3team.boo_market.common.exception.ApiException;
import com.mini3team.boo_market.dto.request.EmailVerifyRequest;
import com.mini3team.boo_market.dto.request.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class SignupService {
    private final UserRepository userRepository;
    private final MajorRepository majorRepository;
    private final EmailVerificationRepository emailVerificationRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    public void signup(SignupRequest request) {
        if (!request.email().endsWith("@hufs.ac.kr")) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "email", "한국외대 글로벌캠퍼스 이메일(@hufs.ac.kr)만 가입 가능합니다.");
        }
        if (userRepository.existsByEmail(request.email())) {
            throw new ApiException(HttpStatus.CONFLICT, "email", "이미 사용 중인 이메일입니다.");
        }
        if (!request.password().equals(request.password2())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "password", "비밀번호가 일치하지 않습니다.");
        }
        if (userRepository.existsByNickname(request.nickname())) {
            throw new ApiException(HttpStatus.CONFLICT, "nickname", "이미 사용 중인 닉네임입니다.");
        }
        if (!Boolean.TRUE.equals(request.isAgreed())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "is_agreed", "개인정보 처리방침에 동의해야 회원가입이 가능합니다.");
        }

        EmailVerification verification = emailVerificationRepository
                .findTopByEmailOrderByCreatedAtDesc(request.email())
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "email", "이메일 인증이 필요합니다."));
        if (!Boolean.TRUE.equals(verification.getIsVerified())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "email", "이메일 인증이 완료되지 않았습니다.");
        }

        Major major = majorRepository.findById(request.majorId())
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "major_id", "존재하지 않는 전공입니다."));

        userRepository.save(User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .name(request.name())
                .nickname(request.nickname())
                .major(major)
                .isAgreed(request.isAgreed())
                .build());
    }

    public void sendEmail(String email) {
        if (!email.endsWith("@hufs.ac.kr")) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "email", "한국외대 글로벌캠퍼스 이메일(@hufs.ac.kr)만 가입 가능합니다.");
        }
        String code = createCode();
        emailVerificationRepository.save(new EmailVerification(email, code));
        mailService.sendVerificationEmail(email, code);
    }

    public void verifyEmail(EmailVerifyRequest request) {
        EmailVerification verification = emailVerificationRepository.findTopByEmailOrderByCreatedAtDesc(request.email())
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "verification_code", "인증번호가 일치하지 않습니다."));

        if (verification.isExpired() || !verification.getVerificationCode().equals(request.verificationCode())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "verification_code", "인증번호가 일치하지 않습니다.");
        }

        verification.verify();
    }

    private String createCode() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }
}

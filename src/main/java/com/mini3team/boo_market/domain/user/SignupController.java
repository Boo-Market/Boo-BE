package com.mini3team.boo_market.domain.user;

import com.mini3team.boo_market.common.response.MessageResponse;
import com.mini3team.boo_market.dto.request.EmailVerifyRequest;
import com.mini3team.boo_market.dto.request.SignupRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class SignupController {
    private final SignupService signupService;

    @PostMapping("/signup")
    public MessageResponse signup(@Valid @RequestBody SignupRequest request) {
        signupService.signup(request);
        return new MessageResponse("회원가입이 완료되었습니다.");
    }

    @GetMapping("/email/send")
    public MessageResponse sendEmail(@RequestParam String email) {
        signupService.sendEmail(email);
        return new MessageResponse("인증 메일을 발송하였습니다.");
    }

    @PostMapping("/email/verify")
    public MessageResponse verifyEmail(@RequestBody EmailVerifyRequest request) {
        signupService.verifyEmail(request);
        return new MessageResponse("이메일 인증이 완료되었습니다.");
    }
}

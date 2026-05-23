package com.mini3team.boo_market.domain.user;

import com.mini3team.boo_market.common.response.MessageResponse;
import com.mini3team.boo_market.dto.request.PasswordEmailSendRequest;
import com.mini3team.boo_market.dto.request.PasswordResetRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class PasswordResetController {
    private final PasswordResetService passwordResetService;

    @PostMapping("/password/email/send")
    public MessageResponse sendPasswordResetEmail(@Valid @RequestBody PasswordEmailSendRequest request) {
        passwordResetService.sendPasswordResetEmail(request);
        return new MessageResponse("인증 메일을 발송하였습니다.");
    }

    @PatchMapping("/password")
    public MessageResponse resetPassword(@Valid @RequestBody PasswordResetRequest request) {
        passwordResetService.resetPassword(request);
        return new MessageResponse("비밀번호 변경이 완료되었습니다.");
    }
}

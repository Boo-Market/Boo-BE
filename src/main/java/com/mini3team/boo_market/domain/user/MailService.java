package com.mini3team.boo_market.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;

    public void sendVerificationEmail(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("[부마켓] 이메일 인증 코드");
        message.setText("인증 코드: " + code + "\n\n5분 내에 입력해주세요.");
        mailSender.send(message);
    }
}

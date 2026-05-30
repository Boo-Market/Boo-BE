package com.mini3team.boo_market.domain.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class MailService {

    private final RestClient restClient;

    public MailService(@Value("${sendgrid.api-key}") String apiKey) {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.sendgrid.com")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

    public void sendVerificationEmail(String to, String code) {
        String body = """
                {
                  "personalizations": [{"to": [{"email": "%s"}]}],
                  "from": {"email": "boomarket03@gmail.com", "name": "부마켓"},
                  "subject": "[부마켓] 이메일 인증 코드",
                  "content": [{"type": "text/plain", "value": "인증 코드: %s\\n\\n5분 내에 입력해주세요."}]
                }
                """.formatted(to, code);

        restClient.post()
                .uri("/v3/mail/send")
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .toBodilessEntity();
    }
}

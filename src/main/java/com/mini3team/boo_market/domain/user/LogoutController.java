package com.mini3team.boo_market.domain.user;

import com.mini3team.boo_market.common.response.MessageResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LogoutController {
    @PostMapping("/logout")
    public MessageResponse logout() {
        return new MessageResponse("성공적으로 로그아웃하였습니다.");
    }
}

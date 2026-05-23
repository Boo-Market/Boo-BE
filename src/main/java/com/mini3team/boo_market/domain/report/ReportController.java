package com.mini3team.boo_market.domain.report;

import com.mini3team.boo_market.common.exception.ApiException;
import com.mini3team.boo_market.common.response.MessageResponse;
import com.mini3team.boo_market.domain.user.UserService;
import com.mini3team.boo_market.dto.request.ReportRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
public class ReportController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse report(HttpServletRequest request, @Valid @RequestBody ReportRequest reportRequest) {
        userService.report(requireUserId(request), reportRequest);
        return new MessageResponse("신고가 완료되었습니다.");
    }

    private Long requireUserId(HttpServletRequest request) {
        Object userId = request.getAttribute("userId");
        if (userId == null) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "authorization", "로그인이 필요합니다.");
        }
        return (Long) userId;
    }
}

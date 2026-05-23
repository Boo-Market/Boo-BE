package com.mini3team.boo_market.domain.user;

import com.mini3team.boo_market.common.exception.ApiException;
import com.mini3team.boo_market.common.response.DataResponse;
import com.mini3team.boo_market.common.response.MessageResponse;
import com.mini3team.boo_market.dto.request.UserUpdateRequest;
import com.mini3team.boo_market.dto.response.MyGoodsResponse;
import com.mini3team.boo_market.dto.response.MyPageResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public DataResponse<MyPageResponse> getMyPage(HttpServletRequest request) {
        return new DataResponse<>(userService.getMyPage(requireUserId(request)));
    }

    @PutMapping("/update")
    public MessageResponse updateMe(HttpServletRequest request, @RequestBody UserUpdateRequest updateRequest) {
        userService.updateMe(requireUserId(request), updateRequest);
        return new MessageResponse("회원정보가 수정되었습니다.");
    }

    @GetMapping("/me/goods")
    public DataResponse<List<MyGoodsResponse>> getMyGoods(HttpServletRequest request) {
        return new DataResponse<>(userService.getMyGoods(requireUserId(request)));
    }

    @DeleteMapping("/delete")
    public MessageResponse withdraw(HttpServletRequest request) {
        userService.withdraw(requireUserId(request));
        return new MessageResponse("회원 탈퇴가 완료되었습니다.");
    }

    private Long requireUserId(HttpServletRequest request) {
        Object userId = request.getAttribute("userId");
        if (userId == null) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "authorization", "로그인이 필요합니다.");
        }
        return (Long) userId;
    }
}

package com.mini3team.boo_market.domain.goods;

import com.mini3team.boo_market.common.exception.ApiException;
import com.mini3team.boo_market.common.response.MessageResponse;
import com.mini3team.boo_market.domain.user.UserService;
import com.mini3team.boo_market.dto.request.GoodsUpdateRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/goods")
public class GoodsController {
    private final UserService userService;

    @PatchMapping("/{goodsId}")
    public MessageResponse updateGoods(HttpServletRequest request,
                                       @PathVariable Long goodsId,
                                       @RequestBody GoodsUpdateRequest updateRequest) {
        userService.updateGoods(requireUserId(request), goodsId, updateRequest);
        return new MessageResponse("게시글이 성공적으로 수정되었습니다.");
    }

    @DeleteMapping("/{goodsId}")
    public MessageResponse deleteGoods(HttpServletRequest request, @PathVariable Long goodsId) {
        userService.deleteGoods(requireUserId(request), goodsId);
        return new MessageResponse("삭제되었습니다.");
    }

    private Long requireUserId(HttpServletRequest request) {
        Object userId = request.getAttribute("userId");
        if (userId == null) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "authorization", "로그인이 필요합니다.");
        }
        return (Long) userId;
    }
}

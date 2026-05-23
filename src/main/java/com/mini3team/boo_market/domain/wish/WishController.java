package com.mini3team.boo_market.domain.wish;

import com.mini3team.boo_market.dto.response.WishListResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;

    @PostMapping("/api/wishes/{postId}")
    public ResponseEntity<?> addWish(@PathVariable Long postId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        wishService.addWish(postId, userId);
        return ResponseEntity.ok(Map.of("success", true, "message", "관심상품으로 등록되었습니다."));
    }

    @DeleteMapping("/api/wishes/{postId}")
    public ResponseEntity<?> removeWish(@PathVariable Long postId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        wishService.removeWish(postId, userId);
        return ResponseEntity.ok(Map.of("success", true, "message", "관심상품이 해제되었습니다."));
    }

    @GetMapping("/api/wish_lists")
    public ResponseEntity<?> getWishList(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<WishListResponse> response = wishService.getWishList(userId);
        return ResponseEntity.ok(Map.of("success", true, "data", response));
    }
}

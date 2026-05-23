package com.mini3team.boo_market.domain.wish;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import com.mini3team.boo_market.dto.response.WishListResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;

    @PostMapping("/api/wishes/{postId}")
    public ResponseEntity<?> addWish(@PathVariable Long postId) {
        wishService.addWish(postId);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "관심상품으로 등록되었습니다."
        ));
    }
    @DeleteMapping("/api/wishes/{postId}")
    public ResponseEntity<?> removeWish(@PathVariable Long postId) {
        wishService.removeWish(postId);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "관심상품이 해제되었습니다."
        ));
    }
    @GetMapping("/api/wish_lists")
    public ResponseEntity<?> getWishList() {
        List<WishListResponse> response = wishService.getWishList();
        return ResponseEntity.ok(Map.of(
                "success", true,
                "data", response
        ));
    }
}

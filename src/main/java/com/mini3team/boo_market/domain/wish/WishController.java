package com.mini3team.boo_market.domain.wish;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

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
}

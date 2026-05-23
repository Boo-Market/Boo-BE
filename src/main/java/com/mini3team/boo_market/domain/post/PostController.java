package com.mini3team.boo_market.domain.post;

import com.mini3team.boo_market.dto.request.PostCreateRequest;
import com.mini3team.boo_market.dto.response.PostDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/api/posts")
    public ResponseEntity<?> createPost(@RequestBody PostCreateRequest request) {
        Long postId = postService.createPost(request);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "data", Map.of("postId", postId),
                "message", "게시글이 등록되었습니다."
        ));
    }

    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Long postId) {
        PostDetailResponse response = postService.getPost(postId);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "data", response
        ));
    }
}

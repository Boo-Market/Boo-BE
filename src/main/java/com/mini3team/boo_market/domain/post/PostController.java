package com.mini3team.boo_market.domain.post;

import com.mini3team.boo_market.dto.request.PostCreateRequest;
import com.mini3team.boo_market.dto.response.PostDetailResponse;
import com.mini3team.boo_market.dto.response.PostListResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/api/posts")
    public ResponseEntity<?> createPost(@RequestBody PostCreateRequest request, HttpServletRequest httpRequest) {
        Long userId = (Long) httpRequest.getAttribute("userId");
        Long postId = postService.createPost(request, userId);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "data", Map.of("postId", postId),
                "message", "게시글이 등록되었습니다."
        ));
    }

    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Long postId, HttpServletRequest httpRequest) {
        Long userId = (Long) httpRequest.getAttribute("userId");
        PostDetailResponse response = postService.getPost(postId, userId);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "data", response
        ));
    }

    @GetMapping("/api/posts")
    public ResponseEntity<?> getPostList(
            @RequestParam(required = false, defaultValue = "전체") String category,
            @RequestParam(required = false, defaultValue = "latest") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Integer majorId) {

        Sort sorting;
        if (sort.equals("low_price")) {
            sorting = Sort.by("price").ascending();
        } else {
            sorting = Sort.by("id").descending();
        }

        Pageable pageable = PageRequest.of(page, size, sorting);
        Page<PostListResponse> response = postService.getPostList(category, sort, majorId, pageable);

        return ResponseEntity.ok(Map.of(
                "success", true,
                "data", Map.of("posts", response.getContent())
        ));
    }
}

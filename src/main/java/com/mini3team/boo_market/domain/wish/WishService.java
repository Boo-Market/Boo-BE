package com.mini3team.boo_market.domain.wish;

import com.mini3team.boo_market.domain.post.Post;
import com.mini3team.boo_market.domain.post.PostRepository;
import com.mini3team.boo_market.dto.response.WishListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class WishService {

    private final WishRepository wishRepository;
    private final PostRepository postRepository;

    public void addWish(Long postId) {
        Long userId = 1L; // 임시 userId (나중에 로그인 기능 붙이면 교체)

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        if (wishRepository.existsByUserIdAndPostId(userId, postId)) {
            throw new IllegalArgumentException("이미 관심상품으로 등록된 게시글입니다.");
        }

        Wish wish = Wish.builder()
                .userId(userId)
                .post(post)
                .build();

        wishRepository.save(wish);
    }
    public void removeWish(Long postId) {
        Long userId = 1L; // 임시 userId

        Wish wish = wishRepository.findByUserIdAndPostId(userId, postId)
                .orElseThrow(() -> new IllegalArgumentException("관심상품으로 등록되지 않은 게시글입니다."));

        wishRepository.delete(wish);
    }
    @Transactional(readOnly = true)
    public List<WishListResponse> getWishList() {
        Long userId = 1L; // 임시 userId

        return wishRepository.findAllByUserId(userId)
                .stream()
                .map(WishListResponse::new)
                .collect(Collectors.toList());
    }
}

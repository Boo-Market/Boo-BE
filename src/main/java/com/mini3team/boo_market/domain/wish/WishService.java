package com.mini3team.boo_market.domain.wish;

import com.mini3team.boo_market.domain.post.Post;
import com.mini3team.boo_market.domain.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}

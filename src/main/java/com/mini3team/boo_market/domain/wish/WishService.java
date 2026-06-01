package com.mini3team.boo_market.domain.wish;

import com.mini3team.boo_market.common.exception.ApiException;
import com.mini3team.boo_market.domain.post.Post;
import com.mini3team.boo_market.domain.post.PostRepository;
import com.mini3team.boo_market.dto.response.WishListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WishService {

    private final WishRepository wishRepository;
    private final PostRepository postRepository;

    public void addWish(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "post", "존재하지 않는 게시글입니다."));

        if (userId.equals(post.getAuthorId())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "wish", "본인 게시글에는 찜할 수 없습니다.");
        }

        if (wishRepository.existsByUserIdAndPostId(userId, postId)) {
            throw new ApiException(HttpStatus.CONFLICT, "wish", "이미 관심상품으로 등록된 게시글입니다.");
        }

        wishRepository.save(Wish.builder().userId(userId).post(post).build());
        post.incrementWishCount();
    }

    public void removeWish(Long postId, Long userId) {
        Wish wish = wishRepository.findByUserIdAndPostId(userId, postId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "wish", "관심상품으로 등록되지 않은 게시글입니다."));
        wishRepository.delete(wish);
        wish.getPost().decrementWishCount();
    }

    @Transactional(readOnly = true)
    public List<WishListResponse> getWishList(Long userId) {
        return wishRepository.findAllByUserId(userId).stream()
                .map(WishListResponse::new)
                .toList();
    }
}

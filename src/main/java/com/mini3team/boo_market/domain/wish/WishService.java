package com.mini3team.boo_market.domain.wish;

import com.mini3team.boo_market.common.exception.ApiException;
import com.mini3team.boo_market.domain.post.Post;
import com.mini3team.boo_market.domain.post.PostRepository;
import com.mini3team.boo_market.domain.user.UserRepository;
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
    private final UserRepository userRepository;

    public void addWish(Long postId, Long buyerId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        if (buyerId.equals(post.getAuthorId())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "chat", "본인 게시글에는 채팅할 수 없습니다.");
        }

        if (wishRepository.existsByUserIdAndPostId(buyerId, postId)) {
            throw new IllegalArgumentException("이미 관심상품으로 등록된 게시글입니다.");
        }

        wishRepository.save(Wish.builder().userId(buyerId).post(post).build());
    }

    public void removeWish(Long postId, Long userId) {
        Wish wish = wishRepository.findByUserIdAndPostId(userId, postId)
                .orElseThrow(() -> new IllegalArgumentException("관심상품으로 등록되지 않은 게시글입니다."));
        wishRepository.delete(wish);
    }

    @Transactional(readOnly = true)
    public List<WishListResponse> getWishList(Long userId) {
        return wishRepository.findAllByUserId(userId).stream()
                .map(WishListResponse::new)
                .toList();
    }
}

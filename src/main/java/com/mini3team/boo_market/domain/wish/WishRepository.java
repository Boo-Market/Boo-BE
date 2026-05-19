package com.mini3team.boo_market.domain.wish;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, Long> {
    boolean existsByUserIdAndPostId(Long userId, Long postId);
}

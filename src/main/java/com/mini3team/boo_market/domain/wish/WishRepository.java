package com.mini3team.boo_market.domain.wish;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish, Long> {
    boolean existsByUserIdAndPostId(Long userId, Long postId);
    Optional<Wish> findByUserIdAndPostId(Long userId, Long postId);
}

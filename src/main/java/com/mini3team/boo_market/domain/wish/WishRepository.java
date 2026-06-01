package com.mini3team.boo_market.domain.wish;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish, Long> {
    boolean existsByUserIdAndPostId(Long userId, Long postId);
    Optional<Wish> findByUserIdAndPostId(Long userId, Long postId);
    List<Wish> findAllByUserId(Long userId);
    long countByUserId(Long userId);
    long countByPostId(Long postId);
}

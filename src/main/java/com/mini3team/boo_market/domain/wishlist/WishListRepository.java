package com.mini3team.boo_market.domain.wishlist;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    long countByUserId(Long userId);
}

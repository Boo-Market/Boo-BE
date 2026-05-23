package com.mini3team.boo_market.domain.goods;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GoodsRepository extends JpaRepository<Goods, Long> {
    long countBySellerId(Long sellerId);
    long countBySellerIdAndStatus(Long sellerId, String status);
    List<Goods> findBySellerIdOrderByCreatedAtDesc(Long sellerId);
    Optional<Goods> findByIdAndSellerId(Long id, Long sellerId);
}

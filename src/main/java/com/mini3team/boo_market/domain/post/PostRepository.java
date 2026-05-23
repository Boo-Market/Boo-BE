package com.mini3team.boo_market.domain.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByCategory_Name(String categoryName, Pageable pageable);
    Page<Post> findAll(Pageable pageable);
}
package com.mini3team.boo_market.domain.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByCategory_Name(String categoryName, Pageable pageable);
    Page<Post> findAll(Pageable pageable);
    Page<Post> findByCategory_NameAndMajor_Id(String categoryName, Integer majorId, Pageable pageable);

    long countByAuthorId(Long authorId);
    long countByAuthorIdAndStatus(Long authorId, String status);
    List<Post> findByAuthorIdOrderByCreatedAtDesc(Long authorId);
    Optional<Post> findByIdAndAuthorId(Long id, Long authorId);
}
package com.mini3team.boo_market.domain.post;

import com.mini3team.boo_market.domain.category.Category;
import com.mini3team.boo_market.domain.category.CategoryRepository;
import com.mini3team.boo_market.dto.request.PostCreateRequest;
import com.mini3team.boo_market.dto.response.PostDetailResponse;
import com.mini3team.boo_market.dto.response.PostListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    public Long createPost(PostCreateRequest request) {
        request.validateRental();

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));

        Post post = Post.builder()
                .title(request.getTitle())
                .category(category)
                .itemCondition(request.getItemCondition())
                .price(request.getPrice())
                .isFree(request.isFree())
                .tradeLocation(request.getTradeLocation())
                .contactMethod(request.getContactMethod())
                .description(request.getDescription())
                .imageUrls(request.getImageUrls())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .lenderName(request.getLenderName())
                .borrowerName(request.getBorrowerName())
                .build();

        return postRepository.save(post).getId();
    }

    public PostDetailResponse getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        post.incrementViewCount();
        return new PostDetailResponse(post);
    }

    @Transactional(readOnly = true)
    public Page<PostListResponse> getPostList(String category, String sort, Integer majorId, Pageable pageable) {
        Page<Post> posts;

        if (category == null || category.equals("전체")) {
            posts = postRepository.findAll(pageable);
        } else if (majorId != null) {
            posts = postRepository.findByCategory_NameAndMajor_Id(category, majorId, pageable);
        } else {
            posts = postRepository.findByCategory_Name(category, pageable);
        }

        return posts.map(PostListResponse::new);
    }
}

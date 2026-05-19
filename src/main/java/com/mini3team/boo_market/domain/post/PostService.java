package com.mini3team.boo_market.domain.post;

import com.mini3team.boo_market.domain.category.Category;
import com.mini3team.boo_market.domain.category.CategoryRepository;
import com.mini3team.boo_market.dto.request.PostCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mini3team.boo_market.dto.response.PostDetailResponse;

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
    @Transactional(readOnly = true)
    public PostDetailResponse getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        return new PostDetailResponse(post);
    }
}

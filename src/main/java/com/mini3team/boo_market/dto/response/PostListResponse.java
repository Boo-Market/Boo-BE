package com.mini3team.boo_market.dto.response;

import com.mini3team.boo_market.domain.post.Post;
import lombok.Getter;

@Getter
public class PostListResponse {

    private Long postId;
    private String title;
    private String category;
    private int price;
    private boolean isFree;
    private String thumbnailUrl;
    private int wishCount;

    public PostListResponse(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.category = post.getCategory().getName();
        this.price = post.getPrice();
        this.isFree = post.isFree();
        this.thumbnailUrl = post.getImageUrls().isEmpty()
                ? null
                : post.getImageUrls().get(0);
        this.wishCount = post.getWishCount();
    }
}

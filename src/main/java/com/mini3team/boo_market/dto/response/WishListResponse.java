package com.mini3team.boo_market.dto.response;

import com.mini3team.boo_market.domain.wish.Wish;
import lombok.Getter;

@Getter
public class WishListResponse {

    private Long postId;
    private String title;
    private int price;
    private boolean isFree;
    private String thumbnailUrl;

    public WishListResponse(Wish wish) {
        this.postId = wish.getPost().getId();
        this.title = wish.getPost().getTitle();
        this.price = wish.getPost().getPrice();
        this.isFree = wish.getPost().isFree();
        this.thumbnailUrl = wish.getPost().getImageUrls().isEmpty()
                ? null
                : wish.getPost().getImageUrls().get(0);
    }
}

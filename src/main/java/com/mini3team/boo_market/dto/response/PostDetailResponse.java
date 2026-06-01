package com.mini3team.boo_market.dto.response;

import com.mini3team.boo_market.domain.post.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostDetailResponse {

    private Long postId;
    private String title;
    private String category;
    private int itemCondition;
    private int price;
    private boolean isFree;
    private String tradeLocation;
    private String contactMethod;
    private String description;
    private List<String> images;
    private Long authorId;
    private boolean isWished;
    private long wishCount;
    private LocalDateTime createdAt;
    private int viewCount;

    // 대여 전용
    private String startDate;
    private String endDate;
    private String lenderName;
    private String borrowerName;

    public PostDetailResponse(Post post, boolean isWished, long wishCount) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.category = post.getCategory().getName();
        this.itemCondition = post.getItemCondition();
        this.price = post.getPrice();
        this.isFree = post.isFree();
        this.tradeLocation = post.getTradeLocation();
        this.contactMethod = post.getContactMethod();
        this.description = post.getDescription();
        this.images = post.getImageUrls();
        this.authorId = post.getAuthorId();
        this.isWished = isWished;
        this.wishCount = wishCount;
        this.createdAt = post.getCreatedAt();
        this.viewCount = post.getViewCount();
        this.startDate = post.getStartDate();
        this.endDate = post.getEndDate();
        this.lenderName = post.getLenderName();
        this.borrowerName = post.getBorrowerName();
    }
}

package com.mini3team.boo_market.domain.post;

import com.mini3team.boo_market.domain.category.Category;
import com.mini3team.boo_market.domain.user.Major;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int itemCondition;
    private int price;
    private boolean isFree;
    private String tradeLocation;
    private String contactMethod;
    private String description;

    @ElementCollection
    private List<String> imageUrls = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private Major major;

    // 대여 전용
    private String startDate;
    private String endDate;
    private String lenderName;
    private String borrowerName;

    private Long authorId;
    private LocalDateTime createdAt;

    @Column(columnDefinition = "VARCHAR(20) DEFAULT 'AVAILABLE'")
    private String status;

    @Column(columnDefinition = "INT DEFAULT 0")
    private int viewCount;

    public void incrementViewCount() {
        this.viewCount++;
    }

    public void update(String title, String status, Integer price, String tradeLocation,
                       String description, Integer itemCondition) {
        if (title != null) this.title = title;
        if (status != null) this.status = status;
        if (price != null) this.price = price;
        if (tradeLocation != null) this.tradeLocation = tradeLocation;
        if (description != null) this.description = description;
        if (itemCondition != null) this.itemCondition = itemCondition;
    }

    @Builder
    public Post(String title, Category category, int itemCondition,
                int price, boolean isFree, String tradeLocation,
                String contactMethod, String description,
                List<String> imageUrls, Long authorId,
                String startDate, String endDate,
                String lenderName, String borrowerName) {
        this.title = title;
        this.category = category;
        this.itemCondition = itemCondition;
        this.price = isFree ? 0 : price;
        this.isFree = isFree;
        this.tradeLocation = tradeLocation;
        this.contactMethod = contactMethod;
        this.description = description;
        this.imageUrls = imageUrls;
        this.authorId = authorId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lenderName = lenderName;
        this.borrowerName = borrowerName;
        this.status = "AVAILABLE";
        this.createdAt = LocalDateTime.now();
    }
}

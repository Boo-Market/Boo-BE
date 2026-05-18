package com.mini3team.boo_market.domain.post;

import com.mini3team.boo_market.domain.category.Category;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

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

    // 대여 전용
    private String startDate;
    private String endDate;
    private String lenderName;
    private String borrowerName;

    @Builder
    public Post(String title, Category category, int itemCondition,
                int price, boolean isFree, String tradeLocation,
                String contactMethod, String description,
                List<String> imageUrls, String startDate, String endDate,
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
        this.startDate = startDate;
        this.endDate = endDate;
        this.lenderName = lenderName;
        this.borrowerName = borrowerName;
    }
}

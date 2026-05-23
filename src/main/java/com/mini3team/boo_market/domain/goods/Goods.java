package com.mini3team.boo_market.domain.goods;

import com.mini3team.boo_market.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Integer price;

    private String rentalPeriod;
    private String place;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "`condition`")
    private Integer condition;

    private LocalDateTime createdAt;
    private Integer viewCount;

    @PrePersist
    void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.viewCount = 0;
    }

    public void update(Integer categoryId, String title, String type, String status, Integer price,
                       String rentalPeriod, String place, String description, Integer condition) {
        if (categoryId != null) this.categoryId = categoryId;
        if (title != null) this.title = title;
        if (type != null) this.type = type;
        if (status != null) this.status = status;
        if (price != null) this.price = price;
        this.rentalPeriod = rentalPeriod;
        if (place != null) this.place = place;
        if (description != null) this.description = description;
        if (condition != null) this.condition = condition;
    }
}

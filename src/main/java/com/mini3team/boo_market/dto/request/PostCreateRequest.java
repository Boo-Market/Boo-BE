package com.mini3team.boo_market.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostCreateRequest {

    private String title;
    private Long categoryId;
    private int itemCondition;
    private int price;
    private boolean isFree;
    private String tradeLocation;
    private String contactMethod;
    private String description;
    private List<String> imageUrls;

    // 대여(categoryId=5) 전용
    private String startDate;
    private String endDate;
    private String lenderName;
    private String borrowerName;

    public void validateRental() {
        if (categoryId == 5) {
            if (startDate == null || endDate == null
                    || lenderName == null || borrowerName == null) {
                throw new IllegalArgumentException("대여 카테고리는 startDate, endDate, lenderName, borrowerName이 필수입니다.");
            }
        }
    }
}

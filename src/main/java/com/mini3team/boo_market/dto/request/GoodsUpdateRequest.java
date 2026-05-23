package com.mini3team.boo_market.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GoodsUpdateRequest(
        @JsonProperty("category_id") Integer categoryId,
        String title,
        String type,
        String status,
        Integer price,
        @JsonProperty("rental_period") String rentalPeriod,
        String place,
        String description,
        Integer condition
) {
}

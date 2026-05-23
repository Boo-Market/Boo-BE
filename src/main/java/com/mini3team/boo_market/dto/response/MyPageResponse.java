package com.mini3team.boo_market.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MyPageResponse(
        String name,
        String nickname,
        MajorInfo major,
        @JsonProperty("sales_post_count") long salesPostCount,
        @JsonProperty("transaction_count") long transactionCount,
        @JsonProperty("wish_count") long wishCount
) {
    public record MajorInfo(Integer id, String name) {
    }
}

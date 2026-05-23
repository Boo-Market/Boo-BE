package com.mini3team.boo_market.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MyGoodsResponse(
        @JsonProperty("goods_id") Long goodsId,
        String title,
        Integer price,
        @JsonProperty("thumbnail_url") String thumbnailUrl
) {
}

package com.mini3team.boo_market.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserUpdateRequest(
        String nickname,
        @JsonProperty("major_id") Integer majorId,
        String password
) {
}

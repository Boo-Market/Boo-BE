package com.mini3team.boo_market.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReportRequest(
        @JsonProperty("target_user_id") @NotNull Long targetUserId,
        @JsonProperty("post_id") Long postId,
        @NotBlank String reason
) {
}

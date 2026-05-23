package com.mini3team.boo_market.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EmailVerifyRequest(
        String email,
        @JsonProperty("verification_code") String verificationCode
) {
}

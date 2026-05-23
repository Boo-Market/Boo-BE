package com.mini3team.boo_market.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SignupRequest(
        @Email String email,
        @NotBlank String password,
        @NotBlank String password2,
        @NotBlank String name,
        @NotBlank String nickname,
        @JsonProperty("major_id") @NotNull Integer majorId,
        @JsonProperty("is_agreed") Boolean isAgreed
) {
}

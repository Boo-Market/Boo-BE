package com.mini3team.boo_market.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record PasswordResetRequest(
        @JsonProperty("new_password") @NotBlank String newPassword
) {
}

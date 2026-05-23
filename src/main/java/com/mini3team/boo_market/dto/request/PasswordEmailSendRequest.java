package com.mini3team.boo_market.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PasswordEmailSendRequest(
        @Email @NotBlank String email
) {
}

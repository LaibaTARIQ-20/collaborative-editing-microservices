package com.user.authentication.user_service.dto.response;

import lombok.Data;

@Data
public class AuthenticationResponseDTO {
    private Long userId;
    private String displayName;
    private String accessToken;
}

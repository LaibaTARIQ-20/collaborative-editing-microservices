package com.user.authentication.user_service.service;

import com.user.authentication.user_service.dto.request.AuthenticationRequestDTO;
import com.user.authentication.user_service.dto.response.AuthenticationResponseDTO;
import com.user.authentication.user_service.exception.GlobleException;

public interface AuthenticationService {
    AuthenticationResponseDTO authenticate(AuthenticationRequestDTO model) throws GlobleException;
    void logout() throws GlobleException;
   void validateToken(String token);
}

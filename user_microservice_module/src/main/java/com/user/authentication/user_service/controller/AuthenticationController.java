// AuthenticationController.java
package com.user.authentication.user_service.controller;

import com.user.authentication.user_service.dto.request.AuthenticationRequestDTO;
import com.user.authentication.user_service.dto.response.BaseResponse;
import com.user.authentication.user_service.dto.response.BaseResponseDTO;
import com.user.authentication.user_service.exception.GlobleException;
import com.user.authentication.user_service.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")
@Slf4j
public class AuthenticationController extends BaseResponse {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<BaseResponseDTO> login(@RequestBody AuthenticationRequestDTO model) throws GlobleException {
        log.info("Authentication request for user: {}", model.getUsername());
        return ResponseEntity.ok(response(HttpStatus.OK, "Authentication successful",
                authenticationService.authenticate(model)));
    }

    @GetMapping("/validate")
    public ResponseEntity<BaseResponseDTO> validateToken(@RequestParam("token") String token) {
        authenticationService.validateToken(token);
        return ResponseEntity.ok(response(HttpStatus.OK, "Token is valid"));
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<BaseResponseDTO> logout(@RequestParam("token") String token) {
        log.info("Logout request for token: {}", token.substring(0, Math.min(token.length(), 10)) + "...");
        // Add logout logic here if needed
        return ResponseEntity.ok(response(HttpStatus.OK, "Successfully logged out"));
    }
}

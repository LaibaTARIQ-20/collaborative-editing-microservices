package com.user.authentication.user_service.controller;

import com.user.authentication.user_service.dto.request.UserRequestDTO;
import com.user.authentication.user_service.dto.response.BaseResponse;
import com.user.authentication.user_service.dto.response.BaseResponseDTO;
import com.user.authentication.user_service.exception.GlobleException;
import com.user.authentication.user_service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/user")
@Slf4j
public class UserController extends BaseResponse {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<BaseResponseDTO> registerUser(@RequestBody UserRequestDTO model) throws GlobleException {
        log.info("User registration request received");
        return ResponseEntity.ok(response(HttpStatus.CREATED, "User registered successfully", userService.saveUser(model)));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<BaseResponseDTO> addUser(@RequestBody UserRequestDTO model) throws GlobleException {
        return ResponseEntity.ok(response(HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase(), userService.saveUser(model)));
    }

    @GetMapping(value = "/profile/{id}")
    public ResponseEntity<BaseResponseDTO> getUserProfile(@PathVariable Long id) throws GlobleException {
        log.info("Get profile request for user ID: {}", id);
        // Using existing saveUser method as placeholder - you can implement getUserById later
        return ResponseEntity.ok(response(HttpStatus.OK, "Profile retrieved", "User profile for ID: " + id));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<BaseResponseDTO> getAllUsers() throws GlobleException {
        log.info("Get all users request for collaboration");
        return ResponseEntity.ok(response(HttpStatus.OK, "Users retrieved", "Available users: user1, user2, user3"));
    }

    @PostMapping(value = "/delete/id/{id}")
    public ResponseEntity<BaseResponseDTO> deleteUser(@PathVariable Long id) throws GlobleException {
        userService.delete(id);
        return ResponseEntity.ok(response(HttpStatus.OK, "Record Deleted"));
    }

    @GetMapping(value = "/health")
    public ResponseEntity<BaseResponseDTO> healthCheck() {
        return ResponseEntity.ok(response(HttpStatus.OK, "User service is healthy"));
    }
}
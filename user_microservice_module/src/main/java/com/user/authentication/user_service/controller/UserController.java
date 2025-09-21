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

@RestController
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
        log.info("User registration request for: {}", model.getUsername());
        return ResponseEntity.ok(response(HttpStatus.CREATED, "User registered successfully", userService.saveUser(model)));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<BaseResponseDTO> addUser(@RequestBody UserRequestDTO model) throws GlobleException {
        return ResponseEntity.ok(response(HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase(), userService.saveUser(model)));
    }

    @GetMapping(value = "/profile/{id}")
    public ResponseEntity<BaseResponseDTO> getUserProfile(@PathVariable Long id) throws GlobleException {
        log.info("Get profile request for user ID: {}", id);
        return ResponseEntity.ok(response(HttpStatus.OK, "Profile retrieved", userService.getUserById(id)));
    }

    @PutMapping(value = "/profile/{id}")
    public ResponseEntity<BaseResponseDTO> updateUserProfile(@PathVariable Long id, @RequestBody UserRequestDTO model) throws GlobleException {
        log.info("Update profile request for user ID: {}", id);
        return ResponseEntity.ok(response(HttpStatus.OK, "Profile updated successfully", userService.updateUser(id, model)));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<BaseResponseDTO> getAllUsers() throws GlobleException {
        log.info("Get all users request for collaboration");
        return ResponseEntity.ok(response(HttpStatus.OK, "Users retrieved", userService.getAllUsers()));
    }

    @PostMapping(value = "/delete/id/{id}")
    public ResponseEntity<BaseResponseDTO> deleteUser(@PathVariable Long id) throws GlobleException {
        userService.delete(id);
        return ResponseEntity.ok(response(HttpStatus.OK, "Record Deleted"));
    }
}
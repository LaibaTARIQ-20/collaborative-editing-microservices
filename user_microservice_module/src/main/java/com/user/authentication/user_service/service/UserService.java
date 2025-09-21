package com.user.authentication.user_service.service;

import com.user.authentication.user_service.dto.request.UserRequestDTO;
import com.user.authentication.user_service.dto.response.UserResponseDTO;
import com.user.authentication.user_service.exception.GlobleException;

import java.util.List;

public interface UserService {


    UserResponseDTO saveUser(UserRequestDTO userRequestDTO) throws GlobleException;
    void delete(long id) throws GlobleException;

    List<UserResponseDTO> findAllUsers() throws GlobleException;





}

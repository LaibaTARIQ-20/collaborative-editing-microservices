package com.user.authentication.user_service;

import com.user.authentication.user_service.dto.request.UserRequestDTO;
import com.user.authentication.user_service.dto.response.UserResponseDTO;
import com.user.authentication.user_service.entity.UserEntity;
import com.user.authentication.user_service.exception.GlobleException;
import com.user.authentication.user_service.mapper.UserMapper;
import com.user.authentication.user_service.repository.UserRepository;
import com.user.authentication.user_service.service.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testSaveUser() throws GlobleException {
        // Arrange
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setEmailAddress("muhammadbilaljanwari@gmail.com");
        userRequestDTO.setPassword("testPassword");

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1L);

        Mockito.when(passwordEncoder.encode(userRequestDTO.getPassword())).thenReturn("encodedPassword");
        Mockito.when(userMapper.addUserMapper(userRequestDTO)).thenReturn(userEntity);
        Mockito.when(userRepository.save(userEntity)).thenReturn(userEntity);
        Mockito.when(userMapper.find(userEntity)).thenReturn(userResponseDTO);

        // Act
        UserResponseDTO savedUser = userService.saveUser(userRequestDTO);

        // Assert
        Assertions.assertNotNull(savedUser);
        Assertions.assertEquals(1L, savedUser.getId());
    }

    @Test
    void testDeleteUser_UserExists() throws GlobleException {
        // Arrange
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));

        // Act
        userService.delete(1L);

        // Assert
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void testFindAllUsers() throws GlobleException {
        // Arrange
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1L);

       Mockito. when(userRepository.findAll()).thenReturn(Collections.singletonList(userEntity));
        Mockito.when(userMapper.find(userEntity)).thenReturn(userResponseDTO);
        List<UserResponseDTO> users = userService.findAllUsers();
        Assertions.assertNotNull(users);
       Assertions.assertEquals(1, users.size());
        Assertions.assertEquals(1L, users.get(0).getId());
    }
}

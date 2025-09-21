package com.user.authentication.user_service.service.serviceImpl;

import com.user.authentication.user_service.dto.request.UserRequestDTO;
import com.user.authentication.user_service.dto.response.UserResponseDTO;
import com.user.authentication.user_service.entity.UserEntity;
import com.user.authentication.user_service.exception.GlobleException;
import com.user.authentication.user_service.mapper.UserMapper;
import com.user.authentication.user_service.repository.UserRepository;
import com.user.authentication.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private   UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  UserMapper userMapper;

    @Override
    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) throws GlobleException {
    userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

       UserEntity userEntity= userRepository.save(userMapper.addUserMapper(userRequestDTO));
        return userMapper.find(userEntity);
    }
    @Override
    public void delete(long id) throws GlobleException {
    Optional<UserEntity> user=userRepository.findById(id);
    if(user.isEmpty())
    {
        throw new GlobleException("User not Present");
    }
        userRepository.deleteById(id);
    }
    @Override
    public List<UserResponseDTO> findAllUsers() throws GlobleException {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(userEntity -> userMapper.find(userEntity))
                .collect(Collectors.toList());
    }
}

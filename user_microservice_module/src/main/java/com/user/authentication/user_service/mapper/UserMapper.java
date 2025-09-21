package com.user.authentication.user_service.mapper;

import com.user.authentication.user_service.dto.request.UserRequestDTO;
import com.user.authentication.user_service.dto.response.UserResponseDTO;
import com.user.authentication.user_service.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class UserMapper {
   private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public  UserEntity addUserMapper(UserRequestDTO userRequestDTO)
    {
       UserEntity userEntity= modelMapper.map(userRequestDTO,UserEntity.class);
       return  userEntity;
    }
    public UserResponseDTO find( UserEntity userEntity)
    {
    UserResponseDTO userResponseDTO= modelMapper.map(userEntity,UserResponseDTO.class);
    return  userResponseDTO;
    }

}

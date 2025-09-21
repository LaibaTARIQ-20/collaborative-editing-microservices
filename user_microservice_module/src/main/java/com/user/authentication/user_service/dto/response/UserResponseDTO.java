package com.user.authentication.user_service.dto.response;

import lombok.Data;

@Data
public class UserResponseDTO {
    private  Long id;
    private String firstName;

    private String lastName;

    private String emailAddress;

    private String alternateEmailAddress;

    private String mobilePhone;

    private  String password;
}

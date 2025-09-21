package com.user.authentication.user_service.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String alternateEmailAddress;
    private String mobilePhone;
    private  String password;
}

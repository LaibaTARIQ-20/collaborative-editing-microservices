package com.user.authentication.user_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "pe_users")
public class UserEntity extends  BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "str_first_name", nullable = false, length = 80)
    private String firstName;

    @Column(name = "str_last_name", nullable = false, length = 80)
    private String lastName;

    @Column(name = "str_email_address", nullable = false, length = 50)
    private String emailAddress;

    @Column(name = "str_alternate_email_address", nullable = true, length = 50)
    private String alternateEmailAddress;

    @Column(name = "str_mobile_phone", nullable = false, length = 20)
    private String mobilePhone;

    @Column (name = "str_password" )
    private  String password;



}

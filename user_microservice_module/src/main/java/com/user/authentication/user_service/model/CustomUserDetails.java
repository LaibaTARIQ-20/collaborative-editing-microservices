package com.user.authentication.user_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.user.authentication.user_service.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails  implements UserDetails {
    private Long userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    @JsonIgnore
    private Boolean isAccountNonExpired;
    @JsonIgnore
    private Boolean isAccountNonLocked;
    @JsonIgnore
    private Boolean isCredentialsNonExpired;
    @JsonIgnore
    private Boolean isEnabled;

    public CustomUserDetails(UserEntity userEntity) {
        this.userId = userEntity.getId();
        this.firstName = userEntity.getFirstName();
        this.lastName = userEntity.getLastName();
        this.username = userEntity.getEmailAddress();
        this.password = userEntity.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}

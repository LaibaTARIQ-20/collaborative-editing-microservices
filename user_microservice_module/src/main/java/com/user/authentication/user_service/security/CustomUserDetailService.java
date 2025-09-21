package com.user.authentication.user_service.security;

import com.user.authentication.user_service.entity.UserEntity;
import com.user.authentication.user_service.model.CustomUserDetails;
import com.user.authentication.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.swing.text.html.Option;
import java.util.Optional;

public class CustomUserDetailService  implements UserDetailsService {
    @Autowired UserRepository userRepository;


    public CustomUserDetailService() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<UserEntity> userEntity= userRepository.findByEmailAddress(username);
       return   userEntity.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found exception"));

    }

}

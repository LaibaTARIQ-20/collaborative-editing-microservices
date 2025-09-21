package com.user.authentication.user_service.service.serviceImpl;

import com.user.authentication.user_service.dto.request.AuthenticationRequestDTO;
import com.user.authentication.user_service.dto.response.AuthenticationResponseDTO;
import com.user.authentication.user_service.exception.GlobleException;
import com.user.authentication.user_service.security.Jwtfactory;
import com.user.authentication.user_service.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationSeriveImpl  implements AuthenticationService {

   private final Jwtfactory jwtfactory;
   private  final AuthenticationManager authenticationManager;

   @Autowired
    public AuthenticationSeriveImpl(Jwtfactory jwtfactory, AuthenticationManager authenticationManager) {
        this.jwtfactory = jwtfactory;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO model) throws GlobleException {
       try{
        final Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(model.getEmailAddress(), model.getPassword())
        );
        if(authentication.isAuthenticated())
        {
            return jwtfactory.generateToken(model);

        }
      }
       catch (Exception e)
       {
           e.printStackTrace();
       }

            return null;

    }

    @Override
    public void logout() throws GlobleException {

    }

    @Override
    public void validateToken(String token) {
    jwtfactory.validateToken(token);
    }


}

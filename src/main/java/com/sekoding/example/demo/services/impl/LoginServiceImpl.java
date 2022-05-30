package com.sekoding.example.demo.services.impl;

import com.sekoding.example.demo.model.entity.User;
import com.sekoding.example.demo.model.request.LoginRequest;
import com.sekoding.example.demo.model.response.FailedResponse;
import com.sekoding.example.demo.model.response.SuccessResponse;
import com.sekoding.example.demo.model.response.payload.LoginResponse;
import com.sekoding.example.demo.repository.UserRepository;
import com.sekoding.example.demo.security.jwt.JwtUtils;
import com.sekoding.example.demo.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    @Override
    public Object loginUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
                )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            User user = userRepository.findUserByUsernameOrEmail(loginRequest.getUsername(), loginRequest.getUsername());

            if (user.getIsaktif().equals(Boolean.FALSE)) {
                return new FailedResponse(HttpStatus.FORBIDDEN, "User tidak aktif");
            }

            LoginResponse loginResponse = new LoginResponse(
                user.getId(),
                user.getNama(),
                user.getNik(),
                user.getAlamat(),
                user.getTanggalLahir(),
                user.getEmail(),
                user.getRoles().iterator().next().getRolename().toString(),
                jwt
            );

            return new SuccessResponse(HttpStatus.OK, "Success", loginResponse);
        } catch (Exception e) {
            return new FailedResponse(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

}

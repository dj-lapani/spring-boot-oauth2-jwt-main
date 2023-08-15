package com.dj.service;

import com.dj.dto.SignUpRequest;
import com.dj.exception.UserAlreadyExistAuthenticationException;
import com.dj.model.User;

public interface UserService {

    User findUserByEmail(String email);

    User registerNewUser(SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;
}

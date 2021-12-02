package com.khrono.app.service.user;

import com.khrono.app.domain.User;
import com.khrono.app.dtos.UserPasswordDto;

import java.util.List;

public interface IUserService {
    UserDto saveUser(UserDto user);

    List<User> getAllUsers();

    UserDto findUser(UserPasswordDto user);
}

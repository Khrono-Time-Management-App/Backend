package com.khrono.app.service.user;

import com.khrono.app.domain.User;
import com.khrono.app.dtos.UserPasswordDto;

import java.util.List;

public interface IUserService {
    User saveUser(User user);

    List<User> getAllUsers();

    UserPasswordDto findUser(UserPasswordDto user);
}

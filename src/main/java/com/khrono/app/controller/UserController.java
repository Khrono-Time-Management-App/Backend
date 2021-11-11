package com.khrono.app.controller;

import com.khrono.app.domain.User;
import com.khrono.app.service.user.UserDto;
import com.khrono.app.service.user.UserServiceImplementation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserServiceImplementation userService;

    @PostMapping({"/createUser"})
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto user)
    {
        System.out.println("here");
        UserDto savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

}

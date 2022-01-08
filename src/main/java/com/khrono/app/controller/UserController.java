package com.khrono.app.controller;

import com.khrono.app.config.JwtTokenService;
import com.khrono.app.domain.User;
import com.khrono.app.dtos.UserPasswordDto;
import com.khrono.app.service.mapper.UserMapperImplementation;
import com.khrono.app.service.user.UserDto;
import com.khrono.app.service.user.UserServiceImplementation;
import com.khrono.app.utils.enums.AppRoles;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin
public class UserController {

    private UserMapperImplementation userMapper;
    private final UserServiceImplementation userService;
    private final JwtTokenService jwtTokenService;

    @PostMapping({"/createUser"})
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto user) {
        System.out.println("here");
        UserDto savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping({"/getUsers"})
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping(value = "/login", produces = "text/plain")
    @SneakyThrows
    public ResponseEntity<String> login(@RequestBody UserPasswordDto userPasswordDto) {

        UserDto user = userService.findUser(userPasswordDto);
        String jwt;

        if (user == null)
            return ResponseEntity.internalServerError().body("Incorrect email!");
        else {
            if (user.getPassword() == null)
                return ResponseEntity.internalServerError().body("Incorrect password!");
            else
                jwt = jwtTokenService.createJwtToken(user, Collections.singleton(AppRoles.valueOf("USER")));
            return ResponseEntity.ok(jwt);
        }
    }

    @GetMapping({"/getUser"})
    public ResponseEntity<UserDto> getUser(@RequestHeader("Authorization") String token) {
        User user = jwtTokenService.getUserFromToken(token);
        UserDto userDto = userMapper.toService(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}

package com.khrono.app.dtos;

import lombok.Data;

@Data
public class UserPasswordDto {

    private Long id;

    private String email;

    private String password;
}

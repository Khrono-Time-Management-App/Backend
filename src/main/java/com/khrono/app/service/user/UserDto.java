package com.khrono.app.service.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class UserDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date birthDate;
}

package com.khrono.app.service.mapper;

import com.khrono.app.domain.User;
import com.khrono.app.service.user.UserDto;

import java.util.Collection;
import java.util.Set;

public interface IUserMapper {
    UserDto toService(User entity);

    User toEntity(UserDto dto);

    Set<UserDto> toServiceList(Collection<User> userList);

}

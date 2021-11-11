package com.khrono.app.service.user;

import com.khrono.app.domain.User;
import com.khrono.app.repository.IUserRepository;
import com.khrono.app.service.mapper.UserMapperImplementation;
import com.khrono.app.service.sequence.SequenceGenerator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImplementation implements IUserService{

    @Autowired
    private UserMapperImplementation userMapper;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    @Override
    public UserDto saveUser(UserDto userDto) {
        User userEntity = userMapper.toEntity(userDto);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setId(sequenceGenerator.getSequenceNumber(User.SEQUENCE_NAME));
        return userMapper.toService(userRepository.save(userEntity));
    }
}

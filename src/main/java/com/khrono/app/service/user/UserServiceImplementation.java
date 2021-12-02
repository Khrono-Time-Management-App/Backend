package com.khrono.app.service.user;

import com.khrono.app.domain.User;
import com.khrono.app.dtos.UserPasswordDto;
import com.khrono.app.repository.IUserRepository;
import com.khrono.app.service.mapper.UserMapperImplementation;
import com.khrono.app.service.sequence.SequenceGenerator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImplementation implements IUserService{


    private UserMapperImplementation userMapper;

    private final IUserRepository userRepository;


    private final SequenceGenerator sequenceGenerator;

    private final PasswordEncoder encoder;

    @Override
    public UserDto saveUser(UserDto userDto) {
        User userEntity = userMapper.toEntity(userDto);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setId(sequenceGenerator.getSequenceNumber(User.SEQUENCE_NAME));
        return userMapper.toService(userRepository.save(userEntity));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDto findUser(UserPasswordDto user) {
        User foundUser = userRepository.findByEmail(user.getEmail());

        if(foundUser == null)
            return null;
        else if (!encoder.matches(user.getPassword(), foundUser.getPassword())) {
            user.setPassword(null);
        }
        return userMapper.toService(foundUser);

    }


}

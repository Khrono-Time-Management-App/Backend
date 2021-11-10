package com.khrono.app.service.user;

import com.khrono.app.domain.User;
import com.khrono.app.dtos.UserPasswordDto;
import com.khrono.app.repository.IUserRepository;
import com.khrono.app.service.sequence.SequenceGenerator;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImplementation implements IUserService{


    private final IUserRepository userRepository;


    private final SequenceGenerator sequenceGenerator;

    private final PasswordEncoder encoder;

    @Override
    public User saveUser(User userEntity) {
        userEntity.setPassword(encoder.encode(userEntity.getPassword()));
        userEntity.setId(sequenceGenerator.getSequenceNumber(User.SEQUENCE_NAME));
        return userRepository.save(userEntity);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserPasswordDto findUser(UserPasswordDto user) {
        User foundUser = userRepository.findByEmail(user.getEmail());

        if(foundUser == null)
            return null;
        else if (!encoder.matches(user.getPassword(), foundUser.getPassword())) {
            user.setPassword(null);
        }
        return user;

    }


}

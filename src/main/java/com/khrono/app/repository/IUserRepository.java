package com.khrono.app.repository;

import com.khrono.app.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<User,Integer> {
    User findByEmail(String email);
}

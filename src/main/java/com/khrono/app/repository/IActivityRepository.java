package com.khrono.app.repository;

import com.khrono.app.domain.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IActivityRepository extends MongoRepository<Activity,Integer> {
}

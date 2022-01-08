package com.khrono.app.repository;

import com.khrono.app.domain.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface IActivityRepository extends MongoRepository<Activity,Integer> {

    List<Activity> findAllByUserId(int userId);
    Activity findByIdAndUserId(int userId, int id);

    long count();
}

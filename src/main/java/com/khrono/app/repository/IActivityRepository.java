package com.khrono.app.repository;

import com.khrono.app.domain.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface IActivityRepository extends MongoRepository<Activity,Integer> {

    @Query(value="{userId:'?0'}")
    List<Activity> findAllFromUser(int userId);

    long count();
}

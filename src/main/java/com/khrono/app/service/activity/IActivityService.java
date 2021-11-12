package com.khrono.app.service.activity;

public interface IActivityService {

    ActivityDto saveActivity(ActivityDto activity);
    ActivityListDto getActivitiesFromUser();
    ActivityDto getActivityById(int id);
    ActivityDto updateActivity(ActivityDto activity);
    ActivityDto deleteActivity(ActivityDto activity);
}

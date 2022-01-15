package com.khrono.app.service.activity;

public interface IActivityService {

    ActivityDto saveActivity(int userId, ActivityDto activity);
    ActivityListDto getActivitiesFromUser(int userId);
    ActivityDto getActivityById(int userId, int activityId);
    ActivityDto updateActivity(int userId, ActivityDto activity);
    ActivityDto deleteActivity(int userId, ActivityDto activity);
    ActivityReportDto getActivitiesInLast2Months(int userId);
    float getUserActivitiesPercentageToMean(int userId);
}

package com.khrono.app.service.activity;

import java.util.List;

public interface IActivityService {

    ActivityDto saveActivity(int userId, ActivityDto activity);
    ActivityListDto getActivitiesFromUser(int userId);
    ActivityDto getActivityById(int userId, int activityId);
    ActivityDto updateActivity(int userId, ActivityDto activity);
    ActivityDto deleteActivity(int userId, ActivityDto activity);
    ActivityReportDto getActivitiesInLast2Months(int userId);
}

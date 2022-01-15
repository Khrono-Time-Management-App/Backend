package com.khrono.app.service.activity;

import com.khrono.app.domain.Activity;
import com.khrono.app.repository.IActivityRepository;
import com.khrono.app.repository.IUserRepository;
import com.khrono.app.service.mapper.IActivityMapper;
import com.khrono.app.service.sequence.SequenceGenerator;
import lombok.AllArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ActivityServiceImplementation implements IActivityService {

    @Autowired
    private final IActivityRepository activityRepository;

    @Autowired
    private final IUserRepository userRepository;

    @Autowired
    private final IActivityMapper activityMapper;

    @Autowired
    private final SequenceGenerator sequenceGenerator;


    @Override
    public ActivityDto saveActivity(int userId, ActivityDto activityDto) {
        Activity activity = activityMapper.toEntity(activityDto);
        activity.setId(sequenceGenerator.getSequenceNumber(Activity.SEQUENCE_NAME));
        activity.setUserId(userId);

        return activityMapper.toService(
                activityRepository.save(
                        activity
                )
        );
    }

    @Override
    public ActivityListDto getActivitiesFromUser(int userId) {

        System.out.print("user id --->");
        System.out.println(userId);

        ActivityListDto activities = new ActivityListDto(
                activityMapper.toServiceList(
                        activityRepository.findAllByUserId(userId)
                )
        );

        System.out.print("activity list fro --->");
        System.out.println(activities);

        return activities;
    }

    @Override
    public ActivityDto getActivityById(int userId, int activityId) {
        ActivityDto activityDto = activityMapper.toService(
                activityRepository
                        .findByIdAndUserId(userId, activityId)
        );
        activityDto.setUserId(userId);
        return activityDto;
    }

    @Override
    public ActivityDto updateActivity(int userId, ActivityDto activityDto) {
        Activity existingActivity = activityRepository.findById(activityDto.getId()).orElse(null);

        if (existingActivity == null)
            return null;

        ActivityDto activityDto1 = activityMapper.toService(
                activityRepository.save(
                        activityMapper.toEntity(activityDto)
                )
        );
        activityDto1.setUserId(userId);
        return activityDto1;
    }

    @Override
    public ActivityDto deleteActivity(int userId, ActivityDto activityDto) {
        Activity activity = activityMapper.toEntity(
                activityDto
        );
        if (activity.getUserId() != userId)
            throw new RuntimeException("ai belit pula");
        if (!activityRepository.findById(
                activity.getId()
        ).isPresent())
            return null;

        activityRepository.delete(
                activity
        );

        return activityDto;
    }

    @Override
    public ActivityReportDto getActivitiesInLast2Months(int userId) {

        System.out.print("user id --->");
        System.out.println(userId);

        var activities =  activityRepository.findAllByUserId(userId);

        LocalDate date = LocalDate.now().minusMonths(1);

        LocalDate previousDate = LocalDate.now().minusMonths(2);

        var currentMonthActivities = activities.stream().filter(s ->
            s.getEndDateTime().after(java.sql.Date.valueOf(date))
        ).collect(Collectors.toList());

        var previousMonthActivities = activities.stream().filter(s ->
                s.getEndDateTime().after(java.sql.Date.valueOf(previousDate)) && s.getEndDateTime().before(java.sql.Date.valueOf(date)))
                .collect(Collectors.toList());


        System.out.print("activity list fro --->");
        System.out.println(activities);

        return new ActivityReportDto(activityMapper.toServiceList(currentMonthActivities),activityMapper.toServiceList(previousMonthActivities));

    }

    @Override
    public float getUserActivitiesPercentageToMean(int userId) {
        return getUserActivitiesTotalMillis(userId) / getAllUsersMeanActivitiesMillis() - 1;
    }

    private float getUserActivitiesTotalMillis(int userId) {
        return activityRepository.findAllByUserId(userId)
                .stream()
                .map(Activity::getActivityDatesDiffInMillis)
                .reduce(0L, Long::sum);
    }

    private float getAllUsersActivitiesTotalMillis() {
        return activityRepository.findAll()
                .stream()
                .map(Activity::getActivityDatesDiffInMillis)
                .reduce(0L, Long::sum);
    }

    private float getAllUsersMeanActivitiesMillis() {
        return getAllUsersActivitiesTotalMillis() / userRepository.count();
    }
}

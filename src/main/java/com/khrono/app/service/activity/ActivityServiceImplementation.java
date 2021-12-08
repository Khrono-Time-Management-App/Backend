package com.khrono.app.service.activity;

import com.khrono.app.config.JwtTokenService;
import com.khrono.app.domain.Activity;
import com.khrono.app.repository.IActivityRepository;
import com.khrono.app.service.mapper.IActivityMapper;
import com.khrono.app.service.sequence.SequenceGenerator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ActivityServiceImplementation implements IActivityService {

    @Autowired
    private final IActivityRepository activityRepository;

    @Autowired
    private final IActivityMapper activityMapper;

    @Autowired
    private final SequenceGenerator sequenceGenerator;


    @Override
    public ActivityDto saveActivity(int userId, ActivityDto activityDto) {
        Activity activity = activityMapper.toEntity(activityDto);
        activity.setId(sequenceGenerator.getSequenceNumber(Activity.SEQUENCE_NAME));

        ActivityDto activityDto1 = activityMapper.toService(
                activityRepository.save(
                        activity
                )
        );
        activityDto1.setUserId(userId);
        return activityDto1;
    }

    @Override
    public ActivityListDto getActivitiesFromUser(int userId) {

        return new ActivityListDto(
                activityMapper.toServiceList(
                        activityRepository.findAllByUserId(userId)
                )
        );
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
}

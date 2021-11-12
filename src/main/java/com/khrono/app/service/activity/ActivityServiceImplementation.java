package com.khrono.app.service.activity;

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
    public ActivityDto saveActivity(ActivityDto activityDto) {
        Activity activity = activityMapper.toEntity(activityDto);
        activity.setId(sequenceGenerator.getSequenceNumber(Activity.SEQUENCE_NAME));

        return activityMapper.toService(
                activityRepository.save(
                        activity
                )
        );
    }

    @Override
    public ActivityListDto getActivitiesFromUser() {
        return new ActivityListDto(
                activityMapper.toServiceList(
                        activityRepository.findAll()
                )
        );
    }

    @Override
    public ActivityDto getActivityById(int id) {
        return activityMapper.toService(
                activityRepository
                        .findById(id)
                        .orElse(null)
        );
    }

    @Override
    public ActivityDto updateActivity(ActivityDto activityDto) {
        Activity existingActivity = activityRepository.findById(activityDto.getId()).orElse(null);

        if (existingActivity == null)
            return null;

        return activityMapper.toService(
                activityRepository.save(
                        activityMapper.toEntity(activityDto)
                )
        );
    }

    @Override
    public ActivityDto deleteActivity(ActivityDto activityDto) {
        activityRepository.delete(
                activityMapper.toEntity(
                        activityDto
                )
        );

        return activityDto;
    }
}

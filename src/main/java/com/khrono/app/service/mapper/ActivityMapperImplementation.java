package com.khrono.app.service.mapper;

import com.khrono.app.domain.Activity;
import com.khrono.app.service.activity.ActivityDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ActivityMapperImplementation implements IActivityMapper{
    @Override
    public ActivityDto toService(Activity entity) {
        return ActivityDto.builder()
                .id(entity.getId())
                .startDateTime(entity.getStartDateTime())
                .endDateTime(entity.getEndDateTime())
                .category(entity.getCategory())
                .build();
    }

    @Override
    public Activity toEntity(ActivityDto dto) {
        return Activity.builder()
                .id(dto.getId())
                .startDateTime(dto.getStartDateTime())
                .endDateTime(dto.getEndDateTime())
                .category(dto.getCategory())
                .build();
    }

    @Override
    public Set<ActivityDto> toServiceList(Collection<Activity> activityList) {
        return activityList
                .stream()
                .map(this::toService)
                .collect(Collectors.toSet());
    }
}

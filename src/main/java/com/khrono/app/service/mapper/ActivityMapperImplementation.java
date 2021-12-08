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
                .name(entity.getName())
                .startDateTime(entity.getStartDateTime())
                .endDateTime(entity.getEndDateTime())
                .category(entity.getCategory())
                .userId(entity.getUserId())
                .build();
    }

    @Override
    public Activity toEntity(ActivityDto dto) {
        return Activity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .startDateTime(dto.getStartDateTime())
                .endDateTime(dto.getEndDateTime())
                .category(dto.getCategory())
                .userId(dto.getUserId())
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

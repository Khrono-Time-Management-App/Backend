package com.khrono.app.service.mapper;

import com.khrono.app.domain.Activity;
import com.khrono.app.service.activity.ActivityDto;

import java.util.Collection;
import java.util.Set;

public interface IActivityMapper {
    ActivityDto toService(Activity entity);

    Activity toEntity(ActivityDto dto);

    Set<ActivityDto> toServiceList(Collection<Activity> activityList);
}

package com.khrono.app.service.activity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ActivityReportDto {
    private Set<ActivityDto> currentMonth;

    private Set<ActivityDto> previousMonth;
}

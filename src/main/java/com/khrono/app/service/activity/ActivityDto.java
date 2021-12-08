package com.khrono.app.service.activity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class ActivityDto {
    private int id;
    private String name;
    private Date startDateTime;
    private Date endDateTime;
    private String category;
    private int userId;
}

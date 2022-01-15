package com.khrono.app.controller;

import com.khrono.app.config.JwtTokenService;
import com.khrono.app.domain.User;
import com.khrono.app.service.activity.ActivityDto;
import com.khrono.app.service.activity.ActivityListDto;
import com.khrono.app.service.activity.ActivityReportDto;
import com.khrono.app.service.activity.ActivityServiceImplementation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activity")
@AllArgsConstructor
@CrossOrigin
public class ActivityController {

    private final JwtTokenService jwtTokenService;

    private final ActivityServiceImplementation activityService;

    @GetMapping({"/getActivities"})
    public ResponseEntity<ActivityListDto> getActivities(@RequestHeader("Authorization") String token)
    {
        User user = jwtTokenService.getUserFromToken(token);
        return new ResponseEntity<>(activityService.getActivitiesFromUser(user.getId()), HttpStatus.OK);
    }

    @GetMapping({"/getActivities/last2Months"})
    public ResponseEntity<ActivityReportDto> getActivitiesFromLast2Months(@RequestHeader("Authorization") String token)
    {
        User user = jwtTokenService.getUserFromToken(token);
        return new ResponseEntity<>(activityService.getActivitiesInLast2Months(user.getId()), HttpStatus.OK);
    }

    @GetMapping({"/getActivity/{activityId}"})
    public ResponseEntity<ActivityDto> getActivity(
            @RequestHeader("Authorization") String token,
            @PathVariable int activityId
    ) {
        User user = jwtTokenService.getUserFromToken(token);
        return new ResponseEntity<>(activityService.getActivityById(user.getId(), activityId), HttpStatus.OK);
    }

    @PostMapping({"/createActivity"})
    public ResponseEntity<ActivityDto> addActivity(
            @RequestHeader("Authorization") String token,
            @RequestBody ActivityDto activity
    ) {
        User user = jwtTokenService.getUserFromToken(token);
        ActivityDto activityDto = activityService.saveActivity(user.getId(), activity);

        System.out.print("Added activity ---> ");
        System.out.println(activityDto);

        return new ResponseEntity<>(activityDto, HttpStatus.CREATED);
    }

    @PostMapping({"/updateActivity"})
    public ResponseEntity<ActivityDto> updateActivity(
            @RequestHeader("Authorization") String token,
            @RequestBody ActivityDto activity
    ) {
        User user = jwtTokenService.getUserFromToken(token);
        ActivityDto activityDto = activityService.updateActivity(user.getId(), activity);

        if (activityDto == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(activityDto, HttpStatus.CREATED);
    }

    @PostMapping({"/deleteActivity"})
    public ResponseEntity<ActivityDto> deleteActivity(
            @RequestHeader("Authorization") String token,
            @RequestBody ActivityDto activity
    ) {

        User user = jwtTokenService.getUserFromToken(token);
        try {
            ActivityDto activityDto = activityService.deleteActivity(user.getId(), activity);

            if (activityDto == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(activityDto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}

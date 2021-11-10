package com.khrono.app.controller;

import com.khrono.app.service.activity.ActivityServiceImplementation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity")
@AllArgsConstructor
public class ActivityController {

    @Autowired
    private final ActivityServiceImplementation activityService;
}

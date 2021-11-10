package com.khrono.app.service.activity;

import com.khrono.app.repository.IActivityRepository;
import com.khrono.app.service.sequence.SequenceGenerator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ActivityServiceImplementation implements IActivityService {

    @Autowired
    private IActivityRepository activityRepository;

    @Autowired
    private SequenceGenerator sequenceGenerator;
}

package com.khrono.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "activity")
public class Activity {

    @Transient
    public static final String SEQUENCE_NAME = "activity_sequence";

    @Id
    private int id;
    private String name;
    private Date startDateTime;
    private Date endDateTime;
    private String category;

    private int userId;

    public long getActivityDatesDiffInMillis() {
        if (getStartDateTime() == null || getEndDateTime() == null)
            return 0;
        return getEndDateTime().getTime() - getStartDateTime().getTime();
    }
}

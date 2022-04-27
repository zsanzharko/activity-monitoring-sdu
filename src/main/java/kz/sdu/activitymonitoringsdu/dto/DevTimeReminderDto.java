package kz.sdu.activitymonitoringsdu.dto;

import kz.sdu.activitymonitoringsdu.entity.DevTimeReminder;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class DevTimeReminderDto implements Serializable {
    private final Long userId;
    private final Long activityId;
    private final Date dateRemind;
    private final String remindDescription;

    public static DevTimeReminderDto convertToDto(DevTimeReminder reminder) {
        String remindDescription = "";

        switch (reminder.getRemindType()) {
            case SPEND_TIME -> remindDescription = "You forgot enter the spend time. Please, go to enter in activity";
            case EXCEPTED_TIME -> remindDescription = "Your excepted time is bad. Please, work hard, and be productivity";
        }

        return DevTimeReminderDto.builder()
                .userId(reminder.getId())
                .activityId(reminder.getActivityId())
                .dateRemind(reminder.getDateRemind())
                .remindDescription(remindDescription)
                .build();
    }
}

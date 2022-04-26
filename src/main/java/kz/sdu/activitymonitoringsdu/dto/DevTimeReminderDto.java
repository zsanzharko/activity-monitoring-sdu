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

    public static DevTimeReminderDto convertToDto(DevTimeReminder reminder) {
        return DevTimeReminderDto.builder()
                .userId(reminder.getId())
                .activityId(reminder.getActivityId())
                .dateRemind(reminder.getDateRemind())
                .build();
    }
}

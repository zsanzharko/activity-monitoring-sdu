package kz.sdu.activitymonitoringsdu.service;

import kz.sdu.activitymonitoringsdu.dto.DevTimeReminderDto;

import java.util.Date;
import java.util.List;

public interface TimeReminderService {

    List<DevTimeReminderDto> findTimeRemindersByActivityId(Long activityId);

    List<DevTimeReminderDto> findTimeRemindersByDateRemind(Date dateRemind);

    DevTimeReminderDto findTimeReminderByActivityIdAndDate(Long activityId, Date dateRemind);

    List<DevTimeReminderDto> findTimeRemindersByUserId(Long userId);

    void removeAllByUserId(Long userId);

    void removeDevTimeReminderByIdAndActivityIdAndDateRemind(Long id, Long activityId, Date dateRemind);
}

package kz.sdu.activitymonitoringsdu.dao;

import kz.sdu.activitymonitoringsdu.dto.DevTimeReminderDto;
import kz.sdu.activitymonitoringsdu.entity.Activity;
import kz.sdu.activitymonitoringsdu.entity.DevTimeReminder;
import kz.sdu.activitymonitoringsdu.repository.ActivityRepository;
import kz.sdu.activitymonitoringsdu.repository.DevTimeReminderRepository;
import kz.sdu.activitymonitoringsdu.service.TimeReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TimeReminderDao implements TimeReminderService {

    private final DevTimeReminderRepository timeReminderRepository;
    private final ActivityRepository activityRepository;

    @Autowired
    public TimeReminderDao(DevTimeReminderRepository timeReminderRepository, ActivityRepository activityRepository) {
        this.timeReminderRepository = timeReminderRepository;
        this.activityRepository = activityRepository;
    }

    @Override
    public List<DevTimeReminderDto> findTimeRemindersByActivityId(Long activityId) {
        return timeReminderRepository.findDevTimeRemindersByActivityId(activityId)
                .stream()
                .map(DevTimeReminderDto::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DevTimeReminderDto> findTimeRemindersByDateRemind(Date dateRemind) {
        return timeReminderRepository.findDevTimeRemindersByDateRemind(dateRemind)
                .stream()
                .map(DevTimeReminderDto::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DevTimeReminderDto findTimeReminderByActivityIdAndDate(Long activityId, Date dateRemind) {
        DevTimeReminder reminder = timeReminderRepository.findDevTimeReminderByActivityIdAndDateRemind(activityId, dateRemind);
        if (reminder == null) return null;
        return DevTimeReminderDto.convertToDto(reminder);
    }

    @Override
    public List<DevTimeReminderDto> findTimeRemindersByUserId(Long userId) {
        return timeReminderRepository.findDevTimeRemindersById(userId)
                .stream()
                .map(DevTimeReminderDto::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void removeAllByUserId(Long userId) {
        timeReminderRepository.removeAllById(userId);
    }

    @Override
    public void removeDevTimeReminderByIdAndActivityIdAndDateRemind(Long id, Long activityId, Date dateRemind) {
        timeReminderRepository.removeDevTimeReminderByIdAndActivityIdAndDateRemind(id, activityId, dateRemind);
    }

    public Map<Long, String> getTitleFromActivity(List<DevTimeReminderDto> reminderDtoList) {
        assert reminderDtoList != null;
        Map<Long, String> titles = new HashMap<>(reminderDtoList.size());
        for (DevTimeReminderDto devTimeReminderDto : reminderDtoList) {
            Activity activity = activityRepository.findById(
                    devTimeReminderDto.getActivityId()).orElse(null);
            assert activity != null;
            titles.put(devTimeReminderDto.getActivityId(), activity.getTitle());
        }
        return titles;
    }
}

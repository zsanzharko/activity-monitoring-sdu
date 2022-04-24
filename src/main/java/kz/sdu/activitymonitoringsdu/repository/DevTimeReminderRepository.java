package kz.sdu.activitymonitoringsdu.repository;

import kz.sdu.activitymonitoringsdu.entity.DevTimeReminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DevTimeReminderRepository extends JpaRepository<DevTimeReminder, Long> {

    List<DevTimeReminder> findDevTimeRemindersByActivityId(Integer activityId);

    List<DevTimeReminder> findDevTimeRemindersByDateRemind(Date dateRemind);

    List<DevTimeReminder> findDevTimeRemindersById(Long id);

    void removeAllById(Long id);

    void removeDevTimeReminderByIdAndActivityIdAndDateRemind(Long id, Integer activityId, Date dateRemind);
}
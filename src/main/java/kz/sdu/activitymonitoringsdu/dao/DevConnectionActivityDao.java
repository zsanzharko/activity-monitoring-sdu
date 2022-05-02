package kz.sdu.activitymonitoringsdu.dao;

import kz.sdu.activitymonitoringsdu.entity.DevConnectionActivity;
import kz.sdu.activitymonitoringsdu.repository.DevConnectionActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevConnectionActivityDao {

    private final DevConnectionActivityRepository activityRepository;

    public DevConnectionActivityDao(DevConnectionActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public DevConnectionActivity getAssignedUserByActivityId(Long activityId) {
        return activityRepository.findById(activityId).orElse(null);
    }

    public DevConnectionActivity save(DevConnectionActivity assign) {
        return activityRepository.save(assign);
    }

    public List<DevConnectionActivity> findAllByUserIdAssign(Long userId) {
        return activityRepository.findAllByUserId(userId);
    }

    public void deleteAllByActivityId(Long activityId) {
        activityRepository.deleteAllByActivityId(activityId);
    }
}

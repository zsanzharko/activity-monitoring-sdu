package kz.sdu.activitymonitoringsdu.dao;

import kz.sdu.activitymonitoringsdu.entity.DevConnectionActivity;
import kz.sdu.activitymonitoringsdu.repository.DevConnectionActivityRepository;
import org.springframework.stereotype.Service;

@Service
public class DevConnectionActivityDao {

    private final DevConnectionActivityRepository activityRepository;

    public DevConnectionActivityDao(DevConnectionActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public DevConnectionActivity getAssignedUserByActivityId(Long activityId) {
        return activityRepository.getById(activityId);
    }
}

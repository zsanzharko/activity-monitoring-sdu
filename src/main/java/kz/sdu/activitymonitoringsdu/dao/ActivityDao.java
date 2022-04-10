package kz.sdu.activitymonitoringsdu.dao;

import kz.sdu.activitymonitoringsdu.dto.ActivityDto;
import kz.sdu.activitymonitoringsdu.entity.Activity;
import kz.sdu.activitymonitoringsdu.enums.ActivityStatus;
import kz.sdu.activitymonitoringsdu.handlers.ActivityHandlerUtils;
import kz.sdu.activitymonitoringsdu.repository.ActivityRepository;
import kz.sdu.activitymonitoringsdu.service.ActivityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityDao implements ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityDao(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public List<Activity> findAllByProjectId(String projectId) {
        return activityRepository.findAllByProjectId(projectId);
    }

    @Override
    public List<Activity> findAllByStatus(ActivityStatus status) {
        return activityRepository.findAllByStatus(status);
    }

    @Override
    public ActivityDto findById(Long id) {
        Activity activity = activityRepository.findById(id).orElse(null);

        return ActivityHandlerUtils.convertToDto(activity);
    }

    @Override
    public Activity save(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public void deleteById(Long id) {
        activityRepository.deleteById(id);
    }

    @Override
    public void deleteAllByProjectId(String projectId) {
        activityRepository.deleteAllByProjectId(projectId);
    }
}

package kz.sdu.activitymonitoringsdu.service;

import kz.sdu.activitymonitoringsdu.dto.ActivityDto;
import kz.sdu.activitymonitoringsdu.entity.Activity;
import kz.sdu.activitymonitoringsdu.enums.ActivityStatus;

import java.util.List;

public interface ActivityService {

    List<Activity> findAllByProjectId(String projectId);

    List<Activity> findAllByStatus(ActivityStatus status);

    ActivityDto findById(Long id);

    Activity save(Activity activity);

    void deleteById(Long id);

    void deleteAllByProjectId(String projectId);
}

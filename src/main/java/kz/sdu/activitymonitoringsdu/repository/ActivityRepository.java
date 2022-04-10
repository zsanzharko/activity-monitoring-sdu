package kz.sdu.activitymonitoringsdu.repository;

import kz.sdu.activitymonitoringsdu.entity.Activity;
import kz.sdu.activitymonitoringsdu.enums.ActivityStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findAllByProjectId(String projectId);

    List<Activity> findAllByStatus(ActivityStatus status);

    void deleteAllByProjectId(String projectId);
}
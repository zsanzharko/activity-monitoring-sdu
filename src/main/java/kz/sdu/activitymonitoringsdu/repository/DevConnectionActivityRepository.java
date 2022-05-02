package kz.sdu.activitymonitoringsdu.repository;

import kz.sdu.activitymonitoringsdu.entity.DevConnectionActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevConnectionActivityRepository extends JpaRepository<DevConnectionActivity, Long> {

    List<DevConnectionActivity> findAllByUserId(Long userId);

    void deleteAllByActivityId(Long activityId);
}
package kz.sdu.activitymonitoringsdu.repository;

import kz.sdu.activitymonitoringsdu.entity.DevConnectionActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DevConnectionActivityRepository extends JpaRepository<DevConnectionActivity, Long> {

    List<DevConnectionActivity> findAllByUserId(Long userId);
}
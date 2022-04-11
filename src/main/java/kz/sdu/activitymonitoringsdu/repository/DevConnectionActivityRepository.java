package kz.sdu.activitymonitoringsdu.repository;

import kz.sdu.activitymonitoringsdu.entity.DevConnectionActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevConnectionActivityRepository extends JpaRepository<DevConnectionActivity, Long> {
}
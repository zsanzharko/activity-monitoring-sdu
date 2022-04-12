package kz.sdu.activitymonitoringsdu.repository;

import kz.sdu.activitymonitoringsdu.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findAllByActivityId(Long activityId);

    void removeAllByActivityId(Long activityId);
}
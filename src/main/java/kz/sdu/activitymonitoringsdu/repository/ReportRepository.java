package kz.sdu.activitymonitoringsdu.repository;

import kz.sdu.activitymonitoringsdu.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findAllByActivityId(Long activityId);

    Report findByActivityIdAndReportDate(Long activityId, Date reportDate);

    List<Report> getReportsByActivityId(Long activityId);

    void removeAllByActivityId(Long activityId);
}
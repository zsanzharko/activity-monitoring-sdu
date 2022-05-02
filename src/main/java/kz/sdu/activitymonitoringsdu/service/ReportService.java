package kz.sdu.activitymonitoringsdu.service;

import kz.sdu.activitymonitoringsdu.entity.Report;

import java.util.Date;
import java.util.List;

public interface ReportService {

    Report save(Report report);

    List<Report> findAllByActivityId(Long activityId);

    void removeAllByActivityId(Long activityId);

    Report findByActivityIdAndReportDate(Long activityId, Date reportDate);
}

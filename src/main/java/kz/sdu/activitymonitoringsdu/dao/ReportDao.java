package kz.sdu.activitymonitoringsdu.dao;

import kz.sdu.activitymonitoringsdu.entity.Report;
import kz.sdu.activitymonitoringsdu.repository.ReportRepository;
import kz.sdu.activitymonitoringsdu.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Slf4j
public class ReportDao implements ReportService {

    private final ReportRepository reportRepository;


    public ReportDao(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }


    @Override
    public Report save(Report report) {
        List<Report> dbReports = reportRepository.findAllByActivityId(report.getActivityId());
        var timeFormat = new SimpleDateFormat("yyyy.MM.dd");

        log.info("Report stamp: " + timeFormat.format(report.getReportDate()));
        log.info("db stamp: ");
        dbReports.forEach(r -> log.info("\t" + timeFormat.format(r.getReportDate())));
        for (var dbReport : dbReports) {
            if (timeFormat.format(report.getReportDate()).equals(timeFormat.format(dbReport.getReportDate()))) {
                dbReport.setTime(report.getTime());
                dbReport.setReportDate(report.getReportDate());
                return reportRepository.saveAndFlush(dbReport);
            }
        }
        return reportRepository.save(report);
    }

    @Override
    public List<Report> findAllByActivityId(Long activityId) {
        return reportRepository.findAllByActivityId(activityId);
    }

    @Override
    public void removeAllByActivityId(Long activityId) {
        reportRepository.removeAllByActivityId(activityId);
    }
}

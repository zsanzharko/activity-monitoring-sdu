package kz.sdu.activitymonitoringsdu.dao;

import kz.sdu.activitymonitoringsdu.entity.Report;
import kz.sdu.activitymonitoringsdu.repository.ReportRepository;
import kz.sdu.activitymonitoringsdu.service.ReportService;
import org.springframework.stereotype.Service;

@Service
public class ReportDao implements ReportService {

    private final ReportRepository reportRepository;


    public ReportDao(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }


    public Report save(Report report) {
        return reportRepository.save(report);
    }
}

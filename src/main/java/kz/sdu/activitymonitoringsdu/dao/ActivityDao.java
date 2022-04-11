package kz.sdu.activitymonitoringsdu.dao;

import kz.sdu.activitymonitoringsdu.dto.ActivityDto;
import kz.sdu.activitymonitoringsdu.entity.Activity;
import kz.sdu.activitymonitoringsdu.entity.Consist;
import kz.sdu.activitymonitoringsdu.entity.Report;
import kz.sdu.activitymonitoringsdu.enums.ActivityStatus;
import kz.sdu.activitymonitoringsdu.handlers.ActivityHandlerUtils;
import kz.sdu.activitymonitoringsdu.repository.ActivityRepository;
import kz.sdu.activitymonitoringsdu.repository.ReportRepository;
import kz.sdu.activitymonitoringsdu.service.ActivityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ActivityDao implements ActivityService {

    private final ActivityRepository activityRepository;
    private final ReportRepository spendtimeUserRepository;
    public ActivityDao(ActivityRepository activityRepository, ReportRepository spendtimeUserRepository) {
        this.activityRepository = activityRepository;
        this.spendtimeUserRepository = spendtimeUserRepository;
    }

    @Override
    public List<Activity> findAllByStatus(ActivityStatus status) {
        return activityRepository.findAllByStatus(status);
    }

    @Override
    public ActivityDto findById(Long id) {
        Activity activity = activityRepository.findById(id).orElse(null);

        assert activity != null;
        return ActivityHandlerUtils.convertToDto(activity);
    }

    public List<ActivityDto> getActivitiesById(List<Consist> consists){
        List<ActivityDto> activities = new ArrayList<>();
        for (Consist consist : consists) {
            activities.add(
                    ActivityHandlerUtils.convertToDto(
                            Objects.requireNonNull(activityRepository.findById(consist.getId()).orElse(null))));
        }
        return activities;
    }

    @Override
    public Activity save(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public void deleteById(Long id) {
        activityRepository.deleteById(id);
    }

    public Report save(Report report) {
        return spendtimeUserRepository.save(report);
    }

    public List<Report> findByActivityId(Long activityId){
        return spendtimeUserRepository.findAllByActivityId(activityId);
    }
}

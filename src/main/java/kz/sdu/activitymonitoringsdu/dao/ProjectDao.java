package kz.sdu.activitymonitoringsdu.dao;

import kz.sdu.activitymonitoringsdu.dto.ActivityDto;
import kz.sdu.activitymonitoringsdu.entity.Activity;
import kz.sdu.activitymonitoringsdu.entity.Consist;
import kz.sdu.activitymonitoringsdu.entity.Project;
import kz.sdu.activitymonitoringsdu.entity.Report;
import kz.sdu.activitymonitoringsdu.enums.ActivityStatus;
import kz.sdu.activitymonitoringsdu.handlers.ActivityHandlerUtils;
import kz.sdu.activitymonitoringsdu.repository.ActivityRepository;
import kz.sdu.activitymonitoringsdu.repository.ProjectRepository;
import kz.sdu.activitymonitoringsdu.repository.ReportRepository;
import kz.sdu.activitymonitoringsdu.service.ActivityService;
import kz.sdu.activitymonitoringsdu.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProjectDao implements ProjectService, ActivityService {

    private final ProjectRepository projectRepository;
    private final ActivityRepository activityRepository;
    private final ReportRepository spendTimeUserRepository;

    @Autowired
    public ProjectDao(ProjectRepository projectRepository, ActivityRepository activityRepository, ReportRepository spendTimeUserRepository) {
        this.projectRepository = projectRepository;
        this.activityRepository = activityRepository;
        this.spendTimeUserRepository = spendTimeUserRepository;
    }

    @Override
    public Project findById(String id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteByProjectId(String projectId) {
        projectRepository.deleteByProjectId(projectId);
    }

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
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
                            Objects.requireNonNull(activityRepository.findById(consist.getActivityId()).orElse(null))));
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
        return spendTimeUserRepository.save(report);
    }

    public List<Report> findByActivityId(Long activityId){
        return spendTimeUserRepository.findAllByActivityId(activityId);
//        return spendTimeUserRepository.getReportsByActivityId(activityId);
    }

}

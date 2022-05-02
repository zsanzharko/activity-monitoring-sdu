package kz.sdu.activitymonitoringsdu.controller.rest;

import kz.sdu.activitymonitoringsdu.dao.*;
import kz.sdu.activitymonitoringsdu.dto.ProjectDto;
import kz.sdu.activitymonitoringsdu.dto.UserDto;
import kz.sdu.activitymonitoringsdu.entity.Activity;
import kz.sdu.activitymonitoringsdu.entity.DevConnectionActivity;
import kz.sdu.activitymonitoringsdu.enums.Role;
import kz.sdu.activitymonitoringsdu.exception.ApiRequestException;
import kz.sdu.activitymonitoringsdu.handlers.ActivityHandlerUtils;
import kz.sdu.activitymonitoringsdu.handlers.UserHandlerUtils;
import kz.sdu.activitymonitoringsdu.handlers.body.ColorHandlerBody;
import kz.sdu.activitymonitoringsdu.handlers.forms.ActivityCreateForm;
import kz.sdu.activitymonitoringsdu.handlers.forms.ProjectCreateForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/api", consumes = "application/json", produces = "application/json")
public class RestProjectController {

    private final static String LOG_TAG = "RestProjectController";

    private final UserDao userDao;
    private final ProjectDao projectDao;
    private final ConsistDao consistDao;
    private final DevConnectionActivityDao assignedUserDao;
    private final ReportDao reportDao;

    @Autowired
    public RestProjectController(UserDao userDao, ProjectDao projectDao, ConsistDao consistDao, DevConnectionActivityDao assignedUserDao, ReportDao reportDao) {
        this.userDao = userDao;
        this.projectDao = projectDao;
        this.consistDao = consistDao;
        this.assignedUserDao = assignedUserDao;
        this.reportDao = reportDao;
    }

    /**
     * THAT'S WORK
     * @apiNote method that creating a new Project with default parameters. He is get a json format project
     * form, and spring parse the form to object.
     * @param project the form to get from request. Need to save the params to database
     * @return response with the form when front end can to show, otherwise get internalServerError
     */
    @PostMapping(value = "/manager/project/create")
    public ResponseEntity<ProjectCreateForm> createProject(@Validated @RequestBody ProjectCreateForm project) {
        try {
            projectDao.saveProject(project.getEntityFromForm());
        } catch (Exception e) {
            log.info(LOG_TAG, e);
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(project);
    }

    @PostMapping("/manager/create/{projectId}")
    public ResponseEntity<ActivityCreateForm> saveActivity(@PathVariable String projectId, @RequestBody final ActivityCreateForm form) {
        Activity activity = projectDao.save(ActivityHandlerUtils.convertToEntity(form.getDtoFromForm()));
        log.info(form.toString());
//        consistDao.save(new Consist(activity.getId(), projectDao.findByProjectId(projectId).getProjectId()));
        return ResponseEntity.ok(form);
    }

    @DeleteMapping("/manager/project/delete")
    public ResponseEntity<?> deleteProject(@RequestParam Boolean isCorrect, @RequestParam final String projectId) {
        if (isCorrect) {
            projectDao.deleteProjectByProjectId(projectId);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @DeleteMapping("/manager/activity/delete")
    public ResponseEntity<?> deleteActivity(@RequestParam Boolean isCorrect, Long activityId) {
        if (isCorrect){
            projectDao.deleteActivityById(activityId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping(value = "activity/panel")
    public ResponseEntity<ProjectDto> getActivityPanel(@RequestParam String projectId) {
        ProjectDto body = projectDao.findByIdDto(projectId);
        if (body == null) ResponseEntity.notFound().build();
        return ResponseEntity.ok(body);
    }

    @PostMapping(value = "project/activity/assign/{activityId}")
    public ResponseEntity<DevConnectionActivity> assignDevelop(@PathVariable final Long activityId,
                                                                  @RequestParam final Long developId,
                                                                  @RequestParam String title) {
        UserDto userDto = UserHandlerUtils.getUserFromAuth(userDao);
        if (userDto.getRole() != Role.MANAGER)
            throw new ApiRequestException("Do not have permission");

        DevConnectionActivity assignation = new DevConnectionActivity();
        assignation.setActivityId(activityId);
        assignation.setUserId(developId);
        assignation.setTitle(title.isEmpty() ? "": title);
        assignedUserDao.save(assignation);

        return ResponseEntity.ok(assignation);
    }

    @GetMapping(value = "project/activity/assign/search")
    public ResponseEntity<List<UserDto>> searchDevelopForAssign(@RequestParam final String text) {
        UserDto userDto = UserHandlerUtils.getUserFromAuth(userDao);
        if (userDto.getRole() != Role.MANAGER)
            throw new ApiRequestException("Do not have permission");

        List<UserDto> users = userDao.findAllByEmailOrFullName(text, text, text);

        return ResponseEntity.ok(users);
    }


    @GetMapping("/get-time-details/{id}")
    public String getTimeDetails(@PathVariable Long id) {
        var reports = projectDao.findByActivityId(id);
        Integer totalSum = 0;
        for (var report : reports) {
            totalSum += report.getTime();
        }
        return convertTimeToString(totalSum);
    }


    private String convertTimeToString(Integer number) {
        int days = number / 60 / 24;
        int hours = number / 60 % 24;
        int minutes = number % 60;

        if (days != 0) {
            return String.format("%02d day(s) %02d hour(s) %02d minute(s)", days, hours, minutes);
        }
        return String.format("%02d hour(s) %02d minute(s)", hours, minutes);
    }

    @PostMapping("/get-color-activity-calendar")
    public ResponseEntity<?> getColorDay(@RequestBody ColorHandlerBody colorBody) {
        if (colorBody == null) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(colorBody.getColor());
    }
}

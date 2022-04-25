package kz.sdu.activitymonitoringsdu.controller;

import kz.sdu.activitymonitoringsdu.dao.ConsistDao;
import kz.sdu.activitymonitoringsdu.dao.ProjectDao;
import kz.sdu.activitymonitoringsdu.dao.UserDao;
import kz.sdu.activitymonitoringsdu.dto.ActivityDto;
import kz.sdu.activitymonitoringsdu.dto.ProjectDto;
import kz.sdu.activitymonitoringsdu.dto.UserDto;
import kz.sdu.activitymonitoringsdu.enums.Role;
import kz.sdu.activitymonitoringsdu.handlers.ProjectHandlerUtils;
import kz.sdu.activitymonitoringsdu.handlers.UserHandlerUtils;
import kz.sdu.activitymonitoringsdu.handlers.forms.ProjectCreateForm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Getter
@RequestMapping(path = "/project")
public class ProjectController {

    private final UserDao userDao;
    private final ProjectDao projectDao;
    private final ConsistDao consistDao;

    @Autowired
    public ProjectController(UserDao userDao, ProjectDao projectDao, ConsistDao consistDao) {
        this.userDao = userDao;
        this.projectDao = projectDao;
        this.consistDao = consistDao;
    }

    @GetMapping("/create")
    public ModelAndView createProject(ModelMap model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userEmail = ((UserDetails) principal).getUsername();

        UserDto userDto = userDao.findUserByEmailDto(userEmail);
        if (userDto.getRole() == Role.EMPLOYEE) {
            return new ModelAndView("redirect:/dashboard");
        }
        model.addAttribute("user", userDto);
        model.addAttribute("back_page", "/dashboard");

        // Adding project facade to place input
        model.addAttribute("project", new ProjectCreateForm());

        return new ModelAndView("new_project", model);
    }

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProjectCreateForm> createProject(@RequestBody ProjectCreateForm project) {
        if (project.getProjectVersion() == null) return ResponseEntity.internalServerError().build();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userEmail = ((UserDetails) principal).getUsername();

        UserDto userDto = userDao.findUserByEmailDto(userEmail);

        if (userDto.getRole() == Role.EMPLOYEE) {
            return null;
        }

        projectDao.saveProject(
                ProjectHandlerUtils.convertToEntity(
                        project.getDtoFromForm()));

        return ResponseEntity.ok(project);
    }

    @DeleteMapping(name = "/remove")
    public ModelAndView removeProject(@RequestParam Boolean isCorrect, @RequestParam String idProject) {
        UserDto userDto = UserHandlerUtils.getUserFromAuth(userDao);
        if (userDto.getRole() == Role.MANAGER && isCorrect) {
            projectDao.deleteByProjectId(idProject);
        }
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/details")
    public ModelAndView showDetails(@RequestParam final String id, ModelMap model) {
        UserDto userDto = UserHandlerUtils.getUserFromAuth(userDao);
        if (userDto.getRole() != Role.MANAGER) return new ModelAndView("redirect:/dashboard");
        ProjectDto projectDto = ProjectHandlerUtils.convertToDto(projectDao.findById(id));
        List<ActivityDto> activities = projectDao.getActivitiesById(consistDao.findAllByProjectId(projectDao.findById(id).getId()));

        List<Integer> spendTimeActivities = activities.stream().map(ActivityDto::getSpentTime).toList();

        var total_time = spendTimeActivities.stream().mapToInt(Integer::intValue).sum();
        var daily_time = total_time / spendTimeActivities.size();

        String total_time_text = convertTimeToString(total_time);
        String daily_time_text = convertTimeToString(daily_time);



        model.addAttribute("userIsManager", userDto.getRole() == Role.MANAGER);
        model.addAttribute("activities", activities);
        model.addAttribute("totalTime", activities.stream()
                .map(activityDto -> getTimeDetails(activityDto.getId()))
                .collect(Collectors.toList()));
        model.addAttribute("back_page", "/dashboard");
        model.addAttribute("user", userDto);
        model.addAttribute("project", projectDto);
        model.addAttribute("projectId", id);
        model.addAttribute("total_time", total_time_text);
        model.addAttribute("daily_time", daily_time_text);

        return new ModelAndView("project_details", model);
    }

    @GetMapping("/get-time-details/{id}")
    @ResponseBody
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

        if(days != 0) {
            return String.format("%02d day(s) %02d hour(s) %02d minute(s)", days, hours, minutes);
        }
        return String.format("%02d hour(s) %02d minute(s)", hours, minutes);
    }
}

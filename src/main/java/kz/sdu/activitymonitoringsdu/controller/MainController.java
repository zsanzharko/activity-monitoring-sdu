package kz.sdu.activitymonitoringsdu.controller;

import kz.sdu.activitymonitoringsdu.dao.ActivityDao;
import kz.sdu.activitymonitoringsdu.dao.ProjectDao;
import kz.sdu.activitymonitoringsdu.dao.UserDao;
import kz.sdu.activitymonitoringsdu.dto.ActivityDto;
import kz.sdu.activitymonitoringsdu.dto.ProjectDto;
import kz.sdu.activitymonitoringsdu.dto.UserDto;
import kz.sdu.activitymonitoringsdu.entity.Activity;
import kz.sdu.activitymonitoringsdu.enums.Role;
import kz.sdu.activitymonitoringsdu.handlers.ActivityHandlerUtils;
import kz.sdu.activitymonitoringsdu.handlers.ProjectHandlerUtils;
import kz.sdu.activitymonitoringsdu.handlers.UserHandlerUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

//SecurityContextHolder.getContext().getAuthentication().getPrincipal()

@Controller
@Getter
public class MainController {

    private final UserDao userDao;
    private final ProjectDao projectDao;
    private final ActivityDao activityDao;

    @Autowired
    public MainController(UserDao userDao, ProjectDao projectDao, ActivityDao activityDao) {
        this.userDao = userDao;
        this.projectDao = projectDao;
        this.activityDao = activityDao;
    }


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/")
    public String mainPage() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public ModelAndView getDashboardPanel(ModelMap model) {
        UserDto userDto = UserHandlerUtils.getUserFromAuth(userDao);
        List<ProjectDto> projects;

        // Manager can get all projects
        if (userDto.getRole() == Role.MANAGER) {
            projects = ProjectHandlerUtils.convertToDto(projectDao.findAll());
        } else {
            projects = ProjectHandlerUtils.convertToDto(projectDao.findAllByCreatorId(userDto.getId()));
        }

        for (ProjectDto projectDto : projects) {
            List<ActivityDto> subActivities = new ArrayList<>();
            int i = 0;
            for (Activity activity : activityDao.findAllByProjectId(projectDto.getProjectId())) {
                subActivities.add(ActivityHandlerUtils.convertToDto(activity));
                if (i > 3) break;
                i++;
            }
            projectDto.setActivities(subActivities);
        }

        model.addAttribute("user", userDto);
        model.addAttribute("projects", projects);

        return new ModelAndView("index", model);
    }
}

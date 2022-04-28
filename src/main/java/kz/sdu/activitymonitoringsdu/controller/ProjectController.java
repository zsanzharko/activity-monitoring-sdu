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
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/panel")
    public ModelAndView showProjectPanel(@RequestParam final String id, ModelMap model) {
        UserDto userDto = UserHandlerUtils.getUserFromAuth(userDao);
        if (userDto.getRole() != Role.MANAGER) return new ModelAndView("redirect:/dashboard");
        ProjectDto projectDto = ProjectHandlerUtils.convertToDto(projectDao.findByProjectId(id));
        List<ActivityDto> activities = projectDao.getActivitiesById(
                consistDao.findAllByProjectId(projectDao.findByProjectId(id).getProjectId()));


        model.addAttribute("titlePage", "Project: " + projectDto.getTitle());
        model.addAttribute("accessRole", userDto.getRole() == Role.MANAGER);
        model.addAttribute("activities", activities);
        model.addAttribute("back_page", "/dashboard");
        model.addAttribute("user", userDto);
        model.addAttribute("project", projectDto);
        model.addAttribute("projectId", id);

        return new ModelAndView("new_project_details2", model);
    }
}

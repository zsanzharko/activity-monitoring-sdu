package kz.sdu.activitymonitoringsdu.controller;

import kz.sdu.activitymonitoringsdu.dao.ActivityDao;
import kz.sdu.activitymonitoringsdu.dao.ConsistDao;
import kz.sdu.activitymonitoringsdu.dao.ProjectDao;
import kz.sdu.activitymonitoringsdu.dao.UserDao;
import kz.sdu.activitymonitoringsdu.dto.ActivityDto;
import kz.sdu.activitymonitoringsdu.dto.ProjectDto;
import kz.sdu.activitymonitoringsdu.dto.UserDto;
import kz.sdu.activitymonitoringsdu.entity.Activity;
import kz.sdu.activitymonitoringsdu.entity.Consist;
import kz.sdu.activitymonitoringsdu.enums.Role;
import kz.sdu.activitymonitoringsdu.handlers.ProjectHandlerUtils;
import kz.sdu.activitymonitoringsdu.handlers.UserHandlerUtils;
import kz.sdu.activitymonitoringsdu.handlers.forms.ProjectCreateForm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@Getter
@RequestMapping(path = "/project")
public class ProjectController {

    private final UserDao userDao;
    private final ProjectDao projectDao;
    private final ActivityDao activityDao;
    private final ConsistDao consistDao;

    @Autowired
    public ProjectController(UserDao userDao, ProjectDao projectDao, ActivityDao activityDao, ConsistDao consistDao) {
        this.userDao = userDao;
        this.projectDao = projectDao;
        this.activityDao = activityDao;
        this.consistDao = consistDao;
    }

    @GetMapping("/create")
    public ModelAndView createProject(ModelMap model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userEmail = ((UserDetails) principal).getUsername();

        UserDto userDto = userDao.findUserByEmailDto(userEmail);
        if(userDto.getRole() == Role.EMPLOYEE) {
            return new ModelAndView("redirect:/dashboard");
        }
        model.addAttribute("user", userDto);
        model.addAttribute("back_page", "/dashboard");

        // Adding project facade to place input
        model.addAttribute("project", new ProjectCreateForm());

        return new ModelAndView("new_project", model);
    }

    @PostMapping("/create")
    public String createProject( @ModelAttribute ProjectCreateForm project,
                                BindingResult bindingResult) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userEmail = ((UserDetails) principal).getUsername();

        UserDto userDto = userDao.findUserByEmailDto(userEmail);

        if(userDto.getRole() == Role.EMPLOYEE) {
            return "redirect:/dashboard";
        }
        if (bindingResult.hasErrors()) {
            return "redirect:/create";
        }

        projectDao.saveProject(
                ProjectHandlerUtils.convertToEntity(
                project.getDtoFromForm()));

        return "redirect:/dashboard";
    }

    @PostMapping(name = "/remove")
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
        ProjectDto projectDto = ProjectHandlerUtils.convertToDto(projectDao.findById(id));
        List<ActivityDto> activities = activityDao.getActivitiesById(consistDao.findAllByProjectId(projectDto.getProjectId()));

        model.addAttribute("userIsManager", userDto.getRole() == Role.MANAGER);
        model.addAttribute("activities", activities);
        model.addAttribute("back_page", "/dashboard");
        model.addAttribute("user", userDto);
        model.addAttribute("project", projectDto);
        return new ModelAndView("project_details", model);
    }
}

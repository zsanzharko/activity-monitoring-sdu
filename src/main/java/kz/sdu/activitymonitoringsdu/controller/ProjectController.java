package kz.sdu.activitymonitoringsdu.controller;

import kz.sdu.activitymonitoringsdu.dao.ActivityDao;
import kz.sdu.activitymonitoringsdu.dao.ProjectDao;
import kz.sdu.activitymonitoringsdu.dao.UserDao;
import kz.sdu.activitymonitoringsdu.dto.ProjectDto;
import kz.sdu.activitymonitoringsdu.dto.UserDto;
import kz.sdu.activitymonitoringsdu.entity.Activity;
import kz.sdu.activitymonitoringsdu.entity.Project;
import kz.sdu.activitymonitoringsdu.enums.ProjectStatus;
import kz.sdu.activitymonitoringsdu.enums.Role;
import kz.sdu.activitymonitoringsdu.handlers.ProjectHandlerUtils;
import kz.sdu.activitymonitoringsdu.handlers.UserHandlerUtils;
import kz.sdu.activitymonitoringsdu.handlers.forms.ProjectCreateForm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Controller
@Getter
@RequestMapping(path = "/project")
public class ProjectController {

    private final UserDao userDao;
    private final ProjectDao projectDao;
    private final ActivityDao activityDao;

    @Autowired
    public ProjectController(UserDao userDao, ProjectDao projectDao, ActivityDao activityDao) {
        this.userDao = userDao;
        this.projectDao = projectDao;
        this.activityDao = activityDao;
    }

    @GetMapping("/create")
    public ModelAndView createProject(ModelMap model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userEmail = ((UserDetails) principal).getUsername();

        UserDto userDto = userDao.findUserByEmailDto(userEmail);
        model.addAttribute("user", userDto);

        // Adding project facade to place input
        model.addAttribute("project", new ProjectCreateForm());

        return new ModelAndView("new_project", model);
    }

    @PostMapping("/create")
    public String createProject( @ModelAttribute ProjectCreateForm project,
                                BindingResult bindingResult) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (bindingResult.hasErrors()) {
            return "redirect:/create";
        }

        String userEmail = ((UserDetails) principal).getUsername();

        UserDto userDto = userDao.findUserByEmailDto(userEmail);

        projectDao.saveProject(ProjectHandlerUtils.convertToEntity(project.getDtoFromForm(userDto.getId())));

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
    public ModelAndView showDetails(@RequestParam final Long id, ModelMap model) {
        UserDto userDto = UserHandlerUtils.getUserFromAuth(userDao);
        ProjectDto projectDto = ProjectHandlerUtils.convertToDto(projectDao.findById(id));
        List<Activity> activities = activityDao.findAllByProjectId(projectDto.getProjectId());

        model.addAttribute("userIsManager", userDto.getRole() == Role.MANAGER);
        model.addAttribute("activities", activities);
        model.addAttribute("project", projectDto);
        return new ModelAndView("project_details", model);
    }
}

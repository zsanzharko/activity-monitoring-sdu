package kz.sdu.activitymonitoringsdu.controller;

import kz.sdu.activitymonitoringsdu.dao.ProjectDao;
import kz.sdu.activitymonitoringsdu.dao.UserDao;
import kz.sdu.activitymonitoringsdu.dto.ProjectDto;
import kz.sdu.activitymonitoringsdu.dto.UserDto;
import kz.sdu.activitymonitoringsdu.enums.Role;
import kz.sdu.activitymonitoringsdu.handlers.ProjectConverter;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//SecurityContextHolder.getContext().getAuthentication().getPrincipal()

@Controller
@Getter
public class MainController {

    private final UserDao userDao;
    private final ProjectDao projectDao;

    @Autowired
    public MainController(UserDao userDao, ProjectDao projectDao) {
        this.userDao = userDao;
        this.projectDao = projectDao;
    }

    @GetMapping("/")
    public String mainPage() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public ModelAndView getDashboardPanel(ModelMap model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userEmail = ((UserDetails) principal).getUsername();

        UserDto userDto = userDao.findUserByEmailDto(userEmail);
        model.addAttribute("user", userDto);
        List<ProjectDto> projects;

        // Manager can get all projects
        if (userDto.getRole() == Role.MANAGER) {
            projects = ProjectConverter.convertToDto(projectDao.findAll());
        } else {
            projects = ProjectConverter.convertToDto(projectDao.findAllByCreatorId(userDto.getId()));
        }
        model.addAttribute("projects", projects);

        return new ModelAndView("index", model);
    }
}

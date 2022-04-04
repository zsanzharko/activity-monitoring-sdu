package kz.sdu.activitymonitoringsdu.controller;

import kz.sdu.activitymonitoringsdu.dao.ProjectDao;
import kz.sdu.activitymonitoringsdu.dao.UserDao;
import kz.sdu.activitymonitoringsdu.dto.ProjectDto;
import kz.sdu.activitymonitoringsdu.dto.UserDto;
import kz.sdu.activitymonitoringsdu.entity.Project;
import kz.sdu.activitymonitoringsdu.enums.ProjectStatus;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
@Getter
@RequestMapping(path = "/project")
public class ProjectController {

    private final UserDao userDao;
    private final ProjectDao projectDao;

    @Autowired
    public ProjectController(UserDao userDao, ProjectDao projectDao) {
        this.userDao = userDao;
        this.projectDao = projectDao;
    }

    @GetMapping("/create")
    public ModelAndView createProject(ModelMap model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userEmail = ((UserDetails) principal).getUsername();

        UserDto userDto = userDao.findUserByEmailDto(userEmail);
        model.addAttribute("user", userDto);

        // Adding project facade to place input
        model.addAttribute("project", new ProjectDto());

        return new ModelAndView("new_project", model);
    }

    @PostMapping("/create")
    public ModelAndView saveProject(@ModelAttribute ProjectDto projectDto, ModelMap model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            String userEmail = ((UserDetails) principal).getUsername();

        UserDto userDto = userDao.findUserByEmailDto(userEmail);

//        {
//            Project project = new Project();
//            project.setProjectId(projectDto.getProjectId());
//            projectDto.setProjectVersion(projectDto.getProjectVersion());
//            project.setTitle(projectDto.getTitle());
//            project.setStartDate(projectDto.getStartDate());
//            project.setStatus(ProjectStatus.NOT_STARTED);
//            project.setExpectedTime("null");
//            project.setSpentTime("null");
//            project.setDescription("null");
//            project.setCreatorId(userDto.getId());
//            projectDao.saveProject(project);
//        }
        {
            Project project = new Project();
            project.setProjectId("9511LSP");
            project.setProjectVersion("A");
            project.setTitle("Android App");
            project.setStartDate(LocalDate.now());
            project.setStatus(ProjectStatus.NOT_STARTED);
            project.setExpectedTime("null");
            project.setSpentTime("null");
            project.setDescription("null");
            project.setCreatorId(userDto.getId());
            projectDao.saveProject(project);
        }

        return new ModelAndView("redirect:/dashboard", model);
    }
}

package kz.sdu.activitymonitoringsdu.controller;

import kz.sdu.activitymonitoringsdu.dao.ConsistDao;
import kz.sdu.activitymonitoringsdu.dao.DevConnectionActivityDao;
import kz.sdu.activitymonitoringsdu.dao.ProjectDao;
import kz.sdu.activitymonitoringsdu.dao.UserDao;
import kz.sdu.activitymonitoringsdu.dto.ActivityDto;
import kz.sdu.activitymonitoringsdu.dto.ProjectDto;
import kz.sdu.activitymonitoringsdu.dto.UserDto;
import kz.sdu.activitymonitoringsdu.entity.Consist;
import kz.sdu.activitymonitoringsdu.entity.DevConnectionActivity;
import kz.sdu.activitymonitoringsdu.enums.Role;
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

@Controller
@Getter
public class MainController {

    private final UserDao userDao;
    private final ProjectDao projectDao;
    private final ConsistDao consistDao;
    private final DevConnectionActivityDao assignDao;

    @Autowired
    public MainController(UserDao userDao, ProjectDao projectDao, ConsistDao consistDao, DevConnectionActivityDao assignDao) {
        this.userDao = userDao;
        this.projectDao = projectDao;
        this.consistDao = consistDao;
        this.assignDao = assignDao;
    }

    @GetMapping("/")
    public String mainPage() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public ModelAndView getDashboardPanel(ModelMap model) {
        UserDto userDto = UserHandlerUtils.getUserFromAuth(userDao);

        roleDefinitionDashboard(userDto, model);


        model.addAttribute("user", userDto);

        model.addAttribute("titlePage", "Dashboard");

        return new ModelAndView("index", model);
    }

    @GetMapping("/profile-panel")
    public ModelAndView getProfilePanel(ModelMap model) {

        return new ModelAndView("redirect:/dashboard");
    }

    private void roleDefinitionDashboard(final UserDto user, ModelMap modelMap) {
        if(user.getRole() == Role.MANAGER) {
            List<ProjectDto> projects = ProjectHandlerUtils.convertToDto(projectDao.findAll());
            for (ProjectDto projectDto : projects) {
                List<ActivityDto> subActivities = new ArrayList<>();

                List<ActivityDto> activities = projectDao.getActivitiesById(
                        consistDao.findAllByProjectId(projectDao.findByProjectId(projectDto.getProjectId()).getProjectId()));
                if(activities == null) activities = new ArrayList<>();
                for (int i = 0; i < 2 && !activities.isEmpty(); i++) {
                    if (i > activities.size() - 1) break;
                    subActivities.add(activities.get(i));
                }
                projectDto.setActivities(subActivities);
            }
            modelMap.addAttribute("projects", projects);
        } else if(user.getRole() == Role.EMPLOYEE) {
            List<DevConnectionActivity> assignedList = assignDao.findAllByUserIdAssign(user.getId());
            List<ActivityDto> activityDtoList = new ArrayList<>();
            List<Consist> consistList = new ArrayList<>();
            for (DevConnectionActivity assign : assignedList) {
                activityDtoList.add(projectDao.findById(assign.getActivityId()));
                consistList.add(consistDao.findById(assign.getActivityId()));
            }

            modelMap.addAttribute("activities", activityDtoList);
            modelMap.addAttribute("consists", consistList);
        }
    }
}

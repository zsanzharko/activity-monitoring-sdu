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

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        model.addAttribute("accessRole", userDto.getRole() == Role.MANAGER);

        model.addAttribute("titlePage", "Dashboard");

        return new ModelAndView("index", model);
    }

    @GetMapping("/profile-panel")
    public ModelAndView getProfilePanel(ModelMap model) {
        UserDto userDto = UserHandlerUtils.getUserFromAuth(userDao);
        List<UserDto> developers = userDao.findAllByRole(Role.EMPLOYEE);
        List<Integer> activityCount = developers.stream()
                .map(developer ->  assignDao.findAllByUserIdAssign(developer.getId()).size())
                .toList();
        List<Boolean> isActiveDevelopers = activityCount.stream()
                .map(count -> count >= 5)
                .toList();


        model.addAttribute("titlePage", "Developers");
        model.addAttribute("user", userDto);
        model.addAttribute("developers", developers);
        model.addAttribute("developersActivityCount", activityCount);
        model.addAttribute("developersActiveState", isActiveDevelopers);

        return new ModelAndView("profiles_page");
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
            modelMap.addAttribute("assigns", assignedList);
            modelMap.addAttribute("daily_count", getDayCount());

        }
    }

    private List<Integer> getDayCount() {
        var daily_count = new ArrayList<Integer>();
        var currentDay = LocalDate.now().getDayOfMonth();
        var lastDay = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        for (int i = 0; i < 15; i++) {
            if (currentDay <= lastDay)
                daily_count.add(currentDay);
            else
                daily_count.add(currentDay = 1);
            currentDay++;
        }
        return daily_count;
    }
}

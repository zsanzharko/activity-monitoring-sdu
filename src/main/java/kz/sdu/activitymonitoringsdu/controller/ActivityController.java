package kz.sdu.activitymonitoringsdu.controller;

import kz.sdu.activitymonitoringsdu.dao.*;
import kz.sdu.activitymonitoringsdu.dto.ActivityDto;
import kz.sdu.activitymonitoringsdu.dto.ProjectDto;
import kz.sdu.activitymonitoringsdu.dto.UserDto;
import kz.sdu.activitymonitoringsdu.entity.Activity;
import kz.sdu.activitymonitoringsdu.entity.Consist;
import kz.sdu.activitymonitoringsdu.entity.DevConnectionActivity;
import kz.sdu.activitymonitoringsdu.entity.Report;
import kz.sdu.activitymonitoringsdu.enums.Role;
import kz.sdu.activitymonitoringsdu.handlers.ActivityHandlerUtils;
import kz.sdu.activitymonitoringsdu.handlers.UserHandlerUtils;
import kz.sdu.activitymonitoringsdu.handlers.forms.ActivityCreateForm;
import kz.sdu.activitymonitoringsdu.handlers.forms.SpendTimeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/project/activity")
public class ActivityController {

    private final ProjectDao projectDao;
    private final DevConnectionActivityDao assignedUserDao;
    private final UserDao userDao;

    @Autowired
    public ActivityController(ProjectDao projectDao, DevConnectionActivityDao assignedUserDao, UserDao userDao) {
        this.projectDao = projectDao;
        this.assignedUserDao = assignedUserDao;
        this.userDao = userDao;
    }

    @GetMapping("/{projectId}/{id}")
    public ModelAndView detailsActivityPage(@PathVariable final String projectId, @PathVariable Long id, ModelMap modelMap) {
        UserDto userDto = UserHandlerUtils.getUserFromAuth(userDao);


        ActivityDto activityDto = projectDao.findById(id);
        List<Report> reportList = projectDao.findByActivityId(activityDto.getId());
        DevConnectionActivity assignedUserDto = assignedUserDao.getAssignedUserByActivityId(id);

        if (userDto.getRole() == Role.MANAGER) {
            List<UserDto> freeDevelopers = UserHandlerUtils
                    .convertToDto(userDao.findAllByRole(Role.EMPLOYEE));

            modelMap.addAttribute("developers", freeDevelopers);
        } else if (userDto.getRole() == Role.EMPLOYEE) {
            modelMap.addAttribute("spendTimeForm", new SpendTimeForm());
        }

        modelMap.addAttribute("assignedUser", assignedUserDto);
        modelMap.addAttribute("assignedUserName",
                assignedUserDto != null ?
                        userDao.findById(assignedUserDto.getUserId()).getFirstName() : "");
        modelMap.addAttribute("user", userDto);
        modelMap.addAttribute("activity", activityDto);
        modelMap.addAttribute("reports", reportList);
        modelMap.addAttribute("projectId", projectId);
        modelMap.addAttribute("back_page",
                "/project/panel?id=" + projectId);
        modelMap.addAttribute("id", id);

        return new ModelAndView("activity_details", modelMap);
    }

    @GetMapping("/assign/{id}/{activityId}/{projectId}")
    public String assignUserToActivity(@PathVariable final Long id,
                                       @PathVariable final Long activityId,
                                       @PathVariable final String projectId) {
        UserDto userDto = UserHandlerUtils.getUserFromAuth(userDao);
        if (userDto.getRole() == Role.EMPLOYEE) return "redirect:/dashboard";

        assignedUserDao.save(new DevConnectionActivity(activityId, id, ""));
        return String.format("redirect:/project/activity/%s/%s", projectId, activityId);
    }
}

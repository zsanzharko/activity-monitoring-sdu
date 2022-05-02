package kz.sdu.activitymonitoringsdu.controller;

import kz.sdu.activitymonitoringsdu.dao.*;
import kz.sdu.activitymonitoringsdu.dto.ActivityDto;
import kz.sdu.activitymonitoringsdu.dto.UserDto;
import kz.sdu.activitymonitoringsdu.entity.DevConnectionActivity;
import kz.sdu.activitymonitoringsdu.entity.Report;
import kz.sdu.activitymonitoringsdu.enums.Role;
import kz.sdu.activitymonitoringsdu.handlers.DateHandler;
import kz.sdu.activitymonitoringsdu.handlers.UserHandlerUtils;
import kz.sdu.activitymonitoringsdu.handlers.forms.SpendTimeForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@Slf4j
@RequestMapping(path = "/project/activity")
public class ActivityController {

    private final ProjectDao projectDao;
    private final DevConnectionActivityDao assignedUserDao;
    private final UserDao userDao;
    private final TimeReminderDao timeReminderDao;
    private final ReportDao reportDao;

    @Autowired
    public ActivityController(ProjectDao projectDao, DevConnectionActivityDao assignedUserDao, UserDao userDao, TimeReminderDao timeReminderDao, ReportDao reportDao) {
        this.projectDao = projectDao;
        this.assignedUserDao = assignedUserDao;
        this.userDao = userDao;
        this.timeReminderDao = timeReminderDao;
        this.reportDao = reportDao;
    }

    @GetMapping("/{projectId}/{id}")
    public ModelAndView detailsActivityPage(@PathVariable final String projectId, @PathVariable Long id, ModelMap modelMap) {
        UserDto userDto = UserHandlerUtils.getUserFromAuth(userDao);


        ActivityDto activityDto = projectDao.findById(id);
        List<Report> reportList = projectDao.findByActivityId(activityDto.getId());
        DevConnectionActivity assignedUserDto = assignedUserDao.getAssignedUserByActivityId(id);

        if (userDto.getRole() == Role.MANAGER) {
            List<UserDto> freeDevelopers = userDao.findAllByRole(Role.EMPLOYEE);

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

    @GetMapping("/assign/{activityId}/{projectId}")
    public String assignUserToActivity(@PathVariable final Long activityId,
                                       @PathVariable final String projectId) {
        UserDto userDto = UserHandlerUtils.getUserFromAuth(userDao);
        if (userDto.getRole() == Role.EMPLOYEE) return "redirect:/dashboard";

        assignedUserDao.save(new DevConnectionActivity(activityId, userDto.getId(), ""));
        return String.format("redirect:/project/activity/%s/%s", projectId, activityId);
    }

    @PostMapping("/{activityId}/push/{spendDateTime}")
    public ModelAndView pushTime(@PathVariable Long activityId,
                                 @PathVariable Long spendDateTime,
                                 @ModelAttribute final SpendTimeForm form) {
        var spendDate = new Date(spendDateTime - 10000L);
        spendDate.setSeconds(0);
        spendDate.setMinutes(0);
        spendDate.setHours(0);
        UserDto userDto = UserHandlerUtils.getUserFromAuth(userDao);
        log.info(String.format("activityId %s spendDate %s minutes %d",
                activityId, spendDate, form.getMinutes()
        ));
        Report report = new Report();

            report.setActivityId(activityId);
            report.setTime(form.getMinutes());
            report.setReportDate(spendDate);

            reportDao.save(report);

            log.info("Report saved");
        {
            var reminder = timeReminderDao.findTimeReminderByActivityIdAndDate(activityId, spendDate);
            if (reminder != null) {
                timeReminderDao.removeDevTimeReminderByIdAndActivityIdAndDateRemind(userDto.getId(), activityId, spendDate);
                log.info("Notification removed");
            }
        }

        return new ModelAndView("redirect:/dashboard");
    }
}

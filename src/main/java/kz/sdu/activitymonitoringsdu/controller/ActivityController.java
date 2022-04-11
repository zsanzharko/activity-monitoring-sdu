package kz.sdu.activitymonitoringsdu.controller;

import kz.sdu.activitymonitoringsdu.dao.ActivityDao;
import kz.sdu.activitymonitoringsdu.dao.DevConnectionActivityDao;
import kz.sdu.activitymonitoringsdu.dao.ProjectDao;
import kz.sdu.activitymonitoringsdu.dao.UserDao;
import kz.sdu.activitymonitoringsdu.dto.ActivityDto;
import kz.sdu.activitymonitoringsdu.dto.UserDto;
import kz.sdu.activitymonitoringsdu.entity.DevConnectionActivity;
import kz.sdu.activitymonitoringsdu.entity.Report;
import kz.sdu.activitymonitoringsdu.enums.Role;
import kz.sdu.activitymonitoringsdu.handlers.ActivityHandlerUtils;
import kz.sdu.activitymonitoringsdu.handlers.UserHandlerUtils;
import kz.sdu.activitymonitoringsdu.handlers.forms.ActivityCreateForm;
import kz.sdu.activitymonitoringsdu.handlers.forms.SpendTimeForm;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ActivityDao activityDao;
    private final DevConnectionActivityDao assignedUserDao;
    private final UserDao userDao;

    @Autowired
    public ActivityController(ProjectDao projectDao, ActivityDao activityDao, DevConnectionActivityDao assignedUserDao, UserDao userDao) {
        this.projectDao = projectDao;
        this.activityDao = activityDao;
        this.assignedUserDao = assignedUserDao;
        this.userDao = userDao;
    }

    @GetMapping("/{id}")
    public ModelAndView detailsActivityPage(@PathVariable Long id, ModelMap modelMap) {
        UserDto userDto = UserHandlerUtils.getUserFromAuth(userDao);


        ActivityDto activityDto = activityDao.findById(id);
        List<Report> reportList = activityDao.findByActivityId(activityDto.getId());
        DevConnectionActivity assignedUserDto = assignedUserDao.getAssignedUserByActivityId(id);

        if(userDto.getRole() == Role.MANAGER) {
            List<UserDto> freeDevelopers = UserHandlerUtils
                    .convertToDto(userDao.findAllByRole(Role.EMPLOYEE));

            modelMap.addAttribute("developers", freeDevelopers);
        } else if (userDto.getRole() == Role.EMPLOYEE){
            modelMap.addAttribute("spendTimeForm", new SpendTimeForm());
        }

        modelMap.addAttribute("assignedUser", assignedUserDto);
        modelMap.addAttribute("user", userDto);
        modelMap.addAttribute("activity", activityDto);
        modelMap.addAttribute("reports", reportList);

        modelMap.addAttribute("id", id);

        return new ModelAndView("activity_details", modelMap);
    }

    @PostMapping("/push")
    public ModelAndView pushSpendTime(@ModelAttribute SpendTimeForm form, ModelMap modelMap) {
        UserDto userDto = UserHandlerUtils.getUserFromAuth(userDao);

        if(userDto.getRole() == Role.MANAGER) {
            return new ModelAndView("redirect:/project/activity/" + modelMap.getAttribute("id"));
        }


        return new ModelAndView("redirect:/project/activity/" + modelMap.getAttribute("id"));
    }

    @GetMapping("/create")
    public ModelAndView createActivity(@RequestParam final Long id, ModelMap model) {
        //checking user is manager or not
        UserDto userDto = UserHandlerUtils.getUserFromAuth(userDao);
        if (!userDto.getRole().name().equals(Role.MANAGER.name()))
            return new ModelAndView("redirect:/");

        model.addAttribute("id", id);
        model.addAttribute("activity", new ActivityCreateForm());

        return new ModelAndView("new_activity", model);
    }

    @PostMapping("/create/{id}")
    public ModelAndView saveActivityProject(@PathVariable Long id,
                                            @ModelAttribute("activity") final ActivityCreateForm activityCreateForm,
                                            ModelMap model,
                                            BindingResult bindingResult) {
        //checking user is manager or not
        UserDto userDto = UserHandlerUtils.getUserFromAuth(userDao);
        if (userDto.getRole() != Role.MANAGER)
            return new ModelAndView("redirect:/");

        if (bindingResult.hasErrors()) {

            model.addAttribute("id", id);
            model.addAttribute("activity", new ActivityCreateForm());
            return new ModelAndView("redirect:/project/activity/create/" + id, model);
        }

        activityDao.save(ActivityHandlerUtils.convertToEntity(activityCreateForm.getDtoFromForm()));
        return new ModelAndView("redirect:/project/details?id=" + id);
    }
}

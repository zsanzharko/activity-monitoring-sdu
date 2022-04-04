package kz.sdu.activitymonitoringsdu.controller;

import kz.sdu.activitymonitoringsdu.dao.ActivityDao;
import kz.sdu.activitymonitoringsdu.dao.UserDao;
import kz.sdu.activitymonitoringsdu.dto.ActivityDto;
import kz.sdu.activitymonitoringsdu.enums.Role;
import kz.sdu.activitymonitoringsdu.handlers.ActivityHandlerUtils;
import kz.sdu.activitymonitoringsdu.handlers.UserHandlerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/project/activity")
public class ActivityController {

    private final ActivityDao activityDao;
    private final UserDao userDao;

    @Autowired
    public ActivityController(ActivityDao activityDao, UserDao userDao) {
        this.activityDao = activityDao;
        this.userDao = userDao;
    }

    @GetMapping("/create")
    public ModelAndView createActivity(@RequestParam final Long projectId, ModelMap model) {
        //checking user is manager or not
        if (UserHandlerUtils.getUserFromAuth(userDao).getRole() != Role.MANAGER) return new ModelAndView("redirect:/");

        model.addAttribute("projectId", projectId);
        model.addAttribute("activity", new ActivityDto());

        return new ModelAndView("new_activity");
    }

    @PostMapping("/create/{projectId}")
    public ModelAndView saveActivityProject(@PathVariable Long projectId, @ModelAttribute final ActivityDto activityDto, ModelMap model) {
        //checking user is manager or not
        if (UserHandlerUtils.getUserFromAuth(userDao).getRole() != Role.MANAGER) return new ModelAndView("redirect:/");

        activityDao.save(ActivityHandlerUtils.convertToEntity(activityDto));

        return new ModelAndView("redirect:/project/details?id=" + projectId);
    }

}

package kz.sdu.activitymonitoringsdu.controller;

import kz.sdu.activitymonitoringsdu.dao.ActivityDao;
import kz.sdu.activitymonitoringsdu.dao.ProjectDao;
import kz.sdu.activitymonitoringsdu.dao.UserDao;
import kz.sdu.activitymonitoringsdu.enums.Role;
import kz.sdu.activitymonitoringsdu.handlers.ActivityHandlerUtils;
import kz.sdu.activitymonitoringsdu.handlers.UserHandlerUtils;
import kz.sdu.activitymonitoringsdu.handlers.forms.ActivityCreateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/project/activity")
public class ActivityController {

    private final ProjectDao projectDao;
    private final ActivityDao activityDao;
    private final UserDao userDao;

    @Autowired
    public ActivityController(ProjectDao projectDao, ActivityDao activityDao, UserDao userDao) {
        this.projectDao = projectDao;
        this.activityDao = activityDao;
        this.userDao = userDao;
    }

    @GetMapping("/create")
    public ModelAndView createActivity(@RequestParam final Long id, ModelMap model) {
        //checking user is manager or not
        if (UserHandlerUtils.getUserFromAuth(userDao).getRole() != Role.MANAGER) return new ModelAndView("redirect:/");

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
        if (UserHandlerUtils.getUserFromAuth(userDao).getRole() != Role.MANAGER) return new ModelAndView("redirect:/");

        if (bindingResult.hasErrors()) {

            model.addAttribute("id", id);
            model.addAttribute("activity", new ActivityCreateForm());
            return new ModelAndView("redirect:/project/activity/create/" + id, model);
        }

        activityCreateForm.setProjectId(projectDao.findById(id).getProjectId());

        activityDao.save(ActivityHandlerUtils.convertToEntity(activityCreateForm.getDtoFromForm()));
        return new ModelAndView("redirect:/project/details?id=" + id);
    }

}

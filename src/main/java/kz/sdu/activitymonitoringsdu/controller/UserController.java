package kz.sdu.activitymonitoringsdu.controller;

import kz.sdu.activitymonitoringsdu.dao.UserDao;
import kz.sdu.activitymonitoringsdu.dto.UserDto;
import kz.sdu.activitymonitoringsdu.handlers.UserHandlerUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Getter
public class UserController {

    private final UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/profile")
    public ModelAndView profilePage(ModelMap modelMap) {
        UserDto userDto = UserHandlerUtils.getUserFromAuth(userDao);
        modelMap.addAttribute("user", userDto);

        modelMap.addAttribute("page_title", userDto.getFullName() + " profile");

        return  new ModelAndView("profile_page", modelMap);
    }
}

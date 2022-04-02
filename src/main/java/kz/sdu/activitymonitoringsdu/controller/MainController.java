package kz.sdu.activitymonitoringsdu.controller;

import kz.sdu.activitymonitoringsdu.dao.UserDao;
import kz.sdu.activitymonitoringsdu.dto.UserDto;
import kz.sdu.activitymonitoringsdu.enums.Gender;
import kz.sdu.activitymonitoringsdu.enums.Role;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@Getter
public class MainController {

    private final UserDao userDao;

    @Autowired
    public MainController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        boolean isAuthorised = SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
        System.out.println(isAuthorised);
        if (isAuthorised) {
            return "redirect:/admin";
        }
        model.addAttribute("user", new UserDto());
        return "home";
    }

    @GetMapping("/admin")
    public ModelAndView getAdminPanel(ModelMap model) {
        UserDto userDto = new UserDto(
                1L, "Sanzhar Zhanibekov", Gender.MALE, "root", Role.USER
        );

        model.addAttribute("user", userDto);
        return new ModelAndView("home", model);
    }


    @GetMapping("/user")
    public ModelAndView getUserPanel(ModelMap model) {
        model.addAttribute("user", new UserDto());
        return new ModelAndView("home", model);
    }
}

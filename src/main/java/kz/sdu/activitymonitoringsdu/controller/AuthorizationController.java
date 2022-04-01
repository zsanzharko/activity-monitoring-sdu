package kz.sdu.activitymonitoringsdu.controller;

import kz.sdu.activitymonitoringsdu.dao.UserDao;
import kz.sdu.activitymonitoringsdu.entity.LoginForm;
import kz.sdu.activitymonitoringsdu.entity.User;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Getter
public class AuthorizationController {

    private final UserDao userDao;

    @Autowired
    public AuthorizationController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/login")
    public ModelAndView authPage(ModelMap model) {
        model.addAttribute("form", new LoginForm());
        return new ModelAndView("authorization", model);
    }

    @PostMapping("/auth")
    public String authorize(@ModelAttribute LoginForm loginForm, ModelMap model) {
        User user = userDao.findUserByEmailAndPassword(
                loginForm.getEmail(),
                loginForm.getPassword()
        );
        if (user == null)
            return "redirect:/login";
        model.addAttribute("user", user);
        return "redirect:/";
    }
}

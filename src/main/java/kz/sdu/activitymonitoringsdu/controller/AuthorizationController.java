package kz.sdu.activitymonitoringsdu.controller;

import kz.sdu.activitymonitoringsdu.dao.UserDao;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Getter
public class AuthorizationController {

    private final UserDao userDao;

    @Autowired
    public AuthorizationController(UserDao userDao) {
        this.userDao = userDao;
    }

//    @ModelAttribute("employees")
//    public void init() {
//        authUsers.add(new UserDto(100L, "Sanzhar Zhanibekov", Gender.MALE,
//                "root", TypeUser.EMPLOYEE));
//    }

//    @GetMapping("/login")
//    public ModelAndView authPage(ModelMap model) {
//        model.addAttribute("form", new LoginForm());
//        return new ModelAndView("login", model);
//    }
//
//    @PostMapping("/login")
//    public String authorize(@ModelAttribute LoginForm loginForm, Model model) {
//        User user = userDao.findUserByEmailAndPassword(
//                loginForm.getEmail(),
//                loginForm.getPassword()
//        );
//        if (user == null)
//            return "login";
//        model.addAttribute("userDto", new UserConverter().convertToDto(user));
//        return "redirect:/home";
//    }
}

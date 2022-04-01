package kz.sdu.activitymonitoringsdu.controller;

import kz.sdu.activitymonitoringsdu.dao.UserDao;
import kz.sdu.activitymonitoringsdu.dto.UserDto;
import kz.sdu.activitymonitoringsdu.entity.LoginForm;
import kz.sdu.activitymonitoringsdu.entity.User;
import kz.sdu.activitymonitoringsdu.enums.Gender;
import kz.sdu.activitymonitoringsdu.enums.TypeUser;
import kz.sdu.activitymonitoringsdu.handlers.UserConverter;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@Getter
public class AuthorizationController {

    private final List<UserDto> authUsers = new ArrayList<>();

    private final ModelMapper modelMapper;
    private final UserDao userDao;

    @Autowired
    public AuthorizationController(UserDao userDao, ModelMapper modelMapper) {
        this.userDao = userDao;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("employees")
    public void init() {
        authUsers.add(new UserDto(100L, "Sanzhar", "Zhanibekov", Gender.MALE,
                "root", TypeUser.EMPLOYEE));
    }

    @GetMapping("/login")
    public ModelAndView authPage(ModelMap model) {
        model.addAttribute("form", new LoginForm());
        return new ModelAndView("authorization", model);
    }

    @PostMapping("/auth")
    public String authorize(@ModelAttribute LoginForm loginForm, Model model) {
        User user = userDao.findUserByEmailAndPassword(
                loginForm.getEmail(),
                loginForm.getPassword()
        );
        if (user == null)
            return "redirect:/login";
        model.addAttribute("userDto", new UserConverter().convertToDto(user));
        return "redirect:/home";
    }
}

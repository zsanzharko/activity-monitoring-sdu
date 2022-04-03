package kz.sdu.activitymonitoringsdu.controller;

import kz.sdu.activitymonitoringsdu.dao.UserDao;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//SecurityContextHolder.getContext().getAuthentication().getPrincipal()

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
        return "redirect:/dashboard";
    }
}

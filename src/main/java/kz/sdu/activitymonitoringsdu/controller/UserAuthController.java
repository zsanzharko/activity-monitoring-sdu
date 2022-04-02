package kz.sdu.activitymonitoringsdu.controller;

import kz.sdu.activitymonitoringsdu.dao.UserDao;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Getter
public class UserAuthController {

    private final UserDao userDao;

    @Autowired
    public UserAuthController(UserDao userDao) {
        this.userDao = userDao;
    }

}

package kz.sdu.activitymonitoringsdu.controller.rest;

import kz.sdu.activitymonitoringsdu.dao.UserDao;
import kz.sdu.activitymonitoringsdu.dto.UserDto;
import kz.sdu.activitymonitoringsdu.enums.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(name = "/api", consumes = "application/json", produces = "application/json")
public class RestAccountController {

    private final UserDao userDao;

    @Autowired
    public RestAccountController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/manager/get-developers")
    public ResponseEntity<List<UserDto>> getDevelopers() {
        List<UserDto> developers =  userDao.findAllByRole(Role.EMPLOYEE);
        return ResponseEntity.ok(developers);
    }
}

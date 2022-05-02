package kz.sdu.activitymonitoringsdu.controller.rest;

import kz.sdu.activitymonitoringsdu.dao.DevConnectionActivityDao;
import kz.sdu.activitymonitoringsdu.dao.UserDao;
import kz.sdu.activitymonitoringsdu.dto.UserDto;
import kz.sdu.activitymonitoringsdu.entity.DevConnectionActivity;
import kz.sdu.activitymonitoringsdu.enums.Role;
import kz.sdu.activitymonitoringsdu.handlers.body.UserAssignBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(name = "/api", consumes = "application/json", produces = "application/json")
public class RestAccountController {

    private final UserDao userDao;
    private final DevConnectionActivityDao assgnDao;

    @Autowired
    public RestAccountController(UserDao userDao, DevConnectionActivityDao assgnDao) {
        this.userDao = userDao;
        this.assgnDao = assgnDao;
    }

    @GetMapping("/manager/get-developers")
    public ResponseEntity<List<UserDto>> getDevelopers() {
        List<UserDto> developers =  userDao.findAllByRole(Role.EMPLOYEE);
        return ResponseEntity.ok(developers);
    }

    @GetMapping("/assignation")
    public ResponseEntity<List<UserAssignBody>> getAccountToAssign() {
        List<UserDto> developers = userDao.findAllByRole(Role.EMPLOYEE);
        if (developers == null) return ResponseEntity.notFound().build();
        List<UserAssignBody> assignBodyList = new ArrayList<>();

        for (UserDto developer : developers) {
            List<DevConnectionActivity> assignation = assgnDao.findAllByUserIdAssign(developer.getId());
            if (assignation != null && assignation.size() < 5) {
                assignBodyList.add(new UserAssignBody(developer.getId(), developer.getFullName(), assignation.size()));
            }
        }
        return ResponseEntity.ok(assignBodyList);
    }
}

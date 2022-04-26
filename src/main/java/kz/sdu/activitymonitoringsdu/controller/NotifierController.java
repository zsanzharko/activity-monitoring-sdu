package kz.sdu.activitymonitoringsdu.controller;

import kz.sdu.activitymonitoringsdu.dao.ConsistDao;
import kz.sdu.activitymonitoringsdu.dao.ProjectDao;
import kz.sdu.activitymonitoringsdu.dao.TimeReminderDao;
import kz.sdu.activitymonitoringsdu.dao.UserDao;
import kz.sdu.activitymonitoringsdu.dto.UserDto;
import kz.sdu.activitymonitoringsdu.handlers.UserHandlerUtils;
import kz.sdu.activitymonitoringsdu.handlers.forms.NotifyTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notify")
public class NotifierController {

    private final TimeReminderDao reminderDao;
    private final UserDao userDao;
    private final ProjectDao projectDao;
    private final ConsistDao consistDao;

    @Autowired
    public NotifierController(TimeReminderDao reminderDao, UserDao userDao, ProjectDao projectDao, ConsistDao consistDao) {
        this.reminderDao = reminderDao;
        this.userDao = userDao;
        this.projectDao = projectDao;
        this.consistDao = consistDao;
    }

    @GetMapping("/")
    public List<NotifyTemplate> getTestStringNotifier() {
        UserDto userDto = UserHandlerUtils.getUserFromAuth(userDao);
        var userReminders = reminderDao.findTimeRemindersByUserId(userDto.getId());
        var activities = reminderDao.getTitleFromActivity(userReminders);
        List<NotifyTemplate> notifyTemplates = userReminders.stream()
                .map(reminder -> new NotifyTemplate(
                        reminder.getDateRemind(),
                        activities.get(reminder.getActivityId()),
                        "project/activity/" +
                                consistDao.findById(reminder.getActivityId()).getProjectId() +
                                "/" + reminder.getActivityId()
                )).collect(Collectors.toList());
        return notifyTemplates;
    }

    @PostMapping("/update")
    public void updateNotify() {

    }

}

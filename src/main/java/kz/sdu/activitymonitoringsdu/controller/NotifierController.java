package kz.sdu.activitymonitoringsdu.controller;

import kz.sdu.activitymonitoringsdu.handlers.forms.NotifyTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/notify")
public class NotifierController {


    @GetMapping("/")
    public List<NotifyTemplate> getTestStringNotifier() {
        var array = new ArrayList<NotifyTemplate>();
        for (int i = 0; i < (int) (Math.random() * 10); i++) {
            array.add(new NotifyTemplate(new Date(System.currentTimeMillis()), (i + 1) + ". Test"));
        }
        return array;
    }

    @PostMapping("/update")
    public void updateNotify() {

    }

}

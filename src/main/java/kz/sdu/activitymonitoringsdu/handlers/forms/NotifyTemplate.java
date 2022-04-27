package kz.sdu.activitymonitoringsdu.handlers.forms;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class NotifyTemplate implements Serializable {
    private Date startDate;
    private String title;
    private String description;

    private String link;
}

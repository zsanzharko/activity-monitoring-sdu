package kz.sdu.activitymonitoringsdu.handlers.forms;

import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
public class SpendTimeForm {

    private Integer minutes;
    private Date dateStart = Date.from(Instant.now());
}

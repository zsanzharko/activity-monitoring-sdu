package kz.sdu.activitymonitoringsdu.handlers.forms;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Data
public class SpendTimeForm implements Serializable {
    private Integer minutes;
}

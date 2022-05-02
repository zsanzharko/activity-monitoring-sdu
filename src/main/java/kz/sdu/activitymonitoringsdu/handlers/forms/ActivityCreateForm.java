package kz.sdu.activitymonitoringsdu.handlers.forms;

import kz.sdu.activitymonitoringsdu.dto.ActivityDto;
import kz.sdu.activitymonitoringsdu.enums.ActivityStatus;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ActivityCreateForm {
    private String title;
    private String description;
    private ActivityStatus status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    public ActivityDto getDtoFromForm() {
        ActivityDto activityDto = new ActivityDto();
        activityDto.setTitle(title);
        activityDto.setDescription(description);
        activityDto.setStatus(status);
        activityDto.setStartDate(startDate);
        activityDto.setSpentTime(0);
        activityDto.setExpectedTime(0);
        return activityDto;
    }
}

package kz.sdu.activitymonitoringsdu.handlers.forms;

import kz.sdu.activitymonitoringsdu.dto.ActivityDto;
import kz.sdu.activitymonitoringsdu.enums.ActivityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityCreateForm {
    private String projectId;
    private String title;
    private String description;
    private ActivityStatus status;
    private LocalDate startDate;

    public ActivityDto getDtoFromForm() {
        ActivityDto activityDto = new ActivityDto();
        activityDto.setProjectId(projectId);
        activityDto.setTitle(description);
        activityDto.setDescription(activityDto.getDescription());
        activityDto.setStatus(status);
        activityDto.setStartDate(startDate);
        activityDto.setSpentTime("0");
        return activityDto;
    }
}

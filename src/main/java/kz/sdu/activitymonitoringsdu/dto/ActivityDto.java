package kz.sdu.activitymonitoringsdu.dto;

import kz.sdu.activitymonitoringsdu.enums.ActivityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDto implements Serializable {
    private Long id;
    private String title;
    private String description;
    private ActivityStatus status;
    private Date startDate;
    private Date endDate;
    private Integer spentTime;
    private Integer expectedTime;

    public String getStartDateString() {
        if (startDate == null) {
            return "Not set";
        } else return startDate.toString();
    }

    public String getEndDateString() {
        if (endDate == null) {
            return "Not set";
        } else return endDate.toString();
    }
}

package kz.sdu.activitymonitoringsdu.dto;

import kz.sdu.activitymonitoringsdu.enums.ActivityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDto implements Serializable {
    private Long id;
    private String title;
    private String description;
    private ActivityStatus status;
    private Date startDate;
    private Integer spentTime;
    private Integer expectedTime;

    public String getDate() {
        if (startDate == null) {
            return "Not set";
        } else return startDate.toString();
    }
}

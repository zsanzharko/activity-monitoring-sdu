package kz.sdu.activitymonitoringsdu.dto;

import kz.sdu.activitymonitoringsdu.enums.ActivityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDto implements Serializable {
    private Long id;
    private String projectId;
    private String title; //
    private String description;
    private ActivityStatus status;
    private LocalDate startDate;
    private String spentTime;
}

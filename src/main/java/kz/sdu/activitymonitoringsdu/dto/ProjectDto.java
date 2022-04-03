package kz.sdu.activitymonitoringsdu.dto;

import kz.sdu.activitymonitoringsdu.enums.ProjectStatus;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ProjectDto implements Serializable {
    private String projectId;
    private String projectVersion;
    private Long creatorId;
    private String title;
    private String description;
    private ProjectStatus status;
    private LocalDate startDate;
    private String expectedTime;
    private String spentTime;
}

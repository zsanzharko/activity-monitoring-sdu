package kz.sdu.activitymonitoringsdu.dto;

import kz.sdu.activitymonitoringsdu.enums.ProjectStatus;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class ProjectDto implements Serializable {
    private Long id;
    private String projectId;
    private String projectVersion;
    private Long creatorId;
    private String title;
    private String description;
    private ProjectStatus status;
    private Date startDate;
    private String expectedTime;
    private String spentTime;

    private List<ActivityDto> activities;
}

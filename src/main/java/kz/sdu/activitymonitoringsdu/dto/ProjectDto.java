package kz.sdu.activitymonitoringsdu.dto;

import kz.sdu.activitymonitoringsdu.entity.Report;
import kz.sdu.activitymonitoringsdu.entity.User;
import kz.sdu.activitymonitoringsdu.enums.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDto implements Serializable {
    private String projectId;
    private String projectVersion;
    private String title;
    private String description;
    private ProjectStatus status;
    private Date startDate;
    private Integer expectedTime;
    private Integer spentTime;

    private List<ActivityDto> activities;
    private Map<Long, List<Report>> reports;
    private Map<Long, User> devConnectionActivities;
}

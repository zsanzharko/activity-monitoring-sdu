package kz.sdu.activitymonitoringsdu.handlers.forms;

import kz.sdu.activitymonitoringsdu.dto.ProjectDto;
import kz.sdu.activitymonitoringsdu.enums.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectCreateForm {
    private String projectId;
    private String projectVersion;
    private String title;
    private String description;
    private LocalDate startDate;

    public ProjectDto getDtoFromForm(Long creatorId) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectId(projectId);
        projectDto.setProjectVersion(projectVersion);
        projectDto.setCreatorId(creatorId);
        projectDto.setTitle(title);
        projectDto.setDescription(description);
        projectDto.setStatus(ProjectStatus.NOT_STARTED);
        projectDto.setStartDate(startDate);
        projectDto.setExpectedTime("0");
        projectDto.setSpentTime("0");

        projectDto.setActivities(new ArrayList<>());
        return projectDto;
    }
}

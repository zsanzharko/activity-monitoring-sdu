package kz.sdu.activitymonitoringsdu.handlers.forms;

import kz.sdu.activitymonitoringsdu.dto.ProjectDto;
import kz.sdu.activitymonitoringsdu.enums.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectCreateForm {
    private String projectId = generateId();
    private String projectVersion;
    private String title;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    public ProjectDto getDtoFromForm() {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectId(projectId);
        projectDto.setProjectVersion(projectVersion);
        projectDto.setTitle(title);
        projectDto.setDescription(description);
        projectDto.setStatus(ProjectStatus.NOT_STARTED);
        projectDto.setStartDate(startDate);
        projectDto.setExpectedTime("0");
        projectDto.setSpentTime("0");

        projectDto.setActivities(new ArrayList<>());
        return projectDto;
    }

    private String generateId() {
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            if(i < 4) {
                id.append((int) (Math.random() * 10));
            } else {
                id.append((char) (65 +Math.random() * 90));
            }
        }
        return id.toString();
    }

    public void regenerateId() {
        projectId = generateId();
    }
}

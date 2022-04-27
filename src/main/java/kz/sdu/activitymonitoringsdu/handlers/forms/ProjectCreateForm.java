package kz.sdu.activitymonitoringsdu.handlers.forms;

import kz.sdu.activitymonitoringsdu.dto.ProjectDto;
import kz.sdu.activitymonitoringsdu.entity.Project;
import kz.sdu.activitymonitoringsdu.enums.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectCreateForm {
    private String projectId;
    private String projectVersion;
    private String title;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    public Project getEntityFromForm() {
        return Project.builder()
                .projectId(projectId)
                .projectVersion(projectVersion)
                .title(title)
                .description(description)
                .startDate(startDate)
                .expectedTime(0)
                .spentTime(0)
                .build();
    }

    private String generateId() {
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            if(i < 4) {
                id.append((int) (Math.random() * 10));
            } else {
                id.append((char) ((int) (65 + Math.random() * 25)));
            }
        }
        return id.toString();
    }

    public void regenerateId() {
        projectId = generateId();
    }
}

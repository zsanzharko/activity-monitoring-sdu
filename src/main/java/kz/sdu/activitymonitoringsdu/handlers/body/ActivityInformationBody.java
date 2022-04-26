package kz.sdu.activitymonitoringsdu.handlers.body;

import kz.sdu.activitymonitoringsdu.dto.ProjectDto;
import kz.sdu.activitymonitoringsdu.entity.DevConnectionActivity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ActivityInformationBody implements Serializable {
    private ProjectDto project;
    private List<DevConnectionActivity> devConnectionActivities;
}

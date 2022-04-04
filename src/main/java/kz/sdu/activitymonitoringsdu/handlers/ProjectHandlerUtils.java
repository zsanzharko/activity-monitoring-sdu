package kz.sdu.activitymonitoringsdu.handlers;

import kz.sdu.activitymonitoringsdu.dto.ProjectDto;
import kz.sdu.activitymonitoringsdu.entity.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectHandlerUtils {

    public static ProjectDto convertToDto(Project project) {
        return converting(project);
    }

    public static List<ProjectDto> convertToDto(List<Project> projects) {
        List<ProjectDto> projectDtos = new ArrayList<>(projects.size());
        for (Project project : projects)
            projectDtos.add(converting(project));
        return projectDtos;
    }

    private static ProjectDto converting(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setProjectId(project.getProjectId());
        projectDto.setProjectVersion(project.getProjectVersion());
        projectDto.setCreatorId(project.getCreatorId());
        projectDto.setTitle(project.getTitle());
        projectDto.setDescription(project.getDescription());
        projectDto.setStatus(project.getStatus());
        projectDto.setStartDate(project.getStartDate());
        projectDto.setExpectedTime(project.getExpectedTime());
        projectDto.setSpentTime(project.getSpentTime());
        return projectDto;
    }
}

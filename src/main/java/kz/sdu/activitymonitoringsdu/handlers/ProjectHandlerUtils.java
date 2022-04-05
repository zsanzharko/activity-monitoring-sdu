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

    public static Project convertToEntity(ProjectDto project) {
        return converting(project);
    }

    public static List<Project> convertToEntity(List<ProjectDto> projectDtos) {
        List<Project> projects = new ArrayList<>(projectDtos.size());
        for (ProjectDto project : projectDtos)
            projects.add(converting(project));
        return projects;
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

    private static Project converting(ProjectDto projectDto) {
        Project project = new Project();
        project.setProjectId(projectDto.getProjectId());
        project.setProjectVersion(projectDto.getProjectVersion());
        project.setCreatorId(projectDto.getCreatorId());
        project.setTitle(projectDto.getTitle());
        project.setDescription(projectDto.getDescription());
        project.setStatus(projectDto.getStatus());
        project.setStartDate(projectDto.getStartDate());
        project.setExpectedTime(project.getExpectedTime());
        project.setProjectId(project.getSpentTime());

        return project;
    }
}

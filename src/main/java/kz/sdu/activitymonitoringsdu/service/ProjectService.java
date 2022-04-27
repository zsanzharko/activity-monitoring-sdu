package kz.sdu.activitymonitoringsdu.service;

import kz.sdu.activitymonitoringsdu.entity.Project;

import java.util.List;

public interface ProjectService {

    Project findByProjectId(String id);

    void deleteProjectByProjectId(String projectId);

    Project saveProject(Project project);

    List<Project> findAll();
}

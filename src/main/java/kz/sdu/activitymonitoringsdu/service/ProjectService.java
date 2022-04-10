package kz.sdu.activitymonitoringsdu.service;

import kz.sdu.activitymonitoringsdu.entity.Project;

import java.util.List;

public interface ProjectService {

    Project findById(String id);

    void deleteByProjectId(String projectId);

    Project saveProject(Project project);

    List<Project> findAll();
}

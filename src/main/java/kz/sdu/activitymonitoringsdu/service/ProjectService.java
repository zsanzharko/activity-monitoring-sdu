package kz.sdu.activitymonitoringsdu.service;

import kz.sdu.activitymonitoringsdu.entity.Project;

import java.util.List;

public interface ProjectService {

    Project findById(Long id);

    Project findByProjectId(String projectId);

    List<Project> findAllByCreatorId(Long creatorId);

    void deleteByProjectId(String projectId);

    void deleteAllByCreatorId(Long creatorId);

    Project saveProject(Project project);

    List<Project> findAll();
}

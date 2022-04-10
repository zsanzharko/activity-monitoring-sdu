package kz.sdu.activitymonitoringsdu.dao;

import kz.sdu.activitymonitoringsdu.entity.Project;
import kz.sdu.activitymonitoringsdu.repository.ProjectRepository;
import kz.sdu.activitymonitoringsdu.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectDao implements ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectDao(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project findById(String id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteByProjectId(String projectId) {
        projectRepository.deleteByProjectId(projectId);
    }

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }
}

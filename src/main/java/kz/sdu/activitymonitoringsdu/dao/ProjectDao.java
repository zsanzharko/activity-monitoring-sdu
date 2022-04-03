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
    public Project findById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public Project findByProjectId(String projectId) {
        return projectRepository.findByProjectId(projectId);
    }

    @Override
    public List<Project> findAllByCreatorId(Long creatorId) {
        return projectRepository.findAllByCreatorId(creatorId);
    }

    @Override
    public void deleteByProjectId(String projectId) {
        projectRepository.deleteByProjectId(projectId);
    }

    @Override
    public void deleteAllByCreatorId(Long creatorId) {
        projectRepository.deleteAllByCreatorId(creatorId);
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

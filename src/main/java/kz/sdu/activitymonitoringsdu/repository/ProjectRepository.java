package kz.sdu.activitymonitoringsdu.repository;

import kz.sdu.activitymonitoringsdu.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findByProjectId(String projectId);

    List<Project> findAllByCreatorId(Long creatorId);

    void deleteByProjectId(String projectId);

    void deleteAllByCreatorId(Long creatorId);
}
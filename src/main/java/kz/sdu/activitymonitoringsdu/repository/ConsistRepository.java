package kz.sdu.activitymonitoringsdu.repository;

import kz.sdu.activitymonitoringsdu.entity.Consist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsistRepository extends JpaRepository<Consist, Long> {

    Consist findConsistByProjectId(Long projectId);

    List<Consist> findByProjectId(Long projectId);

    List<Consist> findAllByActivityId(Long activityId);
}
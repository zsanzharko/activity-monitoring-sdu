package kz.sdu.activitymonitoringsdu.repository;

import kz.sdu.activitymonitoringsdu.entity.Consist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ConsistRepository extends JpaRepository<Consist, Long> {

    Consist findConsistByProjectId(String projectId);

    List<Consist> findByProjectId(String projectId);

    List<Consist> findAllByActivityId(Long activityId);
}
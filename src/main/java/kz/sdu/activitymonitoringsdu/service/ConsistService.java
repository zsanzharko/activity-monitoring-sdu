package kz.sdu.activitymonitoringsdu.service;

import kz.sdu.activitymonitoringsdu.entity.Consist;

import java.util.List;

public interface ConsistService {
    Consist findById(Long id);
    Consist findByProjectId(String projectId);
    List<Consist> findAllByProjectId(String projectId);
    void save(Consist consist);

    void deleteAllByProjectId(String projectId);

    void deleteByActivityId(Long activityId);
}

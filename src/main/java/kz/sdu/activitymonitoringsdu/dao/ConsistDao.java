package kz.sdu.activitymonitoringsdu.dao;

import kz.sdu.activitymonitoringsdu.entity.Consist;
import kz.sdu.activitymonitoringsdu.repository.ConsistRepository;
import kz.sdu.activitymonitoringsdu.service.ConsistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsistDao implements ConsistService {

    private final ConsistRepository consistRepository;

    @Autowired
    public ConsistDao(ConsistRepository consistRepository) {
        this.consistRepository = consistRepository;
    }

    @Override
    public Consist findById(Long id) {
        return consistRepository.findById(id).orElse(null);
    }

    @Override
    public Consist findByProjectId(String projectId) {
        return consistRepository.findConsistByProjectId(projectId);
    }

    @Override
    public List<Consist> findAllByProjectId(String projectId){
        return consistRepository.findByProjectId(projectId);
    }

    @Override
    public void save(Consist consist) {
        consistRepository.save(consist);
    }

    @Override
    public void deleteAllByProjectId(String projectId) {
        consistRepository.deleteAllByProjectId(projectId);
    }

    @Override
    public void deleteByActivityId(Long activityId) {
        consistRepository.deleteById(activityId);
    }
}

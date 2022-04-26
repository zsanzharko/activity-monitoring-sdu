package kz.sdu.activitymonitoringsdu.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "consist")
@AllArgsConstructor
@NoArgsConstructor
public class Consist {
    @Id
    @Column(name = "activity_id", nullable = false)
    private Long activityId;

    @Column(name = "project_id", nullable = false, precision = 4)
    private Long projectId;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long id) {
        this.activityId = id;
    }
}
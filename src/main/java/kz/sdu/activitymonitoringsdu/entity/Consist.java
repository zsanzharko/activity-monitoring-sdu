package kz.sdu.activitymonitoringsdu.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "consist")
public class Consist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id", nullable = false, precision = 8)
    private Long id;

    @Column(name = "project_id", nullable = false, precision = 4)
    private String projectId;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
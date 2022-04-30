package kz.sdu.activitymonitoringsdu.entity;

import kz.sdu.activitymonitoringsdu.enums.ProjectStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

//todo do documentation
// about spent time and expected Time

@Entity
@Table(name = "projects")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @Column(name = "project_id", nullable = false, length = 8)
    private String projectId;

    @Column(name = "version", nullable = false, length = 2)
    private String projectVersion;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "spent_time", nullable = false)
    private Integer spentTime;

//    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @ToString.Exclude
//    private List<Activity> activities;
}
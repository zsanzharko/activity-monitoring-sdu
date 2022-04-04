package kz.sdu.activitymonitoringsdu.entity;

import kz.sdu.activitymonitoringsdu.enums.ProjectStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

//todo do documentation
// about spent time and expected Time

@Entity
@Table(name = "projects")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "project_id", nullable = false)
    private String projectId;

    @Column(name = "project_version", nullable = false, length = 2)
    private String projectVersion;

    @Column(name="creator_id", nullable = false)
    private Long creatorId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "expected_time", nullable = false)
    private String expectedTime;

    @Column(name = "spent_time", nullable = false)
    private String spentTime;

}
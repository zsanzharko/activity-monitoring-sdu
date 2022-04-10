package kz.sdu.activitymonitoringsdu.entity;

import kz.sdu.activitymonitoringsdu.enums.ActivityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "activities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id", nullable = false)
    private Long id;

    @Column(name = "project_id")
    private String projectId;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", length = 2500)
    private String description;

    @Column(name = "status", length = 150)
    @Enumerated(EnumType.STRING)
    private ActivityStatus status;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "spent_time", length = 100)
    private String spentTime;

}
package kz.sdu.activitymonitoringsdu.entity;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class DevConnectionActivity {
    @Id

    @Column(name = "activity_id")
    private Long activityId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "position")
    private String title;
}
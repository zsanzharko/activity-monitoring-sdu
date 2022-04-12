package kz.sdu.activitymonitoringsdu.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DevConnectionActivity {
    @Id
    @Column(name = "activity_id")
    private Long activityId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "position")
    private String title;
}
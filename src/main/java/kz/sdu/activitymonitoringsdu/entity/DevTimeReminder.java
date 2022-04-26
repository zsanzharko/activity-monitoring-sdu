package kz.sdu.activitymonitoringsdu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Sanzhar
 * @version 0.0.1
 * @apiNote entity when we can to see losing spending time from the developer
 * We can see developer id, and activity id , and date , where ( when) he does not spend time
 */

@Entity
@Table(name = "dev_time_reminder")
public class DevTimeReminder {
    @Id
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "activity_id")
    private Long activityId;

    @Column(name = "date_remind", nullable = false)
    private Date dateRemind;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Date getDateRemind() {
        return dateRemind;
    }

    public void setDateRemind(Date dateRemind) {
        this.dateRemind = dateRemind;
    }
}
package kz.sdu.activitymonitoringsdu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "report")
public class Report {
    @Id
    @Column(name = "activity_id", nullable = false)
    private Long activityId;

    @Column(name = "report_date", nullable = false)
    private LocalDate dateFixing;

    @Column(name = "spent_time", nullable = false, length = 4)
    private Integer time;

    public Integer getTime() {
        return time;
    }

    // fixme
    public String getReportTime() {
        System.out.println(time / 60 + " hour(s) " + (time % 60) + " minute(s)");
        return time / 60 + " hour(s) " + (time % 60) + " minute(s)";
    }

    public String getReportDate() {
        return dateFixing.toString();
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public LocalDate getDateFixing() {
        return dateFixing;
    }

    public void setDateFixing(LocalDate dateFixing) {
        this.dateFixing = dateFixing;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
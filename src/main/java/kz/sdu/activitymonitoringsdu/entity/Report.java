package kz.sdu.activitymonitoringsdu.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "report")
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @Id
    @Column(name = "activity_id", nullable = false)
    private Long activityId;

    @Column(name = "report_date", nullable = false)
    private Date reportDate;

    @Column(name = "spent_time", nullable = false, length = 4)
    private Integer time;

    public Integer getTime() {
        return time;
    }

    // fixme
    public String getReportTime() {
        return time / 60 + " hour(s) " + (time % 60) + " minute(s)";
    }

    public String getReportInDate() {
        return reportDate.toString();
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date dateFixing) {
        this.reportDate = dateFixing;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
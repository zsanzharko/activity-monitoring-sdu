package kz.sdu.activitymonitoringsdu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "report")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Report {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
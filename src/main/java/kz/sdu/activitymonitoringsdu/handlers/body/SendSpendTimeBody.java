package kz.sdu.activitymonitoringsdu.handlers.body;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendSpendTimeBody implements Serializable {

    private Long activityId;
    private String title;
    private Integer minutes;
    private Date spendDate;
}

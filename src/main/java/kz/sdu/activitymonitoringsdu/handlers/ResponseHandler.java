package kz.sdu.activitymonitoringsdu.handlers;

import kz.sdu.activitymonitoringsdu.enums.RequestProcess;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class ResponseHandler {
    private RequestProcess requestProcess;
    private Date dateResponse;
    private String type;
    private List<?> data;
}

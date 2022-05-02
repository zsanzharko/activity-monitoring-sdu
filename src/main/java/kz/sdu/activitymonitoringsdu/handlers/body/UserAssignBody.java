package kz.sdu.activitymonitoringsdu.handlers.body;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserAssignBody implements Serializable {
    private final Long userId;
    private final String fullName;
    private final Integer activityCount;
}

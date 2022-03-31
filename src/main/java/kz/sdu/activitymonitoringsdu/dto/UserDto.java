package kz.sdu.activitymonitoringsdu.dto;

import kz.sdu.activitymonitoringsdu.enums.Gender;
import kz.sdu.activitymonitoringsdu.enums.Position;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private final Long id;
    private final String first_name;
    private final String last_name;
    private final Gender gender;
    private final String email;
    private final Position position;
}

package kz.sdu.activitymonitoringsdu.dto;

import kz.sdu.activitymonitoringsdu.enums.Gender;
import kz.sdu.activitymonitoringsdu.enums.Position;
import kz.sdu.activitymonitoringsdu.enums.TypeUser;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private Long id;
    private String first_name;
    private String last_name;
    private Gender gender;
    private String email;
    private TypeUser typeUser;
}

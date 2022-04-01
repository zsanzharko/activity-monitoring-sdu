package kz.sdu.activitymonitoringsdu.dto;

import kz.sdu.activitymonitoringsdu.enums.Gender;
import kz.sdu.activitymonitoringsdu.enums.Position;
import kz.sdu.activitymonitoringsdu.enums.TypeUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;
    private TypeUser typeUser;
}

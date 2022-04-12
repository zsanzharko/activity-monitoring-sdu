package kz.sdu.activitymonitoringsdu.dto;

import kz.sdu.activitymonitoringsdu.enums.Gender;
import kz.sdu.activitymonitoringsdu.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private Long id;
    private String fullName;
    private Gender gender;
    private String email;
    private Role role;

    public boolean getReportPermission() {
        return role == Role.EMPLOYEE;
    }
}

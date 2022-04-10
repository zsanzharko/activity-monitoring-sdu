package kz.sdu.activitymonitoringsdu.handlers.forms;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginForm implements Serializable {
    private String email;
    private String password;
}

package kz.sdu.activitymonitoringsdu.handlers;

import kz.sdu.activitymonitoringsdu.dto.UserDto;
import kz.sdu.activitymonitoringsdu.entity.User;

public class UserConverter {
    public UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setFullName(user.getFirstName() + " " + user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setGender(user.getGender());
        userDto.setRole(user.getRole());
        return userDto;
    }
}

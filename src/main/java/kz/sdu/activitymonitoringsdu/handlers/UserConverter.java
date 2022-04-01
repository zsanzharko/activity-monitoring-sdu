package kz.sdu.activitymonitoringsdu.handlers;

import kz.sdu.activitymonitoringsdu.dto.UserDto;
import kz.sdu.activitymonitoringsdu.entity.User;

public class UserConverter {
    public UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setGender(user.getGender());
        userDto.setTypeUser(user.getTypeUser());
        return userDto;
    }
}

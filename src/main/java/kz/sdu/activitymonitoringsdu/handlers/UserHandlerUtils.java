package kz.sdu.activitymonitoringsdu.handlers;

import kz.sdu.activitymonitoringsdu.dao.UserDao;
import kz.sdu.activitymonitoringsdu.dto.UserDto;
import kz.sdu.activitymonitoringsdu.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class UserHandlerUtils {

    public static List<UserDto> convertToDto(List<User> users) {
        List<UserDto> userDtoList  = new ArrayList<>();

        for (User user : users) {
            userDtoList.add(convertToDto(user));
        }
        return userDtoList;
    }

    public static UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setFullName(user.getFirstName() + " " + user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setGender(user.getGender());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setRole(user.getRole());
        return userDto;
    }

    public static UserDto getUserFromAuth(UserDao userDao) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userEmail = ((UserDetails) principal).getUsername();
        return userDao.findUserByEmailDto(userEmail);
    }
}

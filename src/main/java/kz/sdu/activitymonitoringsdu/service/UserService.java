package kz.sdu.activitymonitoringsdu.service;

import kz.sdu.activitymonitoringsdu.dto.UserDto;
import kz.sdu.activitymonitoringsdu.entity.User;
import kz.sdu.activitymonitoringsdu.enums.Gender;
import kz.sdu.activitymonitoringsdu.enums.Role;

import java.util.List;

public interface UserService {

    List<User> findAllByRole(Role role);

    List<UserDto> findAllByEmailOrFullName(String email, String firstName, String lastName);

    List<User> findAllByGender(Gender gender);

    User findById(Long id);

    User findUserByEmail(String email);

    User findUserByEmailAndPassword(String email, String password);

    void deleteUserByEmail(String email);

    User save(User user);

    void deleteById(Long id);
}

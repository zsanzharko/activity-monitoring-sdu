package kz.sdu.activitymonitoringsdu.service;

import kz.sdu.activitymonitoringsdu.entity.User;
import kz.sdu.activitymonitoringsdu.enums.Gender;
import kz.sdu.activitymonitoringsdu.enums.Position;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> findAllByPosition(Position position);

    List<User> findAllByGender(Gender gender);

    User findById(Long id);

    User findUserByEmail(String email);

    User findUserByEmailAndPassword(String email, String password);

    void deleteUserByEmail(String email);

    User save(User user);

    void deleteById(Long id);
}

package kz.sdu.activitymonitoringsdu.repository;

import kz.sdu.activitymonitoringsdu.entity.User;
import kz.sdu.activitymonitoringsdu.enums.Gender;
import kz.sdu.activitymonitoringsdu.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByRole(Role role);

    List<User> findAllByEmailOrFirstNameOrLastName(String email, String firstName, String lastName);

    List<User> findAllByGender(Gender gender);

    User findUserByEmail(String email);

    User findUserByEmailAndPassword(String email, String password);

    void deleteUserByEmail(String email);


}
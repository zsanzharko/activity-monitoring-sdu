package kz.sdu.activitymonitoringsdu.repository;

import kz.sdu.activitymonitoringsdu.entity.User;
import kz.sdu.activitymonitoringsdu.enums.Gender;
import kz.sdu.activitymonitoringsdu.enums.Position;
import kz.sdu.activitymonitoringsdu.enums.TypeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByTypeUser(TypeUser typeUser);

    List<User> findAllByGender(Gender gender);

    User findUserByEmail(String email);

    User findUserByEmailAndPassword(String email, String password);

    void deleteUserByEmail(String email);


}
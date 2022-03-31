package kz.sdu.activitymonitoringsdu.dao;

import kz.sdu.activitymonitoringsdu.entity.User;
import kz.sdu.activitymonitoringsdu.enums.Gender;
import kz.sdu.activitymonitoringsdu.enums.Position;
import kz.sdu.activitymonitoringsdu.repository.UserRepository;
import kz.sdu.activitymonitoringsdu.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class UserDao implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllByPosition(Position position) {
        return userRepository.findAllByPosition(position);
    }

    @Override
    public List<User> findAllByGender(Gender gender) {
        return userRepository.findAllByGender(gender);
    }

    @Override
    public User findById(Long id) {
        //todo check req for doesn't find user in database
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email, password);
    }

    @Override
    public void deleteUserByEmail(String email) {
        userRepository.deleteUserByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if(user == null)
            throw new UsernameNotFoundException(email);

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                true, true,true,true,
                new ArrayList<>());
    }
}

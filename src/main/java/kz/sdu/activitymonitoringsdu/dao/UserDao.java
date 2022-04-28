package kz.sdu.activitymonitoringsdu.dao;

import kz.sdu.activitymonitoringsdu.dto.UserDto;
import kz.sdu.activitymonitoringsdu.entity.User;
import kz.sdu.activitymonitoringsdu.enums.Gender;
import kz.sdu.activitymonitoringsdu.enums.Role;
import kz.sdu.activitymonitoringsdu.handlers.UserHandlerUtils;
import kz.sdu.activitymonitoringsdu.handlers.UserPrincipal;
import kz.sdu.activitymonitoringsdu.repository.UserRepository;
import kz.sdu.activitymonitoringsdu.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
public class UserDao implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> findAllByRole(Role role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    public List<UserDto> findAllByEmailOrFullName(String email, String firstName, String lastName) {
        return userRepository.findAllByEmailOrFirstNameOrLastName(email, firstName, lastName)
                .stream()
                .filter(user -> user.getRole() == Role.EMPLOYEE)
                .map(user -> new UserDto(user.getId(), (user.getFirstName() + " " + user.getLastName()), user.getGender(), user.getEmail(), user.getRole()))
                .collect(Collectors.toList());
    }

//    public List<User> findAllBy

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


    public UserDto findUserByEmailDto(String email) {
        return UserHandlerUtils.convertToDto(userRepository.findUserByEmail(email));
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
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new UserPrincipal(user);
    }
}

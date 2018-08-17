package com.ap.game.chess.user;

import com.ap.game.chess.user.exception.UserAlreadyExists;
import com.ap.game.chess.user.exception.UserNotFoundException;
import com.ap.game.chess.role.Role;
import com.ap.game.chess.role.RoleRequestBody;
import com.ap.game.chess.role.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User create(UserRequestBody userRequestBody) {
        Optional<User> exisitingUser = userRepository.findByEmail(userRequestBody.getEmail());
        if(exisitingUser.isPresent()) {
            throw new UserAlreadyExists("User with email '" + userRequestBody.getEmail() +
                                    "' already exists");
        }
        User user = User.builder()
                .email(userRequestBody.getEmail())
                .password(passwordEncoder.encode(userRequestBody.getPassword()))
                //.password(userRequestBody.getPassword())
                .firstName(userRequestBody.getFirstName())
                .lastName(userRequestBody.getLastName())
                .build();
        assignRole(user, "ROLE_USER");
        return userRepository.save(user);
    }

    private void assignRole(User user, String roleTitle) {
        Role role = roleServiceImpl.findRoleByTitle(roleTitle);
        user.assignRole(role);
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent()) {
            throw new UserNotFoundException("User with email '" + email + "' does not exist");
        }
        return user.get();
    }

    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) {
            throw new UserNotFoundException("User with userId " + id + " does not exist");
        }
        return user.get();
    }

    @Override
    public void assignRole(Long userId, RoleRequestBody requestBody) {
        User user = findById(userId);
        Role role = roleServiceImpl.findRoleByTitle(requestBody.getRoleTitle());
        user.assignRole(role);
        userRepository.save(user);
    }

}

package com.ap.game.chess.user;

import com.ap.game.chess.role.RoleRequestBody;

import java.util.List;

public interface UserService {
    User create(UserRequestBody user);
    List<User> findAll();
    void deleteUser(long id);
    User findUserByEmail(String email);
    User findById(Long id);
    void assignRole(Long userId, RoleRequestBody requestBody);
}
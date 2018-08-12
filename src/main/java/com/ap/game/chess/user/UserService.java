package com.ap.game.chess.user;

import java.util.List;

public interface UserService {
    User create(UserRequestBody user) throws Exception;
    List<User> findAll();
    void deleteUser(long id);
    User findUserByEmail(String email);
    User findById(Long id);
}
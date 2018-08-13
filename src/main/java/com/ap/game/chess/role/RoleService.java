package com.ap.game.chess.role;

import java.util.List;

public interface RoleService {
    Role create(RoleRequestBody roleBody);
    List<Role> findAll();
    void deleteRole(Integer id);
    Role findRoleByTitle(String roleTitle);
    Role findById(Integer id);
}

package com.ap.game.chess.role;

import com.ap.game.chess.exception.role.RoleDoesNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role getRoleByTitle(String roleTitle) {
        Optional<Role> role = roleRepository.findRoleByTitle(roleTitle);
        if(!role.isPresent()){
            throw new RoleDoesNotExistsException("Role with title " + roleTitle +
                            " does not exist");
        }
        return role.get();
    }

}

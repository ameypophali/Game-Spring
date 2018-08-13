package com.ap.game.chess.role;

import com.ap.game.chess.role.exception.RoleAlreadyExistsException;
import com.ap.game.chess.role.exception.RoleDoesNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role create(RoleRequestBody roleBody) {
        String roleTitle = roleBody.getRoleTitle();
        Optional<Role> presentRole = roleRepository.findRoleByTitle(roleTitle);
        if(presentRole.isPresent()) {
            throw new RoleAlreadyExistsException("Role with title '" + roleTitle +
                    "' already exists");
        }
        Role role = Role.builder()
                .created(new Timestamp(Instant.now().toEpochMilli()))
                .title(roleTitle)
                .build();

        return roleRepository.save(role);
    }

    @Override
    public List<Role> findAll() {
        List<Role> allRoles = new ArrayList<>();
        roleRepository.findAll().forEach(allRoles::add);
        return allRoles;
    }

    @Override
    public void deleteRole(Integer id) {
        Role role = findById(id);
        if(role!=null) roleRepository.deleteById(id);
    }

    public Role findRoleByTitle(String roleTitle) {
        Optional<Role> role = roleRepository.findRoleByTitle(roleTitle);
        if(!role.isPresent()){
            throw new RoleDoesNotExistsException("Role with title " + roleTitle +
                            " does not exist");
        }
        return role.get();
    }

    @Override
    public Role findById(Integer id) {
        Optional<Role> role = roleRepository.findById(id);
        if(!role.isPresent()){
            throw new RoleDoesNotExistsException("Role with id '" + id +
                    "' does not exist");
        }
        return role.get();
    }
}

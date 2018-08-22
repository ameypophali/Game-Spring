package com.ap.game.chess.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping()
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Role> createRole(@RequestBody RoleRequestBody roleRequestBody){
        Role role = roleService.create(roleRequestBody);
        if(role==null)new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @GetMapping()
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<Role>> getAllRoles(){
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public Role getRoleById(@PathVariable(value = "id") Integer id){
        return roleService.findById(id);
    }
}

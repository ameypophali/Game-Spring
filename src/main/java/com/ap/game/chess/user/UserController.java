package com.ap.game.chess.user;

import com.ap.game.chess.role.RoleRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody UserRequestBody userRequestBody) throws Exception {
        User user = userService.create(userRequestBody);
        if(user==null) new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    @PreAuthorize("#email == authentication.principal.username")
    public ResponseEntity<User> getUsers(@PathVariable String email){
        return new ResponseEntity<>(userService.findUserByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("#userId == authentication.principal.id")
    public ResponseEntity<User> getUser(@PathVariable long userId){
        return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
    }

    @PostMapping("/{id}/assignrole")
    @Secured("ROLE_ADMIN")
    public void assignRoleToUser(@PathVariable(value = "id") Long userId,
                                 @RequestBody RoleRequestBody requestBody){
        userService.assignRole(userId, requestBody);
    }
}

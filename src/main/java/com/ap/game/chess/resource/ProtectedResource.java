package com.ap.game.chess.resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/protected")
public class ProtectedResource {

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getMessageUser(){
        return "Hello User";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getMessageAdmin(){
        return "Hello Admin";
    }

    @GetMapping("/trial")
    @PreAuthorize("hasRole('ROLE_TRIAL')")
    public String getMessageTrial(){
        return "Hello Trial";
    }

    @GetMapping("/notprotected")
    public String getMessageWorld(){
        return "Hello World";
    }
}

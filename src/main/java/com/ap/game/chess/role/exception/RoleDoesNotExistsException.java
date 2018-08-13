package com.ap.game.chess.role.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RoleDoesNotExistsException extends RoleException {
    public RoleDoesNotExistsException(String message){
        super(message);
    }
}
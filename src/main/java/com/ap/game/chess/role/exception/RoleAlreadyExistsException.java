package com.ap.game.chess.role.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class RoleAlreadyExistsException extends RoleException {
    public RoleAlreadyExistsException(String message) {
        super(message);
    }
}

package com.ap.game.chess.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserAlreadyHasRoleException extends UserException {
    public UserAlreadyHasRoleException(String message) {
        super(message);
    }
}
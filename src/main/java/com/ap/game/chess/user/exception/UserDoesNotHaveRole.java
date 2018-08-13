package com.ap.game.chess.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserDoesNotHaveRole extends UserException {
    public UserDoesNotHaveRole(String message){
        super(message);
    }
}

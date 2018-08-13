package com.ap.game.chess.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserAlreadyExists extends UserException {
    public UserAlreadyExists(String message){
        super(message);
    }
}

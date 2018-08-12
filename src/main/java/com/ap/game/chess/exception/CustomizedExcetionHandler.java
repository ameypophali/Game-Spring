package com.ap.game.chess.exception;

import com.ap.game.chess.exception.user.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomizedExcetionHandler {

    @ExceptionHandler(value = UserException.class)
    public final ResponseEntity<ErrorDetailsBody> handleUserException(UserException exception){
        log.error(exception.getMessage());
        throw exception;
    }
}

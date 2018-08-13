package com.ap.game.chess.exception;

import com.ap.game.chess.role.exception.RoleException;
import com.ap.game.chess.user.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomizedExcetionHandler {

    @ExceptionHandler(value = UserException.class)
    public final ResponseEntity<ErrorDetailsBody> handleUserException(UserException userException){
        log.error(userException.getMessage());
        throw userException;
    }

    @ExceptionHandler(value = RoleException.class)
    public final ResponseEntity<ErrorDetailsBody> handleRoleException(RoleException roleException){
        log.error(roleException.getMessage());
        throw roleException;
    }
}

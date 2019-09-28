package dev.codenation.logs.exception.handler.user;

import dev.codenation.logs.exception.handler.AbstractExceptionHandler;
import dev.codenation.logs.exception.message.user.UserExistsException;
import dev.codenation.logs.exception.message.user.UserNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class UserExceptionHandler extends AbstractExceptionHandler {

    private UserExistsException userExistsMessage;
    private UserNotFoundException userNotFoundMessage;

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<Object> userNotFoundException(){
        return new ResponseEntity<>(userNotFoundMessage.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserExistsException.class)
    private ResponseEntity<Object> userExistsException(){
        return new ResponseEntity<>(userExistsMessage.getMessage(), HttpStatus.CONFLICT);
    }

}

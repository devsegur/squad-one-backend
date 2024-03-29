package dev.codenation.logs.core.exception.handler.user;

import dev.codenation.logs.core.exception.handler.AbstractExceptionHandler;
import dev.codenation.logs.core.exception.message.user.UserExistsException;
import dev.codenation.logs.core.exception.message.user.UserNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class UserExceptionHandler extends AbstractExceptionHandler {

    private UserExistsException userExistsException = new UserExistsException();
    private UserNotFoundException userNotFoundException = new UserNotFoundException();

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<Object> userNotFoundException(){
        return new ResponseEntity<>(userNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserExistsException.class)
    private ResponseEntity<Object> userExistsException(){
        return new ResponseEntity<>(userExistsException.getMessage(), HttpStatus.CONFLICT);
    }

}

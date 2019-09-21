package dev.codenation.logs.exception.users;

import dev.codenation.logs.exception.AbstractExceptionMessage;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends AbstractExceptionMessage {


    public UserNotFoundException(HttpStatus httpStatus, String error) {
        super(HttpStatus.NOT_FOUND, error);
    }
}
package dev.codenation.logs.exception.users;

import dev.codenation.logs.exception.AbstractExceptionMessage;
import org.springframework.http.HttpStatus;

public class UserExistsException extends AbstractExceptionMessage {

    public UserExistsException(HttpStatus httpStatus, String error) {
        super(HttpStatus.CONFLICT, error);
    }
}

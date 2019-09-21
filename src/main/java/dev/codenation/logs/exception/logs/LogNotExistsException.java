package dev.codenation.logs.exception.logs;

import dev.codenation.logs.exception.AbstractExceptionMessage;
import org.springframework.http.HttpStatus;

public class LogNotExistsException extends AbstractExceptionMessage {

    public LogNotExistsException(HttpStatus httpStatus, String error) {
        super(HttpStatus.NOT_FOUND, error);
    }
}

package dev.codenation.logs.exception.message.model;

import dev.codenation.logs.exception.message.AbstractException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class AbstractConflictExistsException extends AbstractException {

    public AbstractConflictExistsException(String message) {
        super(message);
    }

}

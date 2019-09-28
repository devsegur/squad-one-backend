package dev.codenation.logs.exception.message.model;

import dev.codenation.logs.exception.message.AbstractException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AbstractNotFoundException extends AbstractException {

    public AbstractNotFoundException(String message) {
        super(message);
    }
}

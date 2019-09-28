package dev.codenation.logs.exception.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractException extends Exception {

    public AbstractException(String message) {
        super(message);
    }
}

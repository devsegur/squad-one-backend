package dev.codenation.logs.exception;

import dev.codenation.logs.exception.messages.ErrorMessage;
import org.springframework.http.HttpStatus;

public abstract class AbstractExceptionMessage extends Exception implements ErrorMessage {

    private String error;
    private HttpStatus httpStatus;

    public AbstractExceptionMessage(HttpStatus httpStatus, String error) {
        this.error = getError();
        this.httpStatus = httpStatus;
    }

    public String getError() {
        return error;
    }
}

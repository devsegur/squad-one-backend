package dev.codenation.logs.exception.message.user;

import dev.codenation.logs.exception.message.model.AbstractNotFoundException;

public class UserNotFoundException extends AbstractNotFoundException {

    public UserNotFoundException() {
        super("USER_DOES_NOT_EXISTS");
    }
}
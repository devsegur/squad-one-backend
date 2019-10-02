package dev.codenation.logs.exception.message.user;

import dev.codenation.logs.exception.message.model.AbstractConflictExistsException;

public class UserExistsException extends AbstractConflictExistsException {

    public UserExistsException() {
        super("USER_EXISTS");
    }
}

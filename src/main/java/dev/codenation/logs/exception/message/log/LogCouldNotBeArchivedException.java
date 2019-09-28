package dev.codenation.logs.exception.message.log;

import dev.codenation.logs.exception.message.model.AbstractCouldNotExecuteActionException;

public class LogCouldNotBeArchivedException extends AbstractCouldNotExecuteActionException {
    public LogCouldNotBeArchivedException() {
        super("LOG_COULD_NOT_BE_ARCHIVED");
    }
}

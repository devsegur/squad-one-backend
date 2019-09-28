package dev.codenation.logs.exception.handler;

import dev.codenation.logs.exception.message.AbstractException;
import dev.codenation.logs.exception.message.model.AbstractNotFoundException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@SuppressWarnings("ConstantConditions")
@Order
public abstract class AbstractExceptionHandler<T extends AbstractException> extends ResponseEntityExceptionHandler {

    private T message;

    @ExceptionHandler(AbstractNotFoundException.class)
    private ResponseEntity<Object> entityNotFoundException(){
        return new ResponseEntity<>(message.getMessage(),null);
    }

}

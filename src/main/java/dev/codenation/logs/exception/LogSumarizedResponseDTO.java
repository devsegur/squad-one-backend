package dev.codenation.logs.exception;

import dev.codenation.logs.domain.entity.Log;
import lombok.Data;

@Data
public class LogSumarizedResponseDTO {

    private int total;
    private Log result;
}

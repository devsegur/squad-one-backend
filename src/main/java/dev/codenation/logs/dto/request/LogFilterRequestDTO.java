package dev.codenation.logs.dto.request;
import dev.codenation.logs.domain.enums.Environment;
import dev.codenation.logs.domain.enums.Severity;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class LogFilterRequestDTO {

    private UUID id;

    private String hash;

    private String message;

    private String details;

    private Severity severity;

    private Environment environment;

    private String origin;

    private Boolean archived;

    private UUID archivedBy;

    private UUID reportedBy;

}

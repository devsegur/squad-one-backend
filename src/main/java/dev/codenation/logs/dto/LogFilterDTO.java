package dev.codenation.logs.dto;

import dev.codenation.logs.domain.enums.EnvironmentEnum;
import dev.codenation.logs.domain.enums.SeverityEnum;
import lombok.Data;

import java.util.UUID;

@Data
public class LogFilterDTO {

    private UUID id;

    private String hash;

    private String message;

    private String details;

    private SeverityEnum severityEnum;

    private EnvironmentEnum environmentEnum;

    private String origin;

    private Boolean archived;

    private UUID archivedBy;

    private UUID reportedBy;
}

package dev.codenation.logs.util;

import dev.codenation.logs.domain.enums.Environment;
import dev.codenation.logs.domain.enums.Severity;
import dev.codenation.logs.dto.request.LogFilterRequestDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LogFilterRequestDTOUtil {

    private UUID id_log = UUID.randomUUID();

    private UUID id_user = UUID.randomUUID();

    public LogFilterRequestDTO createLogFilterRequestDTO() {
        return LogFilterRequestDTO.builder()
                .id(id_log)
                .hash("1234")
                .details("Details")
                .archived(Boolean.TRUE)
                .archivedBy(id_user)
                .environment(Environment.DEV)
                .message("Message")
                .origin("Origin")
                .reportedBy(id_user)
                .severity(Severity.WARNING)
                .build();
    }

    public UUID getId_log() {
        return id_log;
    }

    public UUID getId_user() {
        return id_user;
    }
}

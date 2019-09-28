package dev.codenation.logs.parameter;

import dev.codenation.logs.domain.enums.Environment;
import dev.codenation.logs.domain.enums.Severity;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;
import java.util.UUID;

@Builder
@Data
public class LogFindParameter {

    private Optional<UUID> id;

    private Optional<String> hash;

    private Optional<String> message;

    private Optional<String> details;

    private Optional<Severity> severity;

    private Optional<Environment> environment;

    private Optional<String> origin;

    private Optional<Boolean> archived;

    private Optional<UUID> archivedBy;

    private Optional<UUID> reportedBy;


}

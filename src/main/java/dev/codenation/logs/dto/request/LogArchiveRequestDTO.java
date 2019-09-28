package dev.codenation.logs.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class LogArchiveRequestDTO {
    @NotNull
    private UUID id;
    @NotNull
    private UUID userId;
    @NotNull
    private Boolean archived;
}

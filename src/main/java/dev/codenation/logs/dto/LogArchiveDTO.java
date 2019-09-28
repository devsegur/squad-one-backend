package dev.codenation.logs.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class LogArchiveDTO {

    private UUID id;

    private UUID userId;

    private Boolean archived;

}

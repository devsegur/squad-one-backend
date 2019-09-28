package dev.codenation.logs.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.domain.valueObject.LogDetail;
import dev.codenation.logs.domain.valueObject.Origin;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class LogResponseDTO {
    private UUID id;
    private Integer hash;
    private LogDetail logDetail;
    private Origin origin;

    @JsonAlias("reported_by")
    private User reportedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;
}

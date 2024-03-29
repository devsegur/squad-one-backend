package dev.codenation.logs.core.dto.response;

import dev.codenation.logs.domain.valueObject.Origin;
import dev.codenation.logs.domain.valueObject.UserInformation;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class LogResponseDTO {

    public String hash;

    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    public String message;

    public String details;

    public String severity;

    public Origin origin;

    public String archived;

    public UserInformation archivedBy;

    public UserInformation reportedBy;

    public LocalDateTime createdAt;

    public String occurrences;

}

package dev.codenation.logs.dto.response;

import dev.codenation.logs.domain.vo.Origin;
import dev.codenation.logs.domain.vo.UserInformation;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AllLogSummaryResponseDTO {

    public String hash;

    public String id;

    public String message;

    public String details;

    public String severity;

    public String environment;

    public Origin origin;

    public String archived;

    public UserInformation reportedBy;

    public LocalDateTime createdAt;

    public String occurrences;

}

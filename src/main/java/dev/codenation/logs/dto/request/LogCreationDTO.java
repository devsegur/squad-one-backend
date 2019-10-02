package dev.codenation.logs.dto.request;

import dev.codenation.logs.domain.vo.UserInformation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LogCreationDTO {

    private Integer hash;

    @NotNull
    private String message;

    @NotNull
    private String details;

    @NotNull
    private String severity;

    @NotNull
    private String origin;

    @NotNull
    private String environment;

    private Boolean archived = false;

    @NotNull
    private UserInformation reportedBy;

}

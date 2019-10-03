package dev.codenation.logs.domain.vo;

import dev.codenation.logs.domain.enums.Severity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Max;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class LogDetail implements Serializable {

    @NotNull
    @Max(2000)
    private String message;

    @NotNull
    @Max(2000)
    private String details;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    Severity severity;

}

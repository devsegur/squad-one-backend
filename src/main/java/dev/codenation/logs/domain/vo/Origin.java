package dev.codenation.logs.domain.vo;

import dev.codenation.logs.domain.enums.Environment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Origin implements Serializable {

    @NotNull
    @Enumerated(value = EnumType.STRING)
    Environment environment;

    @NotNull
    @Size(max = 100)
    private String origin;

}

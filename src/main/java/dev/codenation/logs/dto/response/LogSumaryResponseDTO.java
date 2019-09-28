package dev.codenation.logs.dto.response;

import dev.codenation.logs.domain.entity.Log;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogSumaryResponseDTO {
    private String total;
    private Log log;
}

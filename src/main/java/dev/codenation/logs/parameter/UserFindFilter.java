package dev.codenation.logs.parameter;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class UserFindFilter {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}

package dev.codenation.logs.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserFindFilterDTO {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}

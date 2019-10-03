package dev.codenation.logs.dto.request;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Email;
import java.util.UUID;

@Data
@Builder
public class UserRequestDTO {

    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String password;
}

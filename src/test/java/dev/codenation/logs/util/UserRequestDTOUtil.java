package dev.codenation.logs.util;

import dev.codenation.logs.dto.request.UserRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class UserRequestDTOUtil {

    public UserRequestDTO createUserRequestDTO(){
        return UserRequestDTO.builder()
                .email("test@test.com")
                .firstName("User")
                .lastName("Test")
                .password("Password")
                .build();
    }

}

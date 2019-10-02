package dev.codenation.logs.util;

import dev.codenation.logs.dto.request.UserFilterRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class UserFilterRequestDTOUtil {

    public UserFilterRequestDTO createUserFilterRequestDTO(){
        return UserFilterRequestDTO.builder()
                .email("test@test.com")
                .firstName("User")
                .lastName("Test")
                .build();
    }

}

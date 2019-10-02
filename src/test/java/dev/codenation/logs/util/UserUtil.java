package dev.codenation.logs.util;

import dev.codenation.logs.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {

    public User createUser(){
        return User.builder()
                .email("user@test.com")
                .firstName("User")
                .lastName("Test")
                .password("Password")
                .build();
    }

}

package dev.codenation.logs.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.codenation.logs.dto.request.UserRequestDTO;
import org.springframework.stereotype.Component;

import java.io.IOException;

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

    public UserRequestDTO createUserRequestDTOJoao(){
        return UserRequestDTO.builder()
                .email("joao@joao.com")
                .firstName("joao")
                .lastName("silva")
                .password("1234")
                .build();
    }

    public UserRequestDTO createUserRequestDTOJoaoAtualizado(){
        return UserRequestDTO.builder()
                .email("joao@joao.com")
                .firstName("joao")
                .lastName("Silva Sauro")
                .password("senhaAtualizada")
                .build();
    }

    public UserRequestDTO createUserRequestDTOMaria(){
        return UserRequestDTO.builder()
                .email("maria@maria.com")
                .firstName("maria")
                .lastName("silva")
                .password("1234")
                .build();
    }


    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);

        return mapper.writeValueAsBytes(object);
    }

}

package dev.codenation.logs.util;

import dev.codenation.logs.dto.request.UserRequestDTO;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;

@Component
public class UserRequestDTOUtil {

    public UserRequestDTO createUserRequestDTOstefano() {

        return UserRequestDTO.builder()
                .firstName("Stefano")
                .lastName("Grando Lazzari")
                .email("stefano@gmail.com")
                .password("123456")
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

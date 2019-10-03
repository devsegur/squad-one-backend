package dev.codenation.logs.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.codenation.logs.dto.request.UserFilterRequestDTO;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserFilterRequestDTOUtil {

    public UserFilterRequestDTO createUserFilterRequestDTO(){
        return UserFilterRequestDTO.builder()
                .email("test@test.com")
                .firstName("User")
                .lastName("Test")
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

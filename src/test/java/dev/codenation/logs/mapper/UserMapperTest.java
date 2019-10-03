package dev.codenation.logs.mapper;

import dev.codenation.logs.auth.WebSecurityConfig;
import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.dto.request.UserFilterRequestDTO;
import dev.codenation.logs.dto.request.UserRequestDTO;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    public void WhenMapUserRequestDTO_ReturnUser() {
        UserRequestDTO request = createUserRequestDTO();

        User user = mapper.map(request);

        Boolean matches = WebSecurityConfig.cryptPasswordEncoder().matches("Password", user.getPassword());

        assertThat(user.getEmail(), Matchers.equalTo("test@test.com"));
        assertThat(user.getFirstName(), Matchers.equalTo("User"));
        assertThat(user.getLastName(), Matchers.equalTo("Test"));
        assertThat(matches, Matchers.equalTo(Boolean.TRUE));
    }

    @Test
    public void WhenMapUserFilterRequestDTO_ReturnUser() {
        UserFilterRequestDTO request = createUserFilterRequestDTO();

        User user = mapper.map(request);

        assertThat(user.getEmail(), Matchers.equalTo("test@test.com"));
        assertThat(user.getFirstName(), Matchers.equalTo("User"));
        assertThat(user.getLastName(), Matchers.equalTo("Test"));
    }

    private UserRequestDTO createUserRequestDTO() {
        return UserRequestDTO.builder()
                .email("test@test.com")
                .firstName("User")
                .lastName("Test")
                .password("Password")
                .build();
    }

    private UserFilterRequestDTO createUserFilterRequestDTO() {
        return UserFilterRequestDTO.builder()
                .email("test@test.com")
                .firstName("User")
                .lastName("Test")
                .build();
    }

}

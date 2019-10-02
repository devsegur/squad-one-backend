package dev.codenation.logs.mapper;

import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.dto.request.UserFilterRequestDTO;
import dev.codenation.logs.dto.request.UserRequestDTO;
import dev.codenation.logs.util.UserFilterRequestDTOUtil;
import dev.codenation.logs.util.UserRequestDTOUtil;
import net.bytebuddy.asm.Advice;
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

        @Autowired
        private UserFilterRequestDTOUtil userFilterRequestDTOUtil;

        @Autowired
        private UserRequestDTOUtil userRequestDTOUtil;

        @Test
        public void WhenMapUserRequestDTO_ReturnUser(){
            UserRequestDTO request = userRequestDTOUtil.createUserRequestDTO();

            User user = mapper.map(request);

            assertThat(user.getEmail(), Matchers.equalTo("test@test.com"));
            assertThat(user.getFirstName(), Matchers.equalTo("User"));
            assertThat(user.getLastName(), Matchers.equalTo("Test"));
            assertThat(user.getPassword(), Matchers.equalTo("Password"));
        }

        @Test
        public void WhenMapUserFilterRequestDTO_ReturnUser(){
            UserFilterRequestDTO request = userFilterRequestDTOUtil.createUserFilterRequestDTO();

            User user = mapper.map(request);

            assertThat(user.getEmail(), Matchers.equalTo("test@test.com"));
            assertThat(user.getFirstName(), Matchers.equalTo("User"));
            assertThat(user.getLastName(), Matchers.equalTo("Test"));
        }
}

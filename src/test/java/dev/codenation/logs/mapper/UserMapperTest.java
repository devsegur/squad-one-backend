package dev.codenation.logs.mapper;

import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.parameter.UserFindFilter;
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

//    @Test
//    public void WhenMapUserWithFilter_ReturnUserFound(){
//        UserFindFilter filter = createFilter();
//        User userFound = mapper.map(filter);
//
//        assertThat(userFound.getEmail(), Matchers.equalTo("user@test.com"));
//        assertThat(userFound.getFirstName(), Matchers.equalTo("User"));
//        assertThat(userFound.getLastName(), Matchers.equalTo("Test"));
//        assertThat(userFound.getPassword(), Matchers.equalTo("password"));
//
//    }

    private UserFindFilter createFilter(){
        return UserFindFilter.builder()
                .email("user@test.com")
                .firstName("User")
                .lastName("Test")
                .password("password")
                .build();
    }

}

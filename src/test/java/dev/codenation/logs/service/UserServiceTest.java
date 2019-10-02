package dev.codenation.logs.service;

import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.repository.UserRepository;
import dev.codenation.logs.util.UserUtil;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@AutoConfigurationPackage
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Autowired
    private UserUtil userUtil;

    @MockBean
    private UserRepository repository;

    @Test
    public void WhenFindByValidId_ReturnUser() {
        UUID id = UUID.randomUUID();
        User userExpected = userUtil.createUser();
        userExpected.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(userExpected));

        Optional<User> userFound = service.findById(id);

        assertThat(userFound.get(), Matchers.equalTo(userExpected));
    }

    @Test
    public void WhenSaveUser_ReturnSameUser() {
        UUID id = UUID.randomUUID();
        User userExpected = userUtil.createUser();
        userExpected.setId(id);
        when(repository.save(userExpected)).thenReturn(userExpected);

        User userSaved = service.save(userExpected);

        assertThat(userSaved, Matchers.equalTo(userExpected));
    }

    @Test
    public void WhenFindAllUsers_ReturnAllUsers(){
        User user1 = userUtil.createUser();
        User user2 = userUtil.createUser();
        User user3 = userUtil.createUser();
        List<User> users = Arrays.asList(user1, user2, user3);

        when(repository.findAll()).thenReturn(users);

        List<User> usersFound = repository.findAll();

        assertThat(usersFound.size(), Matchers.equalTo(3));
        assertThat(usersFound, Matchers.equalTo(users));
    }

}

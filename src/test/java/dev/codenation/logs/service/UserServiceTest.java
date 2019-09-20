package dev.codenation.logs.service;

import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.repository.UserRepository;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Equals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService service;

    @MockBean
    private UserRepository repository;

    @Test
    public void WhenFindByValidId_ReturnUser() {
        UUID id = UUID.randomUUID();
        User userExpected = createUser();
        userExpected.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(userExpected));

        Optional<User> userFound = service.findById(id);

        assertThat(userFound.get(), Matchers.equalTo(userExpected));
    }

    @Test
    public void WhenSaveUser_ReturnSameUser() {
        UUID id = UUID.randomUUID();
        User userExpected = createUser();
        userExpected.setId(id);
        when(repository.save(userExpected)).thenReturn(userExpected);

        User userSaved = service.save(userExpected);

        assertThat(userSaved, Matchers.equalTo(userExpected));
    }

    @Test
    public void WhenFindAllUsers_ReturnAllUsers(){
        User user1 = createUser();
        User user2 = createUser();
        User user3 = createUser();
        Page<User> users = new PageImpl<>(Arrays.asList(user1, user2, user3));

        when(repository.findAll(Pageable.unpaged())).thenReturn(users);

        Page<User> usersFound = repository.findAll(Pageable.unpaged());

        assertThat(usersFound.getTotalElements(), Matchers.equalTo(3L));
        assertThat(usersFound, Matchers.equalTo(users));
    }

    private User createUser(){
        return User.builder()
                .email("user@test.com")
                .firstName("User")
                .lastName("Test")
                .build();
    }
}

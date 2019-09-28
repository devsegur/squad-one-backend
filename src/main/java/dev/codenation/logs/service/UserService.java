package dev.codenation.logs.service;

import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.dto.request.UserFilterRequestDTO;
import dev.codenation.logs.mapper.UserMapper;
import dev.codenation.logs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService extends AbstractService<UserRepository, User, UUID> {

    @Autowired
    private UserMapper mapper;
    
    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
    }

    @Override
    public User save(User user) {
        if (!user.getPassword().isEmpty()) {
            user.setPassword(user.getPassword());
        }
        return (User) repository.save(user);
    }

    public Page<User> findAll(UserFilterRequestDTO filter) {
        User user = mapper.map(filter);
        return (Page<User>) repository.findAllById((Iterable) user);
    }
}

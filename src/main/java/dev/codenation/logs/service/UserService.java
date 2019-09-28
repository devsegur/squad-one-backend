package dev.codenation.logs.service;

import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.dto.UserFindFilterDTO;
import dev.codenation.logs.mapper.UserMapper;
import dev.codenation.logs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService extends AbstractService<UserRepository, User, UUID>{

    @Autowired
    UserMapper userMapper;

    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return super.findById(id);
    }

    @Override
    public Page<User> findAll(Example<User> example, Pageable pageable, Sort sort) {
        return super.findAll(example, pageable, sort);
    }

    @Override
    public User save(User object) {
        return super.save(object);
    }

    @Override
    public List<User> findAll(Example<User> example, Sort sort) {
        return super.findAll(example, sort);
    }

    public List<UserFindFilterDTO> findAllDTO() {
        return userMapper.map(repository.findAll());
    }
}

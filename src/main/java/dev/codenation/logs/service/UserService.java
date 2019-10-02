package dev.codenation.logs.service;

import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.domain.vo.UserAuth;
import dev.codenation.logs.domain.vo.UserInformation;
import dev.codenation.logs.exception.message.user.UserNotFoundException;
import dev.codenation.logs.mapper.UserMapper;
import dev.codenation.logs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService extends AbstractService<UserRepository, User, UUID>{

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserRepository userRepository;

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

    public List<UserInformation> findAllInList() {
        return mapper.map(repository.findAll());
    }

    public UserInformation getUserInformation() {
        return mapper.map(getUserFromUserAuth());
    }

    private User getUserFromUserAuth() {
        UserAuth principal = (UserAuth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(principal.getUsername());
    }

    public UserInformation getUserInformation(UUID id) throws UserNotFoundException {
        return mapper.mapInf(findById(id).orElseThrow(UserNotFoundException::new));
    }
}

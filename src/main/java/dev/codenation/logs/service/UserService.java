package dev.codenation.logs.service;

import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.domain.vo.UserAuth;
import dev.codenation.logs.domain.vo.UserInformation;
import dev.codenation.logs.dto.request.UserRequestDTO;
import dev.codenation.logs.exception.message.user.UserNotFoundException;
import dev.codenation.logs.mapper.UserMapper;
import dev.codenation.logs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService extends AbstractService<UserRepository, User, UUID> {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
    }

    public User save(UserRequestDTO dto) {
        return (User) repository.save(mapper.map(dto));
    }

    public List<UserInformation> findAllInList() {
        return mapper.map(repository.findAll());
    }

    public UserInformation getUserInformation() {
        return mapper.mapInf(getUserFromUserAuth());
    }

    private User getUserFromUserAuth() {
        UserAuth principal = (UserAuth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail
                (principal.getUsername());
    }

    public UserInformation getUserInformation(UUID id) throws UserNotFoundException {
        return mapper.mapInf(findById(id).orElseThrow(UserNotFoundException::new));
    }

    public UserInformation delete(UUID userId) {
        Optional<User> user = repository.findById(userId);
        return user.map(u -> {
            u.setActive(false);
            return mapper.mapInf(u);
        }).orElse(null);
    }
}

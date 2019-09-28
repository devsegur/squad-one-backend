package dev.codenation.logs.controller;

import dev.codenation.logs.domain.entity.User;
<<<<<<< HEAD
import dev.codenation.logs.dto.UserFindFilterDTO;
import dev.codenation.logs.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
=======
import dev.codenation.logs.dto.request.UserFilterRequestDTO;
import dev.codenation.logs.dto.request.UserRequestDTO;
import dev.codenation.logs.mapper.UserMapper;
import dev.codenation.logs.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
>>>>>>> 7efef6dc928ad80860cf991796c3de8583233c9d

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody UserRequestDTO user) {
        return service.save(mapper.map(user));
    }

<<<<<<< HEAD
    @GetMapping
    public List<UserFindFilterDTO> getUsers(){
        return service.findAllDTO();
    }
=======
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getById(@PathVariable UUID id) {
        return service.findById(id).orElse(null);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<User> findAll(UserFilterRequestDTO filter) {
>>>>>>> 7efef6dc928ad80860cf991796c3de8583233c9d

        return service.findAll(filter);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void patch(@PathVariable UUID id, UserRequestDTO user) {
        service.save( mapper.map(user));
    }
}

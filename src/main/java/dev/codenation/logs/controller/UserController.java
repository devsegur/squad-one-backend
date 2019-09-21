package dev.codenation.logs.controller;

import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.exception.users.UserExistsException;
import dev.codenation.logs.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ExceptionHandler(UserExistsException.class)
    //Implementar o Request Body com UserDTO ou algo assim pra receber do front não todos os parametros
    // Pq eu vou dormir agora kkkkkkkk
    public void createUser(@Valid @RequestBody User user) {
        service.save(user);
    }

}

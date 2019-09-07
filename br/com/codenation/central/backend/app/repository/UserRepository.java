package br.com.codenation.central.backend.app.repository;

import br.com.codenation.central.backend.app.entity.User;

import java.util.Optional;

public interface UserRepository extends RepositoryInterface<User, Long> {

    Optional<User> findByEmail(String email);

}

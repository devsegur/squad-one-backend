package dev.codenation.logs.repository;

import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.domain.vo.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
//    UserAuth findByEmail(String userLogin);
    User findByEmail(String userLogin);
}

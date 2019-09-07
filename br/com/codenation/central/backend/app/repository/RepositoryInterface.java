package br.com.codenation.central.backend.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryInterface<T, ID> extends JpaRepository<T, ID> {

    <S extends T> S save(S entity);

    Optional<T> findById(ID id);

    Page<T> findAll(Pageable pageable);

    void delete(T entity);

    void deleteById(ID id);

    boolean existsById(ID id);
}

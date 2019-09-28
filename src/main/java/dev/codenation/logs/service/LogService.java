package dev.codenation.logs.service;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.repository.LogRepository;
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
public class LogService extends AbstractService<LogRepository, Log, UUID>{

    @Autowired
    public LogService(LogRepository repository) {
        super(repository);
    }

    @Override
    public List<Log> findAll(Example<Log> example, Sort sort) {
        return super.findAll(example, sort);
    }

    @Override
    public Log save(Log object) {
        return super.save(object);
    }

    @Override
    public List<?> findAllDTO() {
        return null;
    }

    @Override
    public Page<Log> findAll(Example<Log> example, Pageable pageable, Sort sort) {
        return super.findAll(example, pageable, sort);
    }

    @Override
    public Optional<Log> findById(UUID id) {
        return super.findById(id);
    }

}

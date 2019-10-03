package dev.codenation.logs.service;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.dto.request.LogArchiveRequestDTO;
import dev.codenation.logs.dto.request.LogCreationDTO;
import dev.codenation.logs.dto.request.LogFilterRequestDTO;
import dev.codenation.logs.dto.response.LogResponseDTO;
import dev.codenation.logs.dto.response.LogSumaryResponseDTO;
import dev.codenation.logs.exception.message.log.LogNotFoundException;
import dev.codenation.logs.mapper.LogMapper;
import dev.codenation.logs.repository.LogRepository;
import dev.codenation.logs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class LogService extends AbstractService<LogRepository, Log, UUID> {

    @Autowired
    private LogRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository repositoryUser;

    @Autowired
    private LogMapper mapper;

    @Autowired
    public LogService(LogRepository repository) {
        super(repository);
    }

    public Page<LogSumaryResponseDTO> findAllGroupByHash(LogFilterRequestDTO filter, Pageable pageable) {
        Log log = mapper.map(filter);
        return repository.findAllSumarized(log.getLogDetail().getMessage(),
                log.getLogDetail().getDetails(),
                String.valueOf(log.getLogDetail().getSeverity()),
                String.valueOf(log.getOrigin().getEnvironment()),
                log.getOrigin().getOrigin(),
                String.valueOf(log.getReportedBy().getId()),
                pageable);
    }

    public Optional<Log> delete(UUID logId) {
        return repository.findById(logId).map(l -> {
            repository.delete(l);
            return l;
        });
    }

    public Log save(LogCreationDTO logCreationDTO){
        logCreationDTO.setHash(logCreationDTO.getMessage().hashCode());
        logCreationDTO.setReportedBy(userService.getUserInformation());
        return repository.save(mapper.map(logCreationDTO));
    }

    public LogResponseDTO findOneById(UUID id) throws LogNotFoundException {
        return mapper.map(repository.findById(id).orElseThrow(LogNotFoundException::new));
    }

    public Optional<Log> archiveLogById(UUID logId, LogArchiveRequestDTO filter) {
        return repository.findById(logId).map(l -> setArchivedLogAndSave(filter, l));
    }

    private Log setArchivedLogAndSave(LogArchiveRequestDTO filter, Log log) {
        log.setArchived(filter.getArchived());
        log.setId(filter.getUserId());
        log.setArchivedBy(repositoryUser.getOne(filter.getUserId()));
        log.setArchivedAt(LocalDateTime.now());
        return repository.saveAndFlush(log);
    }

}

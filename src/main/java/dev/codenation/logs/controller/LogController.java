package dev.codenation.logs.controller;

import dev.codenation.logs.domain.entity.Log;
<<<<<<< HEAD
import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.dto.LogFilterDTO;
import dev.codenation.logs.exception.message.log.LogCouldNotBeArchivedException;
import dev.codenation.logs.exception.message.log.LogMismatchIdsException;
import dev.codenation.logs.exception.message.log.LogNotFoundException;
import dev.codenation.logs.mapper.LogMapper;
import dev.codenation.logs.dto.LogArchiveDTO;
=======
import dev.codenation.logs.dto.request.LogArchiveRequestDTO;
import dev.codenation.logs.dto.request.LogFilterRequestDTO;
import dev.codenation.logs.exception.message.log.LogNotFoundException;
>>>>>>> 7efef6dc928ad80860cf991796c3de8583233c9d
import dev.codenation.logs.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/logs")
public class LogController {

    private LogService logService;

    @GetMapping("/{logId}")

    public Log findById(@PathVariable UUID logId) throws LogNotFoundException {
        return logService.findById(logId).orElseThrow(LogNotFoundException::new );
    }

    @GetMapping
<<<<<<< HEAD
    public List<Log> findAll(LogFilterDTO filter, @RequestParam(required = false) Sort sort) {
        Example<Log> logExample = Example.of(mapper.map(filter));
        return logService.findAll(logExample, sort);
    }

    @PatchMapping("/archive/{logId}")
    public ResponseEntity<Log> archive(@PathVariable UUID logId, @Valid LogArchiveDTO filter) throws LogCouldNotBeArchivedException, LogMismatchIdsException {

        if (logId != filter.getId()){
            throw new LogMismatchIdsException();
        }
=======
    public Page<Log> findAll(LogFilterRequestDTO filter, Pageable page) {
        return logService.findAll(filter, page);
    }

>>>>>>> 7efef6dc928ad80860cf991796c3de8583233c9d

    @PatchMapping("/archive/{logId}")
    public Log archive(@PathVariable UUID logId, @Valid LogArchiveRequestDTO filter) throws LogNotFoundException {
        return logService.archiveLogByIdOrElseThrowError(logId,filter);
    }

    @DeleteMapping("/{logId}")
    public HttpStatus delete(@PathVariable UUID logId) throws LogNotFoundException {
        logService.deleteOrElseThrowError(logId);
        return HttpStatus.OK;
    }

}

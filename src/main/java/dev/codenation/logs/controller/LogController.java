package dev.codenation.logs.controller;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.dto.request.LogArchiveRequestDTO;
import dev.codenation.logs.dto.request.LogCreationDTO;
import dev.codenation.logs.dto.request.LogFilterRequestDTO;
import dev.codenation.logs.dto.response.AllLogSummaryResponseDTO;
import dev.codenation.logs.dto.response.LogSumaryResponseDTO;
import dev.codenation.logs.exception.message.log.LogMismatchIdsException;
import dev.codenation.logs.exception.message.log.LogNotFoundException;
import dev.codenation.logs.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/{logId}")
    public Log findById(@PathVariable UUID logId) throws LogNotFoundException {
        return logService.findById(logId).orElseThrow(LogNotFoundException::new);
    }

//    @GetMapping
//    public List<AllLogSummaryResponseDTO> findAll() {
//        return logService.findAll();
//    }


    @GetMapping("/sumarized")
    public Page<LogSumaryResponseDTO> findAllSumarized(LogFilterRequestDTO filter, Pageable page) {
        return logService.findAllGroupByHash(filter, page);
    }

    @PostMapping
    public Log create(@RequestBody LogCreationDTO log){
        return logService.save(log);
    }


    @PatchMapping("/archive/{logId}")
    public Log archive(@PathVariable UUID logId, @RequestBody LogArchiveRequestDTO filter) throws LogNotFoundException, LogMismatchIdsException {
        if (!logId.equals(filter.getId())) {
            throw new LogMismatchIdsException();
        }
        return logService.archiveLogById(logId, filter).orElseThrow(LogNotFoundException::new);
    }

    @DeleteMapping("/{logId}")
    public Log delete(@PathVariable UUID logId) throws LogNotFoundException {
        return logService.delete(logId).orElseThrow(LogNotFoundException::new);
    }
}

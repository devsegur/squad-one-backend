package dev.codenation.logs.service;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.domain.enums.Environment;
import dev.codenation.logs.domain.enums.Severity;
import dev.codenation.logs.domain.valueObject.LogDetail;
import dev.codenation.logs.domain.valueObject.Origin;
import dev.codenation.logs.repository.LogRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest

public class LogServiceTest {

    @Autowired
    private LogService service;

    @MockBean
    private LogRepository repository;

    private List<Log> logs;

    @Before
    public void init_data() {
        Log log1 = createLog(Severity.DEBUG, "localhost", Environment.PROD, "details1");
        Log log2 = createLog(Severity.ERROR, "128.000", Environment.DEV, "details1");
        Log log3 = createLog(Severity.WARNING, "localhost", Environment.PROD, "details2");
        Log log4 = createLog(Severity.FATAL, "localhost", Environment.HMG, "details3");
        Log log5 = createLog(Severity.WARNING, "128.000", Environment.DEV, "details3");
        Log log6 = createLog(Severity.INFO, "localhost", Environment.PROD, "details4");
        logs = Arrays.asList(log1, log2, log3, log4, log5, log6);
    }

    @Test
    public void WhenFindByValidId_LogShouldBeReturned() {
        UUID id = UUID.randomUUID();
        Log logExpected = createLog();
        logExpected.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(logExpected));

        Optional<Log> logFound = service.findById(id);

        assertThat(logFound.get(), Matchers.equalTo(logExpected));

    }

    @Test
    public void WhenSaveLog_ReturnSameLog() {
        UUID id = UUID.randomUUID();
        Log logExpected = createLog();
        logExpected.setId(id);
        when(repository.save(logExpected)).thenReturn(logExpected);

        Log logSaved = service.save(logExpected);

        assertThat(logSaved, Matchers.equalTo(logExpected));
    }

    @Test
    public void WhenFindAllLogs_ReturnAllLogs(){

        Page<Log> pageLogs = new PageImpl<>(logs);
        when(repository.findAll(Pageable.unpaged())).thenReturn(pageLogs);

        Page<Log> pageLogsFound = repository.findAll(Pageable.unpaged());

        assertThat(pageLogsFound.getTotalElements(), Matchers.equalTo(6L));
        assertThat(pageLogsFound, Matchers.equalTo(pageLogs));
    }

    @Test
    public void WhenFindAllLogsByEnvironment_ReturnLogsFilteredByEnvironment(){
        List<Log> logsFiltered = Arrays.asList(logs.get(1), logs.get(4));
        Page<Log> pageLogsFiltered = new PageImpl<>(logsFiltered);

        Example<Log> example = Example.of(Log.builder()
                .origin(Origin.builder()
                        .environment(Environment.DEV)
                        .build())
                .build());
        when(repository.findAll(example, Pageable.unpaged())).thenReturn(pageLogsFiltered);

        Page<Log> pageLogsFound = repository.findAll(example, Pageable.unpaged());

        assertThat(pageLogsFound, Matchers.equalTo(pageLogsFiltered));
    }

    @Test
    public void WhenFindAllLogsByLevel_ReturnLogsFilteredByLevel(){
        List<Log> logsFiltered = Arrays.asList(logs.get(2), logs.get(4));
        Page<Log> pageLogsFiltered = new PageImpl<>(logsFiltered);

        Example<Log> example = Example.of(Log.builder()
                .logDetail(LogDetail.builder()
                        .severity(Severity.WARNING)
                        .build())
                .build());
        when(repository.findAll(example, Pageable.unpaged())).thenReturn(pageLogsFiltered);

        Page<Log> pageLogsFound = repository.findAll(example, Pageable.unpaged());

        assertThat(pageLogsFound, Matchers.equalTo(pageLogsFiltered));
    }

    @Test
    public void WhenFindAllLogsByDetails_ReturnLogsFilteredByDetails(){
        List<Log> logsFiltered = Arrays.asList(logs.get(0), logs.get(1));
        Page<Log> pageLogsFiltered = new PageImpl<>(logsFiltered);
        Example<Log> example = Example.of(Log.builder()
                .logDetail(LogDetail.builder()
                        .details("details1")
                        .build())
                .build());
        when(repository.findAll(example, Pageable.unpaged())).thenReturn(pageLogsFiltered);

        Page<Log> pageLogsFound = repository.findAll(example, Pageable.unpaged());

        assertThat(pageLogsFound, Matchers.equalTo(pageLogsFiltered));
    }

    @Test
    public void WhenFindAllLogsByOrigin_ReturnLogsFilteredByOrigin(){
        List<Log> logsFiltered = Arrays.asList(logs.get(1), logs.get(4));
        Page<Log> pageLogsFiltered = new PageImpl<>(logsFiltered);
        Example<Log> example = Example.of(Log.builder()
                .origin(Origin.builder()
                        .origin("128.000")
                        .build())
                .build());
        when(repository.findAll(example, Pageable.unpaged())).thenReturn(pageLogsFiltered);

        Page<Log> pageLogsFound = repository.findAll(example, Pageable.unpaged());

        assertThat(pageLogsFound, Matchers.equalTo(pageLogsFiltered));
    }

    @Test
    public void WhenFindAllLogsSortedBySeverity_ReturnAllLogsSortedBySeverity(){
        List<Log> logsSorted = logs.stream()
                .sorted(Comparator.comparing(l -> l.getLogDetail().getSeverity()))
                .collect(Collectors.toList());
        Page<Log> pageLogsSorted = new PageImpl<>(logsSorted);

        when(repository.findAll(PageRequest.of(0, 6, Sort.by("logDetail.severity").ascending()))).thenReturn(pageLogsSorted);

        Page<Log> pageLogsFound = repository.findAll(PageRequest.of(0, 6, Sort.by("logDetail.severity").ascending()));

        assertThat(pageLogsFound, Matchers.equalTo(pageLogsSorted));
    }

    @Test
    public void WhenFindAllLogsByOriginSortedBySeverity_ReturnAllLogsFilteredByOriginAndSortedBySeverity(){
        List<Log> logsFilteredAndSorted = Arrays.asList(logs.get(1), logs.get(4))
                .stream()
                .sorted(Comparator.comparing(l -> l.getLogDetail().getSeverity()))
                .collect(Collectors.toList());
        Page<Log> pageLogsFilteredAndSorted = new PageImpl<>(logsFilteredAndSorted);

        when(repository.findAll(PageRequest.of(0, 2, Sort.by("logDetail.severity").ascending()))).thenReturn(pageLogsFilteredAndSorted);

        Page<Log> pageLogsFound = repository.findAll(PageRequest.of(0, 2, Sort.by("logDetail.severity").ascending()));

        assertThat(pageLogsFound, Matchers.equalTo(pageLogsFilteredAndSorted));
    }

    @Test
    public void WhenFindAllLogsByDetailsSortedBySeverity_ReturnAllLogsFilteredByDetailsAndSortedBySeverity(){
        List<Log> logsFilteredAndSorted = Arrays.asList(logs.get(0), logs.get(1))
                .stream()
                .sorted(Comparator.comparing(l -> l.getLogDetail().getSeverity()))
                .collect(Collectors.toList());
        Page<Log> pageLogsFilteredAndSorted = new PageImpl<>(logsFilteredAndSorted);

        when(repository.findAll(PageRequest.of(0, 2, Sort.by("logDetail.severity").ascending()))).thenReturn(pageLogsFilteredAndSorted);

        Page<Log> pageLogsFound = repository.findAll(PageRequest.of(0, 2, Sort.by("logDetail.severity").ascending()));

        assertThat(pageLogsFound, Matchers.equalTo(pageLogsFilteredAndSorted));
    }

    private Log createLog() {
        return Log.builder()
                .archived(false)
                .hash("Hash")
                .logDetail(LogDetail.builder()
                        .message("Message")
                        .details("Details")
                        .severity(Severity.DEBUG)
                        .build())
                .origin(Origin.builder()
                        .origin("localhost")
                        .environment(Environment.DEV)
                        .build())
                .reportedBy(new User())
                .build();
    }

    private Log createLog(Severity severity, String origin, Environment environment, String logDetail) {
        return Log.builder()
                .archived(false)
                .hash("Hash")
                .logDetail(LogDetail.builder()
                        .message("Message")
                        .details(logDetail)
                        .severity(severity)
                        .build())
                .origin(Origin.builder()
                        .origin(origin)
                        .environment(environment)
                        .build())
                .reportedBy(new User())
                .build();
    }

}

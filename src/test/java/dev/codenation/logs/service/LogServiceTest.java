package dev.codenation.logs.service;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.domain.enums.Environment;
import dev.codenation.logs.domain.enums.Severity;
import dev.codenation.logs.domain.valueObject.LogDetail;
import dev.codenation.logs.domain.valueObject.Origin;
import dev.codenation.logs.repository.LogRepository;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest

public class LogServiceTest {

    @Autowired
    private LogService service;

    @MockBean
    private LogRepository repository;

    @Test
    public void WhenFindByValidId_LogShouldBeReturned() {
        UUID id = UUID.randomUUID();
        Log logExpected = createLog();
        logExpected.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(logExpected));

        Optional<Log> logFound = service.findById(id);

        assertThat(logFound.get(), Matchers.equalTo(logExpected));

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

}

package dev.codenation.logs.mapper;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.domain.enums.Environment;
import dev.codenation.logs.domain.enums.Severity;
import dev.codenation.logs.parameter.LogFindParameter;
import dev.codenation.logs.service.UserService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogMapperTest {

    private LogMapper mapper;

    private UUID user_id = UUID.randomUUID();

    @Autowired
    private UserService userService;

//    @Test
//    public void WhenMapLogWithParameter_ReturnLogFound(){
//        LogFindParameter parameter = createParameter();
//        Log logFound = mapper.map(parameter);
//        User user = userService.findById(user_id).get();
//
//        assertThat(logFound.getLogDetail().getMessage(), Matchers.equalTo("Message"));
//        assertThat(logFound.getLogDetail().getDetails(), Matchers.equalTo("Details"));
//        assertThat(logFound.getLogDetail().getSeverity(), Matchers.equalTo(Severity.DEBUG));
//        assertThat(logFound.getArchived(), Matchers.equalTo(Boolean.TRUE));
//        assertThat(logFound.getArchivedBy(), Matchers.equalTo(user));
//        assertThat(logFound.getOrigin().getEnvironment(), Matchers.equalTo(Environment.DEV));
//        assertThat(logFound.getOrigin().getOrigin(), Matchers.equalTo("Origin"));
//        assertThat(logFound.getHash(), Matchers.equalTo("Hash"));
//        assertThat(logFound.getReportedBy(), Matchers.equalTo(user));
//    }

    private LogFindParameter createParameter(){
        return LogFindParameter.builder()
                .message(Optional.of("Message"))
                .details(Optional.of("Details"))
                .severity(Optional.of(Severity.DEBUG))
                .archived(Optional.of(Boolean.TRUE))
                .archivedBy(Optional.of(user_id))
                .environment(Optional.of(Environment.DEV))
                .hash(Optional.of("Hash"))
                .origin(Optional.of("Origin"))
                .reportedBy(Optional.of(user_id))
                .build();
    }
}

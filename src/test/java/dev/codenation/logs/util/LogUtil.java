package dev.codenation.logs.util;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.domain.enums.Environment;
import dev.codenation.logs.domain.enums.Severity;
import dev.codenation.logs.domain.valueObject.LogDetail;
import dev.codenation.logs.domain.valueObject.Origin;
import org.springframework.stereotype.Component;

@Component
public class LogUtil extends AbstractUtil{

    public Log createLog() {
        return Log.builder()
                .archived(false)
                .hash(1)
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

    public Log createLog(Severity severity, String origin, Environment environment, String logDetail) {
        return Log.builder()
                .archived(false)
                .hash(1)
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

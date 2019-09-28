package dev.codenation.logs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableJpaAuditing
@SpringBootApplication
@EnableSwagger2
public class LogRepoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogRepoApplication.class, args);
    }

}

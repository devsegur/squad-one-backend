//package dev.codenation.logs.service;
//
//import dev.codenation.logs.domain.entity.Log;
//import dev.codenation.logs.repository.LogRepository;
//import org.hamcrest.Matchers;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.Assert.assertThat;
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//
//public class LogServiceTest {
//
//    @Autowired
//    private LogService service;
//
//    @MockBean
//    private LogRepository repository;
//
//    @Test
//    public void abc() {
//        UUID uuid = UUID.randomUUID();
//        when(repository.findById(uuid)).thenReturn(Optional.of(getLog()));
//
//        Optional<Log> result = service.findById(uuid);
//
//        assertThat(result.get(), Matchers.notNullValue());
//
//    }
//
//    private Log getLog() {
//        return new Log();
//    }
//
//}

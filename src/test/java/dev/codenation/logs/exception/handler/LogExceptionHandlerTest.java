//package dev.codenation.logs.exception.handler;
//
//import dev.codenation.logs.controller.LogController;
//import dev.codenation.logs.exception.handler.log.LogExceptionHandler;
//import dev.codenation.logs.parameter.LogArchiveParameter;
//import dev.codenation.logs.repository.LogRepository;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//@AutoConfigureMockMvc
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//public class LogExceptionHandlerTest {
//
//    //todo realizar testes para todos os handlers presentes até agora e validar com a thalita a qualidade desses.
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    LogRepository repository;
//
//    @Mock
//    LogController controller;
//
//    @Mock
//    LogArchiveParameter logArchiveParameter;
//
//    @Autowired
//    LogExceptionHandler exceptionHandler;
//
////    @Test
////    public void hereWeWillGetErrorNotFound() throws Exception {
////        UUID uuid = UUID.randomUUID();
////
////        ResultActions getLog = mvc.perform(get("/logs/" + uuid));
////
////        getLog.andExpect(status().isNotFound())
////                .andExpect(ResultMatcher.matchAll(
////                        content().string("LOG_DOES_NOT_EXISTS")
////                ));
////
////    }
////
////    @Test
////    public void hereWeWillGetErrorLogNotBeArchived() throws Exception {
////        UUID uuid = UUID.randomUUID();
////
////        when(controller.archive(uuid, logArchiveParameter)).thenThrow(new LogCouldNotBeArchivedMessage());
////        ResultActions getLog = mvc.perform(patch("/logs/archive/" + uuid));
////
////        getLog.andExpect(status().is(401))
////                .andExpect(ResultMatcher.matchAll(
////                        content().string("LOG_COULD_NOT_BE_ARCHIVED")
////                ));
////
////
////    }
//
//}
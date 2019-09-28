//package dev.codenation.logs.exception.handler;
//
//import dev.codenation.logs.exception.handler.user.UserExceptionHandler;
//import dev.codenation.logs.repository.UserRepository;
//import org.junit.runner.RunWith;
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
//public class UserExceptionHandlerTest {
//
//    //todo realizar testes para todos os handlers presentes até agora e validar com a thalita a qualidade desses.
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    UserRepository repository;
//
//    @Autowired
//    UserExceptionHandler exceptionHandler;
//
//    //todo liberar assim que os controllers estiverem ok.
////    @Test
////    public void hereWeWillGetErrorNotFound() throws Exception {
////        UUID uuid = UUID.randomUUID();
////
////        ResultActions getLog = mvc.perform(get("/user/" + uuid));
////
////        getLog.andExpect(status().isNotFound())
////                .andExpect(ResultMatcher.matchAll(
////                        content().string("USER_DOES_NOT_EXISTS")
////                ));
////
//
//}
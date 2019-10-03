package dev.codenation.logs.controller;

import dev.codenation.logs.dto.request.UserRequestDTO;
import dev.codenation.logs.util.UserRequestDTOUtil;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.boot.json.JacksonJsonParser;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {


    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRequestDTOUtil userRequestDTOUtil;

    @Value("${security.oauth2.client.client-id}")
    private String client;

    @Value("${security.oauth2.client.client-secret}")
    private String secret;

    private String token = "";

    private JacksonJsonParser parser = new JacksonJsonParser();

//    @Test
//    public void verifyingIfEndpointFindByIdReturnsCorrectUser() throws Exception {
//        //Dado
//        String conteudoRetorno = mvc.perform(post("/user")
//                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                .content(UserRequestDTOUtil.convertObjectToJsonBytes(userRequestDTOUtil.createUserRequestDTOstefano())))
//                .andReturn().getResponse().getContentAsString();
//
//        String idStefano = (String) new JSONObject(conteudoRetorno).get("id");
//
//        //Quando
//        ResultActions user = mvc.perform(get("/user/" + idStefano));
//
//        //Entao
//        user.andExpect(status().isOk())
//                .andExpect(jsonPath("$.firstName", is("Stefano")))
//                .andExpect(jsonPath("$.lastName", is("Grando Lazzari")))
//                .andExpect(jsonPath("$.email", is("stefano@gmail.com")))
//                .andExpect(jsonPath("$.password", is("123456")));
//    }

    @Test
    public void verifyingSavesUserSuccessfully() throws Exception {
        getAuthHeader();
        //Dado
        UserRequestDTO userRequestDTO = userRequestDTOUtil.createUserRequestDTOstefano();

        //Quando
        ResultActions user = mvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .header("Authorization", "Bearer " + this.token)
                .content(UserRequestDTOUtil.convertObjectToJsonBytes(userRequestDTO)));
        //Entao
        user.andExpect(status().isCreated());
    }

    @Test
    public void verifyingIfSavesUserWhenNotAuthenticated() throws Exception {
        UserRequestDTO userRequestDTO = userRequestDTOUtil.createUserRequestDTOstefano();

        ResultActions user = mvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .header("Authorization", "Bearer " + this.token)
                .content(UserRequestDTOUtil.convertObjectToJsonBytes(userRequestDTO)));

        user.andExpect(status().is(401));
    }


    private void getAuthHeader() throws Exception {
        if (token.isEmpty()) {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "password");
            params.add("username", "admin@squad.one.arrebenta.com.br");
            params.add("password", "SenhaMestra");

            ResultActions login = mvc.perform(
                    post("/oauth/token")
                            .params(params)
                            .accept("application/json;charset=UTF-8")
                            .with(httpBasic(client, secret)))
                    .andExpect(status().isOk());

            String accessToken = parser.parseMap(login
                    .andReturn()
                    .getResponse()
                    .getContentAsString()).get("access_token").toString();

            this.token = accessToken;
        }
    }
}


package dev.codenation.logs.controller;

import dev.codenation.logs.dto.request.UserRequestDTO;
import dev.codenation.logs.util.UserRequestDTOUtil;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.hamcrest.core.Is.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    //ObjectMapper mapper = new ObjectMapper();

    private String token = "";

    private JacksonJsonParser parser = new JacksonJsonParser();

    @Test
    public void verifyingIfSavesUserSuccessfully() throws Exception {
        UserRequestDTO userRequestDTO = userRequestDTOUtil.createUserRequestDTO();

        ResultActions user = mvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(UserRequestDTOUtil.convertObjectToJsonBytes(userRequestDTO)));

        user.andExpect(status().isCreated());
    }

    @Test
    public void verifyIfReturnsUserInfo_ThenReturns200() throws Exception{
        getAuthHeader();
        ResultActions user = mvc.perform(get("/user/me")
                        .header("Authorization", "Bearer " + this.token));

        user.andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Admin")))
                .andExpect(jsonPath("$.lastName", is("2.0")))
                .andExpect(jsonPath("$.email", is("admin@squad.one.arrebenta.com.br")));
    }


//    @Test
//    public void verifyIfCantDeleteUser_ThenReturns400() throws Exception {
//        getAuthHeader();
//        mvc.perform(post("/user")
//                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                .content(UserRequestDTOUtil.convertObjectToJsonBytes(userRequestDTOUtil.createUserRequestDTOJoao())))
//                .andReturn().getResponse().getContentAsString();
//
//        UUID uuid = UUID.randomUUID();
//
//        ResultActions whenDeleting = mvc.perform(delete("/user/" + uuid)
//                .header("Authorization", "Bearer " + this.token));
//
//        whenDeleting.andExpect(status().is(400));
//    }

//    @Test
//    public void verifyIfUpdatesUser_ThenReturns200() throws Exception {
//        getAuthHeader();
//        String returnContent = mvc.perform(post("/user")
//                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                .content(UserRequestDTOUtil.convertObjectToJsonBytes(userRequestDTOUtil.createUserRequestDTOJoao())))
//                .andReturn().getResponse().getContentAsString();
//
//        String idJoao = (String) new JSONObject(returnContent).get("id");
//
//        UserRequestDTO userRequestDTO = userRequestDTOUtil.createUserRequestDTOJoaoAtualizado();
//        String jsonString = mapper.writeValueAsString(userRequestDTO);
//
//        ResultActions user = mvc.perform(patch("/user/" + idJoao)
//                        .header("Authorization", "Bearer " + this.token)
//                        .param("user",jsonString));
//
//        user.andExpect(status().isOk())
//                .andExpect(jsonPath("$.lastName", is("Silva Sauro")))
//                .andExpect(jsonPath("$.password", is("senhaAtualizada")));;
//    }


    @Test
    public void verifyIfFindAllWhenAuthenticated_ThenReturns200() throws Exception{

        mvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(UserRequestDTOUtil.convertObjectToJsonBytes(userRequestDTOUtil.createUserRequestDTO())));


        mvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(UserRequestDTOUtil.convertObjectToJsonBytes(userRequestDTOUtil.createUserRequestDTOJoao())));

         mvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(UserRequestDTOUtil.convertObjectToJsonBytes(userRequestDTOUtil.createUserRequestDTOMaria())));

         getAuthHeader();
        ResultActions userList = mvc.perform(get("/user")
                        .header("Authorization", "Bearer " + this.token));

        userList.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].firstName", is("Admin")))
                .andExpect(jsonPath("$.[1].firstName", is("User")))
                .andExpect(jsonPath("$.[2].firstName", is("joao")))
                .andExpect(jsonPath("$.[3].firstName", is("maria")));

    }


    @Test
    public void verifyIfDeletesUser_ThenReturns200() throws Exception {
        getAuthHeader();
        String returnContent = mvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(UserRequestDTOUtil.convertObjectToJsonBytes(userRequestDTOUtil.createUserRequestDTOJoao())))
                .andReturn().getResponse().getContentAsString();

        String idJoao = (String) new JSONObject(returnContent).get("id");

        ResultActions whenDeleting = mvc.perform(delete("/user/" + idJoao)
                .header("Authorization", "Bearer " + this.token));

        whenDeleting.andExpect(status().isOk());
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

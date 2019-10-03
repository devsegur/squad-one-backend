package dev.codenation.logs.controller;

import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@FrameworkEndpoint
public class TokenController {

    @Resource(name = "consumerTokenServices")
    ConsumerTokenServices tokenServices;


    @RequestMapping(method = RequestMethod.GET,value = "oauth/logout")
    @ResponseBody
    public void revokeToken(HttpServletRequest request){
        String auth = request.getHeader("Authorization");
        Optional.of(auth).map(auths ->{
                String token = auth.substring("Bearer".length()+1);
                tokenServices.revokeToken(token);
                return "ok";
        }).orElse(null);
    }
}

package dev.codenation.logs.service.oauth;

import com.google.common.base.Optional;
import dev.codenation.logs.dto.request.LoginSocialRequestDTO;
import dev.codenation.logs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.Arrays;
import java.util.HashMap;

@Service
public class FacebookService {

    @Value("${spring.social.facebook.appId}")
    private String appId;

    @Value("${spring.social.facebook.appSecret}")
    private String appSecret;

    @Value("${spring.social.facebook.redirectUri}")
    private String redirectUri;

    @Value("${security.oauth2.client.client-id}")
    private String clientId;


    private UserRepository repository;
    private TokenEndpoint tokenEndpoint;

    @Autowired
    public FacebookService(UserRepository usuarioRepository, TokenEndpoint tokenEndpoint) {
        this.repository = usuarioRepository;
        this.tokenEndpoint = tokenEndpoint;
    }


    public String createFacebookAuthUrl() {
        FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(appId, appSecret);

        OAuth2Parameters params = new OAuth2Parameters();
        params.setRedirectUri(redirectUri);
        params.setScope("public_profile,email");

        return connectionFactory.getOAuthOperations().buildAuthorizeUrl(params);
    }

    public OAuth2AccessToken login(LoginSocialRequestDTO dto) {
        FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(appId, appSecret);

        AccessGrant grant = connectionFactory
                .getOAuthOperations()
                .exchangeForAccess(dto.getCode(), dto.getRedirectUri(), null);

        String token = grant.getAccessToken();

        return (token.isEmpty()) ? null : getUser(token);
    }

    private OAuth2AccessToken getUser(String token) {
        Facebook facebook = new FacebookTemplate(token);

        String[] params = {"id", "name", "picture", "short_name", "email"};
        User facebookUser = facebook.fetchObject("me", User.class, params);
        if (facebookUser == null) {
            return null;
        }
        saveUser(facebookUser);
        return Optional.of(getUserAccessToken(facebookUser)).orNull();
    }

    public void saveUser(User user){
        if (repository.findByEmail(user.getEmail()) == null) {
            repository.saveAndFlush(dev.codenation.logs.domain.entity.User.builder()
                    .email(user.getEmail())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .password(user.getId())
                    .build());
        }
    }

    public OAuth2AccessToken getUserAccessToken(User user) {
        UsernamePasswordAuthenticationToken principal = new UsernamePasswordAuthenticationToken(clientId, null, null);
        SecurityContextHolder.getContext().setAuthentication(principal);

        HashMap<String, String> other = new HashMap<String, String>() {{
            put("grant_type", "password");
            put("username",  user.getEmail());
            put("password", user.getId());
        }};
        try {
            return  tokenEndpoint.getAccessToken(principal, other).getBody();
        } catch (HttpRequestMethodNotSupportedException e) {
            e.printStackTrace();
        }
        return  null;
    }
}

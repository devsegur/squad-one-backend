package dev.codenation.logs.authentication;

import dev.codenation.logs.domain.enums.FrontendAuthEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final int ACCESS_TOKEN_INITIAL_SECONDS = 3600;
    private static final int REFRESH_TOKEN_SECONDS = 3600;

    @Autowired
    private AuthenticationManager manager;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(FrontendAuthEnum.WHO_IS_MINE_BEAUTY_FRONTEND_APPLICATION.name())
                .secret(new BCryptPasswordEncoder().encode(FrontendAuthEnum.YOU_IS.name()))
                .authorizedGrantTypes("password","authorization-code","refresh-token","implicit")
                .scopes("read","write","trust")
                .accessTokenValiditySeconds(ACCESS_TOKEN_INITIAL_SECONDS)
                .refreshTokenValiditySeconds(REFRESH_TOKEN_SECONDS);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(manager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET,HttpMethod.POST);
    }
}
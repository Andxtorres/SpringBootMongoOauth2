package application.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import application.configuration.security.mongo.OAuth2AccessTokenRepository;
import application.configuration.security.mongo.OAuth2RefreshTokenRepository;
import application.configuration.security.mongo.OAuth2RepositoryTokenStore;
import application.service.UserDetailsServiceImpl;

@Configuration
@EnableAuthorizationServer
public class RestApiResourceServerConfiguration extends AuthorizationServerConfigurerAdapter  {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	@Autowired
	private OAuth2AccessTokenRepository accessTokenRepository;
	@Autowired
	private OAuth2RefreshTokenRepository refresTokenRepository;
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints)
	        throws Exception {
		endpoints
		.tokenStore( new OAuth2RepositoryTokenStore(accessTokenRepository,refresTokenRepository))
		.authenticationManager(this.authenticationManager)
		.userDetailsService(userDetailsService).exceptionTranslator(webResponseExceptionTranslator());
	}
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
	    //oauthServer.realm("sparklr2/client");
	    oauthServer.allowFormAuthenticationForClients();
	}
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	    clients
		            .inMemory()
		            .withClient("naurooJobs")
		.authorizedGrantTypes("password", "refresh_token")
		.authorities("USER")
		.scopes("read", "write")
		.resourceIds("naurooJobsRes")
		.secret("naurooJobsSecret");
	}
	
	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
	    DefaultTokenServices tokenServices = new DefaultTokenServices();
	    tokenServices.setSupportRefreshToken(true);
	    tokenServices.setTokenStore( new OAuth2RepositoryTokenStore(accessTokenRepository,refresTokenRepository));
	    return tokenServices;
	}
	@Bean
	public WebResponseExceptionTranslator webResponseExceptionTranslator() {
	     return new DefaultWebResponseExceptionTranslator() {

	         @Override
	         public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
	             ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
	             OAuth2Exception body = responseEntity.getBody();
	             body.printStackTrace();
	             HttpHeaders headers = new HttpHeaders();
	             headers.setAll(responseEntity.getHeaders().toSingleValueMap());
	             // do something with header or response
	             return new ResponseEntity<>(body, headers, responseEntity.getStatusCode());
	         }
	     };
	 }		

}

package com.system.failed.backendtienda.config.security;


import com.system.failed.backendtienda.config.security.filter.JwtAuthenticationFilter;
import com.system.failed.backendtienda.persistence.util.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity(prePostEnabled = true)
public class HttpSecurityConfig {

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	@Autowired
	private AuthorizationManager<RequestAuthorizationContext> authorizationManager;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.csrf(AbstractHttpConfigurer::disable)
				.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.authorizeHttpRequests(
						authorizationRequestMatcher -> {
							authorizationRequestMatcher.anyRequest().access(authorizationManager);
						}
				)
				.exceptionHandling(exceptionHandling -> {
					exceptionHandling.authenticationEntryPoint(authenticationEntryPoint);
					exceptionHandling.accessDeniedHandler(accessDeniedHandler);
				})
				.build();
	}

	private static void buildRequestsMatchers(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorizationRequestsConfig) {
		authorizationRequestsConfig.requestMatchers(HttpMethod.GET, "/products")
				.hasAnyRole(RoleEnum.ADMINISTRATOR.name(), RoleEnum.ASSISTANT_ADMINISTRATOR.name());

		authorizationRequestsConfig.requestMatchers(HttpMethod.GET, "/products/{productId}")
				.hasAnyRole(RoleEnum.ADMINISTRATOR.name(), RoleEnum.ASSISTANT_ADMINISTRATOR.name());

		authorizationRequestsConfig.requestMatchers(HttpMethod.POST, "/products")
				.hasRole(RoleEnum.ADMINISTRATOR.name());

		authorizationRequestsConfig.requestMatchers(HttpMethod.PUT, "/products/{productId}")
				.hasAnyRole(RoleEnum.ADMINISTRATOR.name(), RoleEnum.ASSISTANT_ADMINISTRATOR.name());

		authorizationRequestsConfig.requestMatchers(HttpMethod.PUT, "/products/{productId}/disabled")
				.hasRole(RoleEnum.ADMINISTRATOR.name());

		authorizationRequestsConfig.requestMatchers(HttpMethod.GET, "/categories")
				.hasAnyRole(RoleEnum.ADMINISTRATOR.name(), RoleEnum.ASSISTANT_ADMINISTRATOR.name());

		authorizationRequestsConfig.requestMatchers(HttpMethod.GET, "/categories/{categoryId}")
				.hasAnyRole(RoleEnum.ADMINISTRATOR.name(), RoleEnum.ASSISTANT_ADMINISTRATOR.name());

		authorizationRequestsConfig.requestMatchers(HttpMethod.POST, "/categories")
				.hasRole(RoleEnum.ADMINISTRATOR.name());

		authorizationRequestsConfig.requestMatchers(HttpMethod.PUT, "/categories/{categoryId}")
				.hasRole(RoleEnum.ADMINISTRATOR.name());

		authorizationRequestsConfig.requestMatchers(HttpMethod.PUT, "/categories/{categoryId}/disabled")
				.hasRole(RoleEnum.ADMINISTRATOR.name());

		authorizationRequestsConfig.requestMatchers(HttpMethod.GET, "/auth/profile")
				.hasAnyRole(RoleEnum.ADMINISTRATOR.name(), RoleEnum.ASSISTANT_ADMINISTRATOR.name(), RoleEnum.CUSTOMER.name());

		authorizationRequestsConfig.requestMatchers(HttpMethod.POST, "/customers").permitAll();
		authorizationRequestsConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
		authorizationRequestsConfig.requestMatchers(HttpMethod.GET, "/auth/validate-token").permitAll();


		authorizationRequestsConfig.anyRequest().authenticated();
	}


}

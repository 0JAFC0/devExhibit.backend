package io.github.jafc.jafcportfolio.infrastructure.security.config;

import io.github.jafc.jafcportfolio.infrastructure.security.services.JwtAuthEntryPoint;
import io.github.jafc.jafcportfolio.infrastructure.security.services.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static io.github.jafc.jafcportfolio.infrastructure.utils.ResourceUriMapper.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthEntryPoint unauthorizedHandler;

	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	private final AuthenticationProvider authenticationProvider;

	private static final String ALL_URI = "/**";
	private static final String[] SWAGGER_WHITE_LIST = {
			"/v3/api-docs/**",
			"/swagger-ui/**",
			"/v2/api-docs/**",
			"/swagger-resources/**",
			"/swagger-ui.html"
	};

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf()
			.disable()
			.authorizeHttpRequests()
				.requestMatchers(SWAGGER_WHITE_LIST).permitAll()
				.requestMatchers(HttpMethod.POST,USER_URI + ALL_URI).permitAll()
				.requestMatchers(HttpMethod.GET,USER_URI + ALL_URI).permitAll()
				.requestMatchers(HttpMethod.GET,SKILL_URI + ALL_URI).permitAll()
				.requestMatchers(HttpMethod.GET,REVIEW_URI + ALL_URI).permitAll()
				.requestMatchers(HttpMethod.GET,PROJECT_URI + ALL_URI).permitAll()
				.requestMatchers(HttpMethod.GET,ACADEMIC_EXPERIENCE_URI + ALL_URI).permitAll()
				.requestMatchers(HttpMethod.GET,PROFESSIONAL_EXPERIENCE_URI + ALL_URI).permitAll()
				.anyRequest().authenticated()
				.and()
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

}

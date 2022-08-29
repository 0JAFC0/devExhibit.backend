package io.github.jafc.jafcportfolio.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.github.jafc.jafcportfolio.infrastructure.security.jwt.AuthEntryPointJwt;
import io.github.jafc.jafcportfolio.infrastructure.security.jwt.AuthTokenFilter;
import io.github.jafc.jafcportfolio.infrastructure.security.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
	    return new AuthTokenFilter();
	}
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	//Configurations for authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
    }

    //Configuration for authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
    	.antMatchers("/api/user/signin","/api/user/signup","/swagger-ui.html","/v2/api-docs").permitAll()
    	.antMatchers(HttpMethod.GET, "/api/user/**","/api/professional/**","/api/academic/**","/api/project/**","/api/review/**","/api/skill/**").permitAll()
    	.anyRequest().authenticated()
    	.and()
    	.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
    	.and()
    	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	// disable csrf
    	http.csrf().disable();
    	//Configuração do Filtro
    	http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}

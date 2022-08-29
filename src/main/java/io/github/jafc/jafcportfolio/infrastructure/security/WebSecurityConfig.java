package io.github.jafc.jafcportfolio.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.github.jafc.jafcportfolio.application.services.UserService;
import io.github.jafc.jafcportfolio.infrastructure.security.jwt.TokenAuthenticationFilter;
import io.github.jafc.jafcportfolio.infrastructure.security.jwt.TokenService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenService tokenService;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	//Configurations for authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //Configuration for authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
    	.antMatchers(HttpMethod.POST, "/api/user/signin","/api/user/register").permitAll()
    	.antMatchers(HttpMethod.GET, "/api/user/**","/api/professional/**","/api/academic/**","/api/project/**","/api/review/**","/api/skill/**").permitAll()
    	.anyRequest().authenticated()
    	.and().csrf().disable()
    	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    	//Configuração do Filtro
    	.and().addFilterBefore(new TokenAuthenticationFilter(tokenService,userService), UsernamePasswordAuthenticationFilter.class);
    }

    //Configuration for static resources
    @Override
    public void configure(WebSecurity web) throws Exception {
    }
}

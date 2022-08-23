package io.github.jafc.jafcportfolio.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.github.jafc.jafcportfolio.application.services.MyUserDetailsService;
import io.github.jafc.jafcportfolio.infrastructure.security.jwt.JwtAuthenticationEntryPoint;
import io.github.jafc.jafcportfolio.infrastructure.security.jwt.JwtTokenFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private MyUserDetailsService userService;
	
	@Autowired
	private JwtTokenFilter jwtTokenFilter;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable()
    	.cors()
    	.and()
	    	.authorizeRequests()
	    	.antMatchers("/auth/signin","/api/user/register","/auth/refresh","/v2/api-docs/**","/swagger-ui.html**").permitAll()
	    	.anyRequest().authenticated()
	    	.and()
	    	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	    	//.antMatchers("/users").denyAll()
	    .and()
	    .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
    	
    	// Add a filter to validate the tokens with every request
    	http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
}

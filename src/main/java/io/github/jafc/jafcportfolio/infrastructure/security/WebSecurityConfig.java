package io.github.jafc.jafcportfolio.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.github.jafc.jafcportfolio.infrastructure.security.jwt.AuthEntryPointJwt;
import io.github.jafc.jafcportfolio.infrastructure.security.jwt.AuthTokenFilter;
import io.github.jafc.jafcportfolio.infrastructure.security.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
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
    	.expressionHandler(webSecurityExpressionHandler())
		
    	.antMatchers(SWAGGER_WHITE_LIST).permitAll()
		// configs hasRoles
    	.antMatchers(HttpMethod.PUT, USER_ENDPOINT).hasRole(ADMIN)
		.antMatchers(HttpMethod.DELETE, USER_ENDPOINT).hasRole(ADMIN)
		.antMatchers(HttpMethod.GET, ENDPOINTS_GETBLACKLIST).hasRole(ADMIN)
    	.antMatchers(HttpMethod.DELETE, ENDPOINTS_WHITELIST).hasRole("USER")
		.antMatchers(HttpMethod.PUT, ENDPOINTS_WHITELIST).hasRole("USER")
		.antMatchers(HttpMethod.POST, ENDPOINTS_WHITELIST).hasRole("USER")
		// ENDPOINTS_WHITELIST configs
    	.antMatchers( "/api/user/signin", "/api/user/signup").permitAll()
    	.antMatchers(HttpMethod.GET, ENDPOINTS_WHITELIST).permitAll()
		.antMatchers(HttpMethod.GET, USER_ENDPOINT).permitAll()
    	.anyRequest().denyAll()
    	.and()
    	.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
    	.and()
    	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	// disable csrf
    	http.csrf().disable();
    	//Configuração do Filtro
    	http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    
    @Bean
    public RoleHierarchyImpl roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
        return roleHierarchy;
    }
    
    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
        return expressionHandler;
    }

	// Contants
	private static final String ADMIN = "ADMIN";
	private static final String USER_ENDPOINT = "/api/user/**";
    
	private static final String[] ENDPOINTS_GETBLACKLIST = {
		"/api/professional/professionals",
		"/api/academic/academics",
		"/api/project/projects",
		"/api/review/reviews",
		"/api/skill/skills",
		"/api/user/users"
	};

	private static final String[] ENDPOINTS_WHITELIST = {
		"/api/professional/**",
		"/api/academic/**",
		"/api/project/**",
		"/api/review/**",
		"/api/skill/**"
	};

    // Constant method with whiteList permissions
    private static final String[] SWAGGER_WHITE_LIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui**",
            "/v2/api-docs/**",
            "/swagger-resources/**"
    };
}

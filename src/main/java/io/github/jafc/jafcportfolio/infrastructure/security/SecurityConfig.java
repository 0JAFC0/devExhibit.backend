package io.github.jafc.jafcportfolio.infrastructure.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "security.config")
@Setter
public class SecurityConfig {
	
	public static String PREFIX;
	public static String KEY;
	public static Long EXPIRATION;
}

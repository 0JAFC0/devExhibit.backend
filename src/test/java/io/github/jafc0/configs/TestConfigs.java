package io.github.jafc0.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "properties")
public class TestConfigs {
    public static final int SERVER_PORT = 8888;
	
	public static final String HEADER_PARAM_AUTHORIZATION = "Authorization";
	public static final String HEADER_PARAM_ORIGIN = "Origin";
	
	public static final String CONTENT_TYPE_JSON = "application/json";
	public static final String CONTENT_TYPE_XML = "application/xml";
	public static final String CONTENT_TYPE_YML = "application/x-yaml";

	public static final String ORIGIN_LOCAL = "http://127.0.0.1:4200/";
	public static final String ORIGIN_SEMERU = "https://semeru.com.br";
}

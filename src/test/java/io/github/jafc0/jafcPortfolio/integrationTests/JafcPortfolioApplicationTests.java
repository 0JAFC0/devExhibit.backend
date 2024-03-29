package io.github.jafc0.jafcPortfolio.integrationTests;

import java.util.Map;
import java.util.stream.Stream;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;


@ContextConfiguration(initializers = JafcPortfolioApplicationTests.Initializer.class)
public abstract class JafcPortfolioApplicationTests {

	static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		
		static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14");

		private static void startContainers() {
			Startables.deepStart(Stream.of(postgres)).join();
		}

		private static Map<String, String> createConnectionConfiguration() {
			return Map.of(
				// configurações do banco de dados
				"spring.datasource.url", postgres.getJdbcUrl(),
				"spring.datasource.username", postgres.getUsername(),
				"spring.datasource.password", postgres.getPassword(),
				// configurações do flyway
				"spring.flyway.url",postgres.getJdbcUrl(),
				"spring.flyway.user",postgres.getUsername(),
				"spring.flyway.password",postgres.getPassword()
			);
		}

		@SuppressWarnings({"unchecked", "rawtypes"})
		@Override
		public void initialize(ConfigurableApplicationContext applicationContext) {
			startContainers();
			ConfigurableEnvironment environment = applicationContext.getEnvironment();
			MapPropertySource testcontainers = new MapPropertySource(
				"testcontainers",
				(Map) createConnectionConfiguration());
			environment.getPropertySources().addFirst(testcontainers);
		}
	}
	
}

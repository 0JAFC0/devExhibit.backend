package io.github.jafc0.jafcPortfolio.integrationTests.controller;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.jafc.jafcportfolio.DevExhibitApplication;
import io.github.jafc.jafcportfolio.presentation.dto.request.AccountCredentials;
import io.github.jafc.jafcportfolio.presentation.dto.request.UserRequest;
import io.github.jafc0.configs.TestConfigs;
import io.github.jafc0.jafcPortfolio.integrationTests.JafcPortfolioApplicationTests;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {DevExhibitApplication.class})
@TestMethodOrder(OrderAnnotation.class)
class UserControllerTest extends JafcPortfolioApplicationTests {
    
	private static RequestSpecification specification;

	@Test
    @Order(1)
    void testSignup() {
        UserRequest user = new UserRequest();
		user.setId(0l);
		user.setAge(23);
        user.setEmail("teste@gmail.com");
        user.setPassword("teste123");
		user.setName("João Arthur");
		user.setAbout("Cursando Analise e Desenvolvimento de Sistemas, Aprendendo REST API's RESTFul do 0 à AWS com Spring Boot 2.x e Docker, Sou uma pessoa que ama aprender, que gosta de ouvir música, aprender sobre o Linux e sobre as novas tecnologias.");
		user.setField("Desenvolvedor Fullstack");
		user.setImageBase64("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAmQAAAJkCAMAAACIz82OAAAABGdBTUEAALGPC/xhBQAAAAFzUkdCAK7OHOkAAAMAUExURXVT3nFQ1mlLx2pLyGdJw3RS2yMmLG1Nzm9P0m5O0GVIv2lKxmhKxWpLyWtMymxMzHdU4WZIwXFQ1XBP");
		user.setLiveIn("Sumé,Paraiba,Brasil");
		user.setWork("Em Busca");

        specification = new RequestSpecBuilder()
			.addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCAL)
			.setBasePath("/api/user/signup")
			.setPort(TestConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
			.build();

		var content = given().spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
					.body(user)
					.when()
					.post()
				.then()
					.statusCode(201)
					.extract()
					.body()
						.asString();

    }

    @Test
    @Order(2)
    void testSignin() {
        AccountCredentials user = new AccountCredentials("teste@gmail.com","teste123");

        specification = new RequestSpecBuilder()
			.addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCAL)
			.setBasePath("/api/user/signin")
			.setPort(TestConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
			.build();

		given().spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
					.body(user)
					.when()
					.post()
				.then()
					.statusCode(200)
					.extract()
					.body()
						.asString();

    }
}

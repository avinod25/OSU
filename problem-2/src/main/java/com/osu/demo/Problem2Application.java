package com.osu.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
 * The Class Problem2Application.
 * 
 * @author Vinod Arroju
 */
@SpringBootApplication
public class Problem2Application {

	/**
	 * The main method.
	 *
	 * @param args the {@code String[]}
	 */
	public static void main(String[] args) {
		SpringApplication.run(Problem2Application.class, args);
	}

	/**
	 * Custom open API.
	 *
	 * @param appDesciption the {@code String}
	 * @param appVersion the {@code String}
	 * @return the {@code OpenAPI}
	 */
	@Bean
	public OpenAPI customOpenAPI(@Value("${application-description}") String appDesciption,
			@Value("${application-version}") String appVersion) {
		return new OpenAPI()
				.info(new Info().title("OSU test problem 2 API").version(appVersion).description(appDesciption)
						.termsOfService("http://swagger.io/terms/")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")))
				.components(new Components().addSecuritySchemes("bearer-token",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
	}
}

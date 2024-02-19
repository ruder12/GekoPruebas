package com.Geko.pruebas.Configuration;

import com.Geko.pruebas.Assets.Utils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
/**
 * Configuración de Swagger para documentar la API REST.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Configura el Docket para la documentación de la API.
	 * @return El Docket configurado.
	 */
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo());
	}

	/**
	 * Obtiene la información de la API para Swagger.
	 * @return La información de la API.
	 */
	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("GekoPrueba Service API")
				.description(Utils.DescriccionApi)
				.version("1.0")
				.termsOfServiceUrl("http://localhost:5000/api/")
				.contact(new Contact("ApiGeko", "http://localhost:5000/", "ruderneysp@gmail.com"))
				.license("LICENSE")
				.licenseUrl("http://localhost:5000/")
				.build();
	}
}
package com.test.elastic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.models.Contact;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Configuration {

	
	private ApiInfo buildApiInfo() {
		Contact contact=new Contact();
		contact.setName("Pratyush Singh");
		contact.setEmail("singhprat*****@gmail.com");
		contact.setUrl("www.*******.com");
		
		ApiInfo apiInfo=new ApiInfo("Practical Java API","Practical Java API description","1.0.0",
				"", "Pratyush Singh", "","");
		return apiInfo;
	}
	
	@Bean
	public Docket swaggerApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.any()).apis(RequestHandlerSelectors.basePackage("com.test"))
				.paths(PathSelectors.any())
				.build().apiInfo(buildApiInfo()).useDefaultResponseMessages(false);
	}
	
	@Bean
	public UiConfiguration tryItOutDisable() {
		final String[] methodsWithButtonTry = { "get" };

		return UiConfigurationBuilder.builder().supportedSubmitMethods(methodsWithButtonTry).build();
	}
}

package com.budget.management.configuration;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI springBlogPessoalOpenAPI() {

		return new OpenAPI()
				.info(new Info()
						.title("Budget Management Project")
						.description("API Rest de uma aplicação de controle financeiro, realizando validações de regras de negócio"
								+ " e implementando um relatório de informações e um controle de acesso")
						.version("v0.0.1")
						.contact(new Contact()
								.name("Luíza Ramos	")
								.url("https://www.linkedin.com/in/luiza-ramos-b96a4a160/")
								.email("luizavramos30@gmail.com")))
				.externalDocs(new ExternalDocumentation()
						.description("Github")
						.url("https://github.com/luizavramos"));
	}
    
    @Bean
	public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {

		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {

				ApiResponses apiResponses = operation.getResponses();

				apiResponses.addApiResponse("200", createApiResponse("Success!"));
				apiResponses.addApiResponse("201", createApiResponse("Persisted Object"));
				apiResponses.addApiResponse("204", createApiResponse("Object Deleted"));
				apiResponses.addApiResponse("400", createApiResponse("Request Error"));
				apiResponses.addApiResponse("401", createApiResponse("Unauthorized access"));
				apiResponses.addApiResponse("404", createApiResponse("Object not found"));
				apiResponses.addApiResponse("500", createApiResponse("Application Error"));

			}));
		};
	}

	private ApiResponse createApiResponse(String message) {

		return new ApiResponse().description(message);

	}
}

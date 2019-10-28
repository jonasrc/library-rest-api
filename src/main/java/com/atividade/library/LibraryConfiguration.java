package com.atividade.library;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class LibraryConfiguration {
	
	@Bean
    public Docket api() { 

		SecurityReference securityReference = SecurityReference.builder()
				.reference("basicAuth")
				.scopes(new AuthorizationScope[0])
				.build();

		ArrayList<SecurityReference> reference = new ArrayList<>(1);
		reference.add(securityReference);

		ArrayList<SecurityContext> securityContexts = new ArrayList<>(1);
		securityContexts.add(SecurityContext.builder().securityReferences(reference).build());

		ArrayList<SecurityScheme> auth = new ArrayList<>(1);
		auth.add(new BasicAuth("basicAuth"));
		
        return new Docket(DocumentationType.SWAGGER_2)  
			.securitySchemes(auth)
			.securityContexts(securityContexts)
			.select()                                  
			.apis(RequestHandlerSelectors.basePackage("com.atividade.library.controller"))              
			.paths(PathSelectors.regex("/.*"))                      
			.build()
			.apiInfo(apiEndPointsInfo());                                           
    }
	
	private ApiInfo apiEndPointsInfo() {

        return new ApiInfoBuilder().title("Gestão de livraria virtual")
                .description("Spring boot REST API para gestão de uma livraria virtual"
                		+ "\n\nAPI implementada como parte da disciplina de Arquitetura de Backend e Microsserviços do curso de pós-graduação em Arquitetura de Software Distribuído da PUC Minas.")
                .contact(new Contact("Jonas Castanheira", "https://github.com/jonasrc", "jonasrcastanheira@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0-SNAPSHOT")
                .build();
    }
}

package com.hibernate.config;

import org.springdoc.core.GroupedOpenApi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;



@EnableWebMvc
@Configuration
public class SwaggerConfig {
	
	
	public static final String AUTHORIZATION_HEADER = "Authorization";

//    private ApiKey apiKey() {
//        return new ApiKey(AUTHORIZATION_HEADER, "Authorization", "header");
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                .build();
//    }
//
//    List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope
//                = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Arrays.asList(new SecurityReference(AUTHORIZATION_HEADER, authorizationScopes));
//    }

	
	/*
    @Bean
    public Docket getDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInformation())
 //               .securityContexts(Arrays.asList(securityContext()))
 //               .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInformation() {
        return new ApiInfo("API of E-Com Project", "These apis are created by Ankit", "1.0", "Terms of Service", new Contact("Ankit", "https://www.xyz.com", "xyz.com"), "License of Apis", "License URL", Collections.emptyList());
    }
    
    */
	
	
	  @Bean
	    public GroupedOpenApi getDocket() {
	        return GroupedOpenApi.builder()
	                .group("springshop-public")
	                .pathsToMatch("/**")
	                .build();
	    }
	

	  
	    @Bean
	    public OpenAPI springShopOpenAPI() {
	        return new OpenAPI()
	                .info(new Info().title("Ankit's Website API")
	                .description("Ankit E-Commerce application")
	                .version("v0.0.1")
	                .license(new License().name("Apache Ankit 2.0").url("http://springdoc.org")))
	                .externalDocs(new ExternalDocumentation()
	                .description("Ankits Ecom Wiki Documentation")
	                .url("https://shop.wiki.github.org/docs"));
	    }
	    
	    
	    
}

package com.entelgydigital.minishift.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${spring.swagger.version}")
    private String version;
    @Value("${spring.swagger.title}")
    private String title;
    @Value("${spring.swagger.description}")
    private String description;
    @Value("${spring.swagger.termsOfServiceUrl}")
    private String termsOfServiceUrl;
    @Value("${spring.swagger.license}")
    private String license;
    @Value("${spring.swagger.licenseUrl}")
    private String licenseUrl;
    @Value("${spring.swagger.contact.name}")
    private String contactName;
    @Value("${spring.swagger.contact.email}")
    private String contactEmail;
    @Value("${spring.swagger.contact.url}")
    private String contactUrl;

    @Bean
    public Docket api() {
	return new Docket(DocumentationType.SWAGGER_2).select()
		.apis(RequestHandlerSelectors.basePackage("com.entelgydigital.minishift.controller"))
		.paths(PathSelectors.any()).build().securitySchemes(Lists.newArrayList(apiKey())).apiInfo(apiInfo());
    }

    @Bean
    SecurityConfiguration security() {
	return new SecurityConfiguration("test-app-client-id", "test-app-client-secret", "test-app-realm", "test-app",
		"", ApiKeyVehicle.HEADER, "Authorization", "," /* scope separator */);
    }

    @Bean
    SecurityScheme apiKey() {
	return new ApiKey("token", "token", "header");
    }

    private ApiInfo apiInfo() {
	return new ApiInfo(title, description, version, termsOfServiceUrl,
		new Contact(contactName, contactUrl, contactEmail), license, licenseUrl, Collections.emptyList());
    }
}
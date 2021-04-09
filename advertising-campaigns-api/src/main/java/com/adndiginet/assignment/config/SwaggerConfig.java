package com.adndiginet.assignment.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * @author    Md Mizanur Rahman<kmmizanurrahmanjp@gmail.com>
 * @version   0.0.1-SNAPSHOT
 * @since     0.0.1-SNAPSHOT
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(SwaggerConfig.class);
	
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors.basePackage("com.adndiginet.assignment.controller"))
            .paths(PathSelectors.regex("/.*"))
            .build()
            .apiInfo(apiEndPointsInfo());
    }
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Job Assignment")
            .description("Api Documentation for managing advertising campaigns.")
            .contact(new Contact("ADN Diginet Ltd.", "https://www.adndiginet.com", "info@adndigital.com.bd"))
            .license("")
            .licenseUrl("")
            .version("0.0.1-SNAPSHOT")
            .build();
    }
}

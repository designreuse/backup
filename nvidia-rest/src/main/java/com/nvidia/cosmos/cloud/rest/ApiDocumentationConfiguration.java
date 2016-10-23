package com.nvidia.cosmos.cloud.rest;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.fasterxml.classmate.TypeResolver;

//@Configuration
//@EnableSwagger2
//@ComponentScan(basePackages = { "com.nvidia.cosmos.cloud.rest.controller" })
public class ApiDocumentationConfiguration {

	@Autowired
	private TypeResolver typeResolver;

	@Bean
	public Docket nvidiaRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.pathMapping("/")
				.apiInfo(metadata())
				.directModelSubstitute(LocalDate.class, String.class)
				.genericModelSubstitutes(ResponseEntity.class)
				.alternateTypeRules(
						newRule(typeResolver.resolve(DeferredResult.class,
								typeResolver.resolve(ResponseEntity.class,
										WildcardType.class)), typeResolver
								.resolve(WildcardType.class)))
				.useDefaultResponseMessages(false)
				.globalResponseMessage(
						RequestMethod.GET,
						newArrayList(new ResponseMessageBuilder().code(500)
								.message("500 message")
								.responseModel(new ModelRef("Error")).build()))
				.enableUrlTemplating(false);
		
	}

	private ApiInfo metadata() {
		return new ApiInfoBuilder().title("NVIDIA-REST API")
				.description("NVIDIA-REST Documentation").version("1.0")
				.contact("support@tresbu.com").build();
	}

}
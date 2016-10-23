package com.tresbu.nvidia.common;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.EncoderConfig.encoderConfig;

import java.io.File;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.internal.mapper.ObjectMapperType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

/**
 * @author K Anil
 * 
 */
public class MultipartPostAdapter extends BaseRestAdapter implements IRestAdapter {
	
	private String name;

	protected MultipartPostAdapter(GetBuilder<?, ?> builder) {
		super(builder);
		this.name = builder.name;

	}

	public static GetBuilder<?, ?> builder() {
		return new DefaultGetBuilder();
	}

	public String getName() {
		return name;
	}

	@Override
	public JsonPath execute() {
		Response response = given().baseUri(getEndPoint())
				.contentType(getContentType().getContentType())
				.header(getKey(), getValue())
				.body(getObject().toString()).expect()
				.contentType(ContentType.JSON).statusCode(200).log().all()

				.when().post(getMethod());

		String json = response.asString();
		return new JsonPath(json);
	}

	@Override
	public <T> T execute(Class<T> responseClass) {
		Response response = given()
				.config(RestAssured
						.config()
						.encoderConfig(
								encoderConfig()
								.appendDefaultContentCharsetToContentTypeIfUndefined(
										true))).baseUri(getEndPoint())
				.multiPart("file",new File(getFileName()))
				.param("emails", false)
				.header(getKey(), getValue())
				.expect().contentType(ContentType.JSON).log()
				.all()
				
				.when().post(getMethod());
		
		return response.as(responseClass, ObjectMapperType.GSON);
	}

	public static abstract class GetBuilder<S extends MultipartPostAdapter, B extends GetBuilder<S, B>>
			extends BaseRestAdapter.AbstractBuilder<S, B> {
		private String name;

		@SuppressWarnings("unchecked")
		public B name(String name) {
			this.name = name;
			return (B) this;
		}

	}

	private static class DefaultGetBuilder extends
			GetBuilder<MultipartPostAdapter, DefaultGetBuilder> {
		@Override
		public MultipartPostAdapter build() {
			return new MultipartPostAdapter(this);
		}
	}
}

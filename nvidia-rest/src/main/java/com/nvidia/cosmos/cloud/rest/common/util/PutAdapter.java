package com.nvidia.cosmos.cloud.rest.common.util;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.EncoderConfig.encoderConfig;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.internal.mapper.ObjectMapperType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

/**
 * @author K Anil
 * 
 */
public class PutAdapter extends BaseRestAdapter implements IRestAdapter {
	private String name;

	protected PutAdapter(GetBuilder<?, ?> builder) {
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
	public String executes(String contentType) {
		Response response = given().baseUri(getEndPoint())
				.contentType(getContentType().getContentType())
				.header(getKey(), getValue())
				.body(getObject().toString()).expect()
				.contentType(contentType).statusCode(200).log().all()

				.when().get(getMethod());

		
		return response.asString();
	}

	@Override
	public JsonPath execute() {
		Response response = given().baseUri(getEndPoint())
				.contentType(ContentType.JSON)
				.header(getKey(), getValue())
				.body(getObject().toString()).expect()
				.contentType(ContentType.JSON).statusCode(200).log().all()

				.when().put(getMethod());

		String json = response.asString();
		return new JsonPath(json);
	}
	
	@Override
	public JsonPath execute(String contentType) {
		Response response = given().baseUri(getEndPoint())
				.contentType(getContentType().getContentType())
				.header(getKey(), getValue())
				.body(getObject().toString()).expect()
				.contentType(contentType).statusCode(200).log().all()

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
				.contentType(ContentType.JSON)
				.header(getKey(), getValue())
				.body(getObject(), ObjectMapperType.GSON)

				.expect().contentType(ContentType.JSON).log()
				.all()

				.when().put(getMethod());
		return response.as(responseClass, ObjectMapperType.GSON);
	}

	@Override
	public Response executeForAllStatuscode() {
		Response response = given().baseUri(getEndPoint())
				.contentType(ContentType.JSON)
				.header(getKey(), getValue())
				.body(getObject().toString()).expect()
				.contentType(ContentType.JSON).log().all()

				.when().put(getMethod());

		return response;
	}
	
	public static abstract class GetBuilder<S extends PutAdapter, B extends GetBuilder<S, B>>
			extends BaseRestAdapter.AbstractBuilder<S, B> {
		private String name;

		@SuppressWarnings("unchecked")
		public B name(String name) {
			this.name = name;
			return (B) this;
		}

	}

	private static class DefaultGetBuilder extends
			GetBuilder<PutAdapter, DefaultGetBuilder> {
		@Override
		public PutAdapter build() {
			return new PutAdapter(this);
		}
	}

	@Override
	public Response executeForAllStatuscode(String contentType) {
			Response response = given().baseUri(getEndPoint())
				.contentType(getContentType().getContentType())
				.header(getKey(), getValue())
				.body(getObject().toString()).expect()
				.contentType(contentType).log().all()

				.when().put(getMethod());
		return response;
	}

}

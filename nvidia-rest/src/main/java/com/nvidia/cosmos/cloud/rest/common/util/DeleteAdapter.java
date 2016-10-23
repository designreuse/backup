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
public class DeleteAdapter extends BaseRestAdapter implements IRestAdapter {
	private String name;

	protected DeleteAdapter(DeleteBuilder<?, ?> builder) {
		super(builder);
		this.name = builder.name;

	}

	public static DeleteBuilder<?, ?> builder() {
		return new DefaultDeleteBuilder();
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
		getObject().toString();
		Response response = given().baseUri(getEndPoint())
				.contentType(getContentType().getContentType())
				//.body(getObject().toString())
				.header(getKey(), getValue())
				.expect()
				.contentType(ContentType.JSON).statusCode(200).log().all()

				.when().delete(getMethod());

		String json = response.asString();
		return new JsonPath(json);
	}
	@Override
	public JsonPath execute(String contentType) {
		getObject().toString();
		Response response = given().baseUri(getEndPoint())
				.contentType(getContentType().getContentType())
				//.body(getObject().toString())
				.header(getKey(), getValue())
				.expect()
				.contentType(contentType).statusCode(200).log().all()

				.when().delete(getMethod());

		String json = response.asString();
		return new JsonPath(json);
	}

	@Override
	public <T> T execute(Class<T> responseClass) {
		getObject().toString();
		Response response = given()
				.config(RestAssured
						.config()
						.encoderConfig(
								encoderConfig()
										.appendDefaultContentCharsetToContentTypeIfUndefined(
												true))).baseUri(getEndPoint())
				.contentType(ContentType.JSON)
				.header(getKey(), getValue())
				//.body(getObject(), ObjectMapperType.GSON)

				.expect().contentType(ContentType.JSON).log()
				.all()

				.when().delete(getMethod());
		return response.as(responseClass, ObjectMapperType.GSON);
	}


	@Override
	public Response executeForAllStatuscode() throws AssertionError{
		getObject().toString();
		Response response=null;
		try{
		response = given().baseUri(getEndPoint())
				.contentType(getContentType().getContentType())
				//.body(getObject().toString())
				.header(getKey(), getValue())
				.expect()
				.contentType(ContentType.JSON).log().all()
				
				.when().delete(getMethod());

       }catch(AssertionError ae){
       	  throw ae;  
        }
		return response;
	}
	
	public static abstract class DeleteBuilder<S extends DeleteAdapter, B extends DeleteBuilder<S, B>>
			extends AbstractBuilder<S, B> {
		private String name;

		@SuppressWarnings("unchecked")
		public B name(String name) {
			this.name = name;
			return (B) this;
		}

	}

	private static class DefaultDeleteBuilder extends
			DeleteBuilder<DeleteAdapter, DefaultDeleteBuilder> {
		@Override
		public DeleteAdapter build() {
			return new DeleteAdapter(this);
		}
	}
	
	@Override
	public Response executeForAllStatuscode(String contentType) {
			Response response = given().baseUri(getEndPoint())
				.contentType(getContentType().getContentType())
				.header(getKey(), getValue())
				.body(getObject().toString()).expect()
				.contentType(contentType).log().all()

				.when().delete(getMethod());
		return response;
	}


}
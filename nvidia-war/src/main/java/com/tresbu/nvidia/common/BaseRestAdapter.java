package com.tresbu.nvidia.common;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.RestAssured;

/**
 * @Headeror K Anil
 * 
 */
public class BaseRestAdapter {
	private Object object = "";
	private String endPoint = RestAssured.baseURI+"/api";
	private String method;
	private ResponseType contentType;
	private String key = "X-Auth-Token";
	private String value = "dummytoken";
	private String fileName;

	protected BaseRestAdapter(AbstractBuilder<?, ?> builder) {
		this.object = builder.object;
		this.endPoint = builder.endPoint;
		this.method = builder.method;
		this.contentType = builder.contentType;
		this.key = builder.key;
		this.value = builder.value;
		this.fileName = builder.fileName;
	}

	public static AbstractBuilder<?, ?> builder() {
		return new DefaultAbstractBuilder();
	}

	public Object getObject() {
		prettyPrint();
		return object;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public String getMethod() {
		return method;
	}
	public String getFileName() {
		return fileName;
	}

	public ResponseType getContentType() {
		return contentType;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	private void prettyPrint() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println("Navigating to the URL : " + getEndPoint()
				+ getMethod());
		if (object != null && !object.equals("")) {
			System.out.println("Sending request with  : \t\t");
			System.out.println(gson.toJson(object) + "\n");
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("object", object)
				.append("endPoint", endPoint).append("method", method)
				.append("contentType", contentType).toString();
	}

	public static abstract class AbstractBuilder<S extends BaseRestAdapter, B extends AbstractBuilder<S, B>> {
		private Object object = "";
		private String endPoint = RestAssured.baseURI;
		private String method;
		public ResponseType contentType = ResponseType.JSON;
		private String key = "X-Auth-Token";
		private String value = "dummytoken";
		private String fileName;

		@SuppressWarnings("unchecked")
		public B setRequestObject(Object object) {
			this.object = object;
			return (B) this;
		}

		@SuppressWarnings("unchecked")
		public B setEndPoint(String endPoint) {
			this.endPoint = endPoint;
			return (B) this;
		}

		@SuppressWarnings("unchecked")
		public B setMethodName(String method) {
			this.method = method;
			return (B) this;
		}

		@SuppressWarnings("unchecked")
		public B setContentType(ResponseType contentType) {
			this.contentType = contentType;
			return (B) this;
		}

		@SuppressWarnings("unchecked")
		public B setHeader(String key, String value) {
			this.key = key;
			this.value = value;
			return (B) this;
		}
		@SuppressWarnings("unchecked")
		public B setFileName(String fileName) {
			this.fileName = fileName;
			return (B) this;
		}
		
		public abstract S build();
	}

	private static class DefaultAbstractBuilder extends
			AbstractBuilder<BaseRestAdapter, DefaultAbstractBuilder> {

		@Override
		public BaseRestAdapter build() {
			return new BaseRestAdapter(this);
		}
	}
}
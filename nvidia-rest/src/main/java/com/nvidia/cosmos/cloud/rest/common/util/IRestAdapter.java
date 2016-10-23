package com.nvidia.cosmos.cloud.rest.common.util;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

/**
 * @author K Anil
 * 
 */
public interface IRestAdapter {
	public JsonPath execute();
	public JsonPath execute(String contentType);
	public String executes(String contentType);
	public <T> T execute(Class<T> responseClass);
	public Response executeForAllStatuscode();
	public Response executeForAllStatuscode(String contentType);
}

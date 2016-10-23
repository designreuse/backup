package com.tresbu.nvidia.common;

import com.jayway.restassured.path.json.JsonPath;

/**
 * @author K Anil
 * 
 */
public interface IRestAdapter {
	public JsonPath execute();

	public <T> T execute(Class<T> responseClass);
}

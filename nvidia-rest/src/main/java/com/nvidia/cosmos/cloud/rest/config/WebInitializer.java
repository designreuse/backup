/**
 * 
 */
package com.nvidia.cosmos.cloud.rest.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import com.nvidia.cosmos.cloud.rest.Application;

/**
 * @author pbatta
 *
 */
public class WebInitializer extends SpringBootServletInitializer {   
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }    
}

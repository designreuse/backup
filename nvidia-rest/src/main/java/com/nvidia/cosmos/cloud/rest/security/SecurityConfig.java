package com.nvidia.cosmos.cloud.rest.security;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	 private final static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	 @Override
    protected void configure(HttpSecurity http) throws Exception {
		 logger.debug("Initilized security filter");
		 	
        http    
               .csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().
                authorizeRequests().
                antMatchers("/api/node/create").permitAll().
                antMatchers("/api/reset/**").permitAll().
                antMatchers("/api/register").permitAll().
                antMatchers("/api-docs/**").permitAll().
                antMatchers("/docs/**").permitAll().
                antMatchers("/**/api/quay/verify").permitAll().
                antMatchers("/docs").permitAll().
                antMatchers("/error").permitAll().
                antMatchers("/favicon.ico").permitAll().
                antMatchers("/api/reset/verify/**").permitAll().
                antMatchers("/api/customerExist").permitAll().
                antMatchers("/api/fetch").permitAll().
                antMatchers("/api/validatepermissions").permitAll().
                
                anyRequest().authenticated().
                and().
                exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint());
        
        
        http.addFilterBefore(
        	       new AuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class);
    }

	
	 
	 
	 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	 logger.debug("Initilized Autz Managers");
        auth.authenticationProvider(domainUsernamePasswordAuthenticationProvider()).
                authenticationProvider(tokenAuthenticationProvider());
        
    }

  /*  @Bean
    public TokenService tokenService() {
        return new TokenService();
    }*/

   
    @Bean
    public ServiceAuthenticator nvidiaServiceAuthenticator() {
        return new NvidiaServiceAuthenticator();
    }
    
    @Bean
    public AuthenticationProvider domainUsernamePasswordAuthenticationProvider() {
        return new DomainUsernamePasswordAuthenticationProvider( nvidiaServiceAuthenticator());
    }

  

    @Bean
    public AuthenticationProvider tokenAuthenticationProvider() {
        return new TokenAuthenticationProvider();
    }

    @Bean
    public AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
    
    
   
}



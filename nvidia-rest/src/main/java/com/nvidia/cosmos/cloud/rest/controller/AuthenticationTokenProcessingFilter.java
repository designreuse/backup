package com.nvidia.cosmos.cloud.rest.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.GenericFilterBean;

import com.nvidia.cosmos.cloud.common.ServicesConstants;
import com.nvidia.cosmos.cloud.exceptions.UserAuthNotFoundException;
import com.nvidia.cosmos.cloud.factory.ServicesFactory;
import com.nvidia.cosmos.cloud.rest.util.EmailSenderFactory;
import com.nvidia.cosmos.cloud.services.userauth.model.UserAuth;
import com.nvidia.cosmos.cloud.services.userauth.service.UserAuthService;

public class AuthenticationTokenProcessingFilter extends GenericFilterBean {
	private static Logger LOGGER = LoggerFactory.getLogger(AuthenticationTokenProcessingFilter.class);

	// @Autowired
	private UserAuthService userAuthService;
	//
	// @Autowired
	// public AuthenticationTokenProcessingFilter(UserAuthService
	// userAuthService)
	// {
	// this.userAuthService = userAuthService;
	// }

	@Autowired
	public AuthenticationTokenProcessingFilter() {
	}

	@Autowired
	private Environment environment;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.filter.GenericFilterBean#initFilterBean()
	 */
	@Override
	protected void initFilterBean() throws ServletException {
		super.initFilterBean();
		ServicesFactory factory = ServicesFactory.getInstance();
		userAuthService = factory.getUserAuthService();
		String EMAILS_YES_NO = environment.getProperty("emails.yes.no");
		String ACCESS_KEY = environment.getProperty("nvidia.access.id");
		String SECRET_KEY = environment.getProperty("nvidia.secret.key");
		String INVITE_FROM = environment.getProperty("nvidia.invite.email");
		String REPLY = environment.getProperty("nvidia.reply.email");

		try {
			EmailSenderFactory.init(EMAILS_YES_NO, ACCESS_KEY, INVITE_FROM, REPLY, SECRET_KEY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = this.getAsHttpRequest(request);
		HttpServletResponse httpResponse = this.getAsHttpResponse(response);
		HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(httpRequest);
		HeaderMapResponseWrapper responseWrapper = new HeaderMapResponseWrapper(httpResponse);
		String URI = httpRequest.getRequestURI();
		LOGGER.info("URI is " + URI);
		if (!URI.equals("/") && !URI.contains("/api/login") && !URI.contains("/api/reset")
				&& !URI.contains("/api/register") && !URI.contains("/api/quay/verify") && !URI.contains("/docs")
				&& !URI.contains("/api-docs")
				// && !URI.contains("tar.gz")
				&& !URI.contains("/error") && !URI.contains("/api/fetch") && !URI.contains("/api/node/create")
				&&  !URI.contains("/favicon.ico")
				&& !URI.contains("/api/verify/email")
				// && !URI.contains("/api/saveEula")
				// &&!URI.contains("/api/updateEula")
				// &&!URI.contains("/api/findEula")
				// && !URI.contains("/api/eulaUpdated")
				&& !URI.contains("/api/customerExist")) {
			String authToken = this.extractAuthTokenFromRequest(httpRequest);
			if (authToken == null) {
				responseWrapper.setStatus(HttpServletResponse.SC_FORBIDDEN);
				throw new SecurityException("Auth token not found in request");
			}

			UserAuth auth = null;
			try {
				auth = userAuthService.findUserAuthByAuthToken(authToken);
			} catch (UserAuthNotFoundException e) {
				LOGGER.debug("Invalid auth-token", e.getMessage());
			}

			if (auth == null) {
				responseWrapper.setStatus(HttpServletResponse.SC_FORBIDDEN);
				throw new SecurityException("Invalid auth-token");
			}
			// LOGGER.debug("Auth token is found"+ auth);
			requestWrapper.setAttribute(ServicesConstants.LOGGED_IN_AUTH_KEY, auth.getAuthToken());
			requestWrapper.setAttribute(ServicesConstants.LOGGED_IN_EMAIL, auth.getEmail());
			requestWrapper.setAttribute(ServicesConstants.LOGGED_IN_ROLE_NAME, auth.getRoleName());

		}
		// responseWrapper.setHeader("Access-Control-Allow-Origin", "*");
		// responseWrapper.setHeader("Access-Control-Allow-Headers", "Origin,
		// X-Requested-With, Content-Type, Accept, Key, x-auth-key");
		// responseWrapper.setHeader("Access-Control-Allow-Origin", "*");
		// responseWrapper.setHeader("Access-Control-Allow-Methods", "POST, GET,
		// OPTIONS, DELETE");
		// responseWrapper.setHeader("Access-Control-Max-Age", "3600");
		// responseWrapper.setHeader("Access-Control-Allow-Headers", "Origin,
		// X-Requested-With,Accept,Content-Type,X-AUTH-TOKEN");

		chain.doFilter(requestWrapper, responseWrapper);
	}

	private HttpServletRequest getAsHttpRequest(ServletRequest request) {
		if (!(request instanceof HttpServletRequest)) {
			throw new RuntimeException("Expecting an HTTP request");
		}

		return (HttpServletRequest) request;
	}

	private HttpServletResponse getAsHttpResponse(ServletResponse response) {
		if (!(response instanceof HttpServletResponse)) {
			throw new RuntimeException("Expecting an HTTP response");
		}

		return (HttpServletResponse) response;
	}

	private String extractAuthTokenFromRequest(HttpServletRequest httpRequest) {
		/* Get token from header */
		String authToken = httpRequest.getHeader("X-Auth-Token");

		/* If token not found get it from request parameter */
		if (authToken == null) {
			authToken = httpRequest.getParameter("token");
		}

		return authToken;
	}

	// http://stackoverflow.com/questions/2811769/adding-an-http-header-to-the-request-in-a-servlet-filter
	// http://sandeepmore.com/blog/2010/06/12/modifying-http-headers-using-java/
	// http://bijubnair.blogspot.de/2008/12/adding-header-information-to-existing.html
	/**
	 * allow adding additional header entries to a request
	 * 
	 * @author wf
	 * 
	 */
	public class HeaderMapRequestWrapper extends HttpServletRequestWrapper {
		/**
		 * construct a wrapper for this request
		 * 
		 * @param request
		 */
		public HeaderMapRequestWrapper(HttpServletRequest request) {
			super(request);
		}

		private Map<String, String> headerMap = new HashMap<String, String>();

		/**
		 * add a header with given name and value
		 * 
		 * @param name
		 * @param value
		 */
		public void addHeader(String name, String value) {
			headerMap.put(name, value);
		}

		@Override
		public String getHeader(String name) {
			String headerValue = super.getHeader(name);
			if (headerMap.containsKey(name)) {
				headerValue = headerMap.get(name);
			}
			return headerValue;
		}

		/**
		 * get the Header names
		 */
		@Override
		public Enumeration<String> getHeaderNames() {
			List<String> names = Collections.list(super.getHeaderNames());
			for (String name : headerMap.keySet()) {
				names.add(name);
			}
			return Collections.enumeration(names);
		}

		@Override
		public Enumeration<String> getHeaders(String name) {
			List<String> values = Collections.list(super.getHeaders(name));
			if (headerMap.containsKey(name)) {
				values.add(headerMap.get(name));
			}
			return Collections.enumeration(values);
		}

	}

	public class HeaderMapResponseWrapper extends HttpServletResponseWrapper {
		/**
		 * construct a wrapper for this request
		 * 
		 * @param request
		 */
		public HeaderMapResponseWrapper(HttpServletResponse response) {
			super(response);
		}

		private Map<String, String> headerMap = new HashMap<String, String>();

		/**
		 * add a header with given name and value
		 * 
		 * @param name
		 * @param value
		 */
		public void addHeader(String name, String value) {
			headerMap.put(name, value);
		}

		@Override
		public String getHeader(String name) {
			String headerValue = super.getHeader(name);
			if (headerMap.containsKey(name)) {
				headerValue = headerMap.get(name);
			}
			return headerValue;
		}

		// /**
		// * get the Header names
		// */
		// @Override
		// public Enumeration<String> getHeaderNames() {
		// List<String> names = Collections.list(super.getHeaderNames());
		// for (String name : headerMap.keySet()) {
		// names.add(name);
		// }
		// return Collections.enumeration(names);
		// }
		//
		// @Override
		// public Enumeration<String> getHeaders(String name) {
		// List<String> values = Collections.list(super.getHeaders(name));
		// if (headerMap.containsKey(name)) {
		// values.add(headerMap.get(name));
		// }
		// return Collections.enumeration(values);
		// }
		//
	}
}


 class DuplicateFoundException extends RuntimeException {
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}

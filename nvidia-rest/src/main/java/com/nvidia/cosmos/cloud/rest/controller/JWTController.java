package com.nvidia.cosmos.cloud.rest.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.xml.bind.DatatypeConverter;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Optional;
import com.nvidia.cosmos.cloud.dtos.JWTResponseDTO;
import com.nvidia.cosmos.cloud.exceptions.JWTUserNotFoundException;
import com.nvidia.cosmos.cloud.exceptions.UserNotFoundException;
import com.nvidia.cosmos.cloud.services.user.model.User;
import com.nvidia.cosmos.cloud.services.user.service.UserService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Controller
public class JWTController extends BaseController {

	private static final String AUTHORIZATION_HEADER = "authorization";
	private static final Logger LOGGER = LoggerFactory.getLogger(JWTController.class);

	/**
	 * 
	 */
	UserService service;

	/**
	 * 
	 */
	public JWTController() {
		service = factory.getUserService();
	}

	private static final String TYPE = "typ";
	private static final String TYPE_VALUE = "JWT";
	private static final String EMAIL = "email";
	private static final String AUTHY = "authy";
	private static final String AUDIANCE_URL = "quay.io/jwtauthn";

	/**
	 * RBAC and tenant rules This is an open API Verify whether user exists or
	 * not
	 * 
	 * @return
	 * @throws ServletException
	 * @throws JWTUserNotFoundException
	 */
	@Path("/api/quay/verify")
	@GET
	@ApiOperation(value = "Verify whether user exists or not", notes = "Verify whether user exists or not. This is an open API.", response = User.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "User has been verified") })
	@RequestMapping(value = "/api/quay/verify", method = RequestMethod.GET)
	public JWTResponseDTO getJWTResponse(HttpServletRequest request) throws ServletException, JWTUserNotFoundException {

		//JWTResponseDTO response = new JWTResponseDTO("404");
		JWTResponseDTO response = new JWTResponseDTO("Invalid username/password", HttpStatus.SC_NOT_FOUND);
		LOGGER.info("In  getJWTResponse");
		User user = null;
		UserLogin loginInfo = getBasicAuth(request);
		if (loginInfo == null)
			return response;

		LOGGER.info("User credentials Name:[" + loginInfo.name + "] password [ " + loginInfo.password + "]");
		try {
			user = service.findUserByUserName(loginInfo.name, loginInfo.password);
		} catch (UserNotFoundException e1) {
			throw new JWTUserNotFoundException("Invalid username/password");
		}

		try {

			// if(EncryptorFactory.getBCryptEncryptor().encrypt(loginInfo.password).equals(user.getPassword())){
			if (user != null) {
				Date currentDate = new Date();
				Date expDate = addTime(currentDate, 5);
				final String tocken = Jwts.builder().setSubject(user.getUserName()).setHeaderParam(TYPE, TYPE_VALUE)
						.claim(EMAIL, user.getEmail()).setIssuer(AUTHY).setAudience(AUDIANCE_URL)
						.setNotBefore(new Date()).setExpiration(expDate).setIssuedAt(new Date())
						.signWith(SignatureAlgorithm.RS256, getPrivateKey()).compact();
				LOGGER.info("Returning token to quyo[" + tocken + "]");

				response = new JWTResponseDTO(tocken, HttpStatus.SC_OK);
			} else {
				LOGGER.debug("Local DB authintication failed ");
			}
		} catch (Exception e) {
			LOGGER.error("Error in user evaluation", e.getMessage());
		}
		return response;
	}

	public static Date addTime(Date date, int mins) {
		date.setTime(date.getTime() + 1000 * 60 * mins);
		return date;

	}

	private UserLogin getBasicAuth(HttpServletRequest request) {

		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			LOGGER.debug("{}", headerName);
			LOGGER.debug("Header {} Value {}", request.getHeader(headerName), request.getHeader(headerName));
		}

		String auth = request.getHeader(AUTHORIZATION_HEADER);
		if (auth == null) {
			LOGGER.debug("Required Authorization tocken not avilable in header");
			return null;
		}
		LOGGER.info("AUTH HEADER" + auth);
		String userDetails = auth.substring(6, auth.length());
		userDetails = new String(DatatypeConverter.parseBase64Binary(userDetails));
		String userAttributes[] = userDetails.split(":");
		UserLogin loginDetails = new UserLogin(userAttributes[0], userAttributes[1]);
		LOGGER.info("JWT token for user[" + userAttributes[0] + "]");
		return loginDetails;
	}

	public static PrivateKey getPrivateKey() {

		PrivateKey privateKey = null;

		try {
			ClassPathResource cpr = new ClassPathResource("example_private.der");

			File f = new File(cpr.getFile().getAbsolutePath());
			FileInputStream fis = new FileInputStream(f);
			DataInputStream dis = new DataInputStream(fis);
			byte[] keyBytes = new byte[(int) f.length()];
			dis.readFully(keyBytes);
			dis.close();
			PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory kf = KeyFactory.getInstance("RSA");
			privateKey = kf.generatePrivate(spec);
		} catch (FileNotFoundException e) {
			LOGGER.error("Error in constructing key  FileNotFoundException", e);
			// e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("Error in constructing key  NoSuchAlgorithmException ", e);
			// e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			LOGGER.error("Error in constructing key  InvalidKeySpecException ", e);
			// e.printStackTrace();
		} catch (IOException e) {
			LOGGER.error("Error in constructing key  IOException ", e);
			// e.printStackTrace();
		}

		return privateKey;
	}

	@SuppressWarnings("unused")
	private static class UserLogin {
		public String name;
		public String password;

		public UserLogin(String name, String password) {
			this.name = name;
			this.password = password;
		}

	}

	@SuppressWarnings("unused")
	private static class LoginResponse implements Serializable {

		private static final long serialVersionUID = 1L;
		public String token;

		public LoginResponse(final String token) {
			this.token = token;
		}

	}

}

package com.tresbu.nvidia.web.controller;

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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tresbu.nvidia.common.data.LoginData;
import com.tresbu.nvidia.common.exception.NvidiaServiceException;
import com.tresbu.nvidia.common.logging.NvidiaAppLogger;
import com.tresbu.nvidia.service.intrface.NvidiaServiceIntrface;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Controller
public class JWTController {

	private static final String AUTHORIZATION_HEADER = "authorization";
	private static final Logger LOGGER = NvidiaAppLogger.getLogger(JWTController.class.getName());

	@Autowired
	private NvidiaServiceIntrface mNvidiaServiceIntrface;

	public JWTController() {
	}

	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	public @ResponseBody LoginResponse getJWTResponse(HttpServletRequest request) throws ServletException {
		LOGGER.info("In  getJWTResponse");
		LoginResponse response = new LoginResponse("404");
		LoginData loginData = null;
		UserLogin loginInfo = getBasicAuth(request);
		if (loginInfo == null)
			return response;

		try {
			LOGGER.info("query data name ["+loginInfo.name+"] password ["+loginInfo.password+"]");
			loginData = mNvidiaServiceIntrface.getUserLoginDetails(loginInfo.name);
		} catch (NvidiaServiceException e) {
			LOGGER.debug("User info retrival problem " + e.getDescription());
		}

		if (loginData == null || loginData.getEmail() == null) {
			return response;
		}
		Date currentDate = new Date();
		Date expDate = addTime(currentDate, 20);
		String tocken = Jwts.builder().setSubject(loginData.getEmail()).setHeaderParam("typ", "JWT")
				.claim("email", "sitaram.shastri@tresbu.com").setIssuer("authy").setAudience("quay.io/jwtauthn")
				.setNotBefore(new Date()).setExpiration(expDate).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.RS256, getPrivateKey()).compact();

		LOGGER.info("Returning token to quyo[" + tocken + "]");
		response = new LoginResponse(tocken);
		return response;
	}

	public static Date addTime(Date d, int days) {
		d.setTime(d.getTime() + 1000 * 60);
		return d;
	}

	private UserLogin getBasicAuth(HttpServletRequest request) {

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
		LOGGER.info("JWT token for user[" + userAttributes[0] + "]password ["+userAttributes[1]+"]");
		return loginDetails;
	}

	public static PrivateKey getPrivateKey() {

		PrivateKey privateKey = null;

		try {
			ClassPathResource cpr = new ClassPathResource("example_private.der");
			System.out.println(cpr.getFile());
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
			LOGGER.error("Error in constructing key  FileNotFoundException",e);
			//e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("Error in constructing key  NoSuchAlgorithmException ",e);
			//e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			LOGGER.error("Error in constructing key  InvalidKeySpecException ",e);
			//e.printStackTrace();
		} catch (IOException e) {
			LOGGER.error("Error in constructing key  IOException ",e);
			//e.printStackTrace();
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
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public String token;

		public LoginResponse(final String token) {
			this.token = token;
		}
	}
}

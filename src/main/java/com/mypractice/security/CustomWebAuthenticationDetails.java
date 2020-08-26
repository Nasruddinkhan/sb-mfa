/**
 * 23-Aug-2020
 * CustomWebAuthenticationDetails.java 
 * ZAID
 */
package com.mypractice.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * @author ZAID
 *
 */
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public  final String verificationCode;
	/**
	 * @param request
	 */
	public CustomWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		this.verificationCode = request.getParameter("code");

		// TODO Auto-generated constructor stub
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	
}

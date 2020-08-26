/**
 * 23-Aug-2020
 * CustomAuthenticationProvider.java 
 * ZAID
 */
package com.mypractice.security;

import java.util.ArrayList;

import org.jboss.aerogear.security.otp.Totp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.mypractice.model.User;
import com.mypractice.repository.UserRepository;

/**
 * @author ZAID
 *
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
private UserRepository userRepository;

	/**
 * @param userRepository
 */
@Autowired
public CustomAuthenticationProvider(final UserRepository userRepository) {
	super();
	this.userRepository = userRepository;
}

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		// TODO Auto-generated method stub
		String verificationCode = ((CustomWebAuthenticationDetails) auth.getDetails()).getVerificationCode();
		String password = auth.getCredentials().toString();
		User user = userRepository.findByEmail(auth.getName());
		if ((user == null)) {
			throw new BadCredentialsException("Invalid username or password");
		}
		Totp totp = new Totp(user.getSecret());
		if ( !totp.verify(verificationCode)) {
			throw new BadCredentialsException("Invalid verfication code");
		}

		//Authentication result = super.
		return new UsernamePasswordAuthenticationToken(user, password, new ArrayList<>() );
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}

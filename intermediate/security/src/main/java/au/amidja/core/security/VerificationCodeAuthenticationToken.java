package au.amidja.core.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class VerificationCodeAuthenticationToken extends UsernamePasswordAuthenticationToken{

	private static final long serialVersionUID = 5708338788020183074L;
	
	public VerificationCodeAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
	}
	
	
	/**
	 * This constructor should only be used by <code>AuthenticationManager</code> or
	 * <code>AuthenticationProvider</code> implementations that are satisfied with
	 * producing a trusted (i.e. {@link #isAuthenticated()} = <code>true</code>)
	 * authentication token.
	 *
	 * @param principal
	 * @param credentials
	 * @param authorities
	 */
	public VerificationCodeAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		
		super(principal, credentials, authorities);		
	}

}

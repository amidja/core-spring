package au.amidja.core.security.userdetails;

import org.springframework.security.core.userdetails.UserDetails;

public interface ICustomUserDetails extends UserDetails{
	
	/**
	 * Returns the secret used to authenticate the user.
	 *
	 * @return the secret
	 */
	String getSecret();
}

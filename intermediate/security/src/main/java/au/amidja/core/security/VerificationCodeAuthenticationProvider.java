package au.amidja.core.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

import au.amidja.persistence.dao.SystemUserReadDao;
import au.amidja.persistence.dao.adapter.SystemUserAdapter;
import au.amidja.persistence.model.SystemUser;

/**
 * Performs validation of verification codes
 */
public class VerificationCodeAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private static final Logger LOG = LoggerFactory.getLogger(VerificationCodeAuthenticationProvider.class);

	@Autowired(required = true)
	SystemUserAdapter adapter;

	@Autowired(required = true)
	private SystemUserReadDao systemUserReadDao;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		Assert.isInstanceOf(VerificationCodeAuthenticationToken.class, authentication,
				messages.getMessage("AbstractUserDetailsAuthenticationProvider.onlySupports",
						"Only UsernamePasswordAuthenticationToken is supported"));

		final String verificationCode = ((CustomWebAuthenticationDetails) authentication.getDetails()).getVerificationCode();
		
		LOG.debug(" ... found verification code [{}] in CustomWebAuthenticationDetails", verificationCode);
		LOG.debug(" ... found verification code [{}] in AuthenticationToke", authentication.getCredentials());
		
		SystemUser systemUser = retrieveUser(authentication.getName());		
		UserDetails userDetails = adapter.adapt(systemUser);
		
		
		//Assumption: all users are setup to use use two factor authentication 
		if (systemUser.isUsing2FA()){
			if (verificationCode != null && verificationCode.length() > 0 ){
				if (systemUser.getSecret().equals(verificationCode)){
			
					return new VerificationCodeAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(),
							userDetails.getAuthorities());
				}
			}	
		}
		
		throw new BadCredentialsException("Invalid verfication code");		
	}

	protected SystemUser retrieveUser(String username) {
		final SystemUser systemUser = systemUserReadDao.findByUserName(username);

		if ((systemUser == null)) {
			throw new UsernameNotFoundException("Invalid username provided");
		}

		return systemUser;				
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

			throw new UnsupportedOperationException("additionalAuthenticationChecks method is not supported");
	}
	
	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		SystemUser systemUser = retrieveUser(username);
		
		
		UserDetails loadedUser = adapter.adapt(systemUser);

		if (loadedUser == null) {
			throw new InternalAuthenticationServiceException(
					"UserDetailsService returned null, which is an interface contract violation");
		}

		return loadedUser;
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(VerificationCodeAuthenticationToken.class);
	}
}

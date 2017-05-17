package au.amidja.core.security;

import java.util.HashSet;
import java.util.Set;

import org.jboss.aerogear.security.otp.Totp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

import au.amidja.core.security.model.SystemRoleType;
import au.amidja.persistence.dao.ISystemUserReadDao;
import au.amidja.persistence.dao.adapter.ISystemUserAdapter;
import au.amidja.persistence.model.SystemUser;

/**
 * Performs validation of verification codes
 */
public class VerificationCodeAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private static final Logger LOG = LoggerFactory.getLogger(VerificationCodeAuthenticationProvider.class);

	@Autowired(required = true)
	ISystemUserAdapter adapter;

	@Autowired(required = true)
	private ISystemUserReadDao systemUserReadDao;

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
	        final Totp totp = new Totp(systemUser.getSecret());
			LOG.debug(" ... verification code [{}] is valid [{}]", authentication.getCredentials(), totp.verify(verificationCode));
	        //if (isValidLong(verificationCode) && totp.verify(verificationCode)) {
			if (totp.verify(verificationCode)) {
	        	LOG.debug(" ... verification code [{}] in AuthenticationToke is valid", authentication.getCredentials());
				//Add ADMIN _ROLE to users that are using 2FA and has provided valid 2FA
	        	Set<GrantedAuthority> userAuthorities = new HashSet<GrantedAuthority>();
	        	for (GrantedAuthority authority : userDetails.getAuthorities()){
	        		userAuthorities.add(authority);
	        	}
	        	
	        	userAuthorities.add(new SimpleGrantedAuthority(SystemRoleType.ROLE_ADMIN.name()));	        	
				return new VerificationCodeAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), userAuthorities);
	        }
		}
					
		throw new BadCredentialsException("Invalid verfication code");		
	}
	
			
	private boolean isValidLong(String code) {
        try {
            Long.parseLong(code);
        } catch (final NumberFormatException e) {
            return false;
        }
        return true;
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

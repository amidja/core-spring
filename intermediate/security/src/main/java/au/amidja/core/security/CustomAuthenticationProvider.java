package au.amidja.core.security;


import java.util.Arrays;

import org.jboss.aerogear.security.otp.Totp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import au.amidja.core.security.model.SystemRoleType;
import au.amidja.persistence.dao.SystemUserReadDao;
import au.amidja.persistence.dao.adapter.SystemUserAdapter;
import au.amidja.persistence.model.SystemRole;
import au.amidja.persistence.model.SystemUser;
import au.amidja.web.config.ApplicationConfig;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

	private static final Logger LOG = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
	
	@Autowired(required=true)
	SystemUserAdapter adapter;
	
	@Autowired(required=true)
	private SystemUserReadDao systemUserReadDao;
	
	/** 
	 * Customers authenticated using username and password are only given the GUEST role.
	 */
    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        
        final Authentication result = super.authenticate(auth);

        //Successful pwd authentication only gives the user GUEST role
        GrantedAuthority[] roles = new GrantedAuthority[1];
        roles[0] = new SimpleGrantedAuthority(SystemRoleType.ROLE_GUEST.name());		        
        return new UsernamePasswordAuthenticationToken(auth.getPrincipal(), result.getCredentials(), Arrays.asList(roles));
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
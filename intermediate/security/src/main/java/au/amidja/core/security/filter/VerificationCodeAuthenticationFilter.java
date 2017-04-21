package au.amidja.core.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import au.amidja.core.security.VerificationCodeAuthenticationToken;

public class VerificationCodeAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private static final Logger LOG = LoggerFactory.getLogger(VerificationCodeAuthenticationFilter.class);
	
	public static final String SPRING_SECURITY_FORM_CODE_KEY = "code";
	
	private String codeParameter = SPRING_SECURITY_FORM_CODE_KEY;
	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
	
		String username = obtainUsername(request);
		String code = obtainCode(request);
			
		VerificationCodeAuthenticationToken authRequest = new VerificationCodeAuthenticationToken(username, code);

		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	
	protected String obtainCode(HttpServletRequest request) {
		String code = request.getParameter(codeParameter);
		if (code == null){
			code = "";
		}
		code = code.trim();
		
		return code;
	}
	
	protected String obtainUsername(HttpServletRequest request) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

	
	/**
	 * Indicates whether this filter should attempt to process a login request for the
	 * current invocation.
	 * 
	 * @return <code>true</code> if the filter should attempt authentication,
	 * <code>false</code> otherwise.
	 */
	@Override
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		boolean requiredAuth = super.requiresAuthentication(request, response);

		if (requiredAuth){
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();			
			if (authentication == null) return false;
			
			if (!(authentication instanceof AnonymousAuthenticationToken)) {
				requiredAuth = true;
			    			
			    LOG.debug(" ... requires stepUp for [{}] ",  authentication.getName());
			    
			}else{
				requiredAuth = false;
			}
		}
		return requiredAuth;
	}

}

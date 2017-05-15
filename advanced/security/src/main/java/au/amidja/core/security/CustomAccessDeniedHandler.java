package au.amidja.core.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.util.StringUtils;

import au.amidja.core.security.model.SystemRoleType;

public class CustomAccessDeniedHandler implements AccessDeniedHandler{
	
	protected static final Logger LOG = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);
	
	private String errorPage;
	
	private String stepUpPage;

	/**
	 * It redirects authenticated users with the GUEST role to the step-up page.
	 */
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		
		if (!response.isCommitted()) {													
			
			//If we have authenticated guest user and stepUp page is provided
			if (isGuestUser()){
				if (stepUpPage != null){
					// Put exception into request scope (perhaps of use to a view)
					request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, accessDeniedException);
					
					// forward to error page.
					LOG.debug("... redirecting to stepUpPage [{}]", stepUpPage);
					RequestDispatcher dispatcher = request.getRequestDispatcher(stepUpPage);
					dispatcher.forward(request, response);
					
					return;
				}				
			}
			
			if (errorPage != null) {								
				
				// Put exception into request scope (perhaps of use to a view)
				request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);

				// Set the 403 status code.
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);

				// forward to error page.
				RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);
				dispatcher.forward(request, response);
			}
			else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
			}
		}

	}
	
	
	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}
	
	
	
	public void setStepUpPage(String stepUpPage) {
		this.stepUpPage = stepUpPage;
	}


	private boolean isGuestUser(){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null && authentication.isAuthenticated()){
			
			for ( GrantedAuthority authority : authentication.getAuthorities()){
				if (StringUtils.pathEquals(authority.getAuthority(), SystemRoleType.ROLE_GUEST.name())){
				
					String currentPrincipalName = authentication.getName();		
					LOG.debug("... current Principal Name is [{}]", currentPrincipalName);
				
					return true;
				}		
			}
		}
										
		return false;
	}

}

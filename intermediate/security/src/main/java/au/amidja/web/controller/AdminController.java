package au.amidja.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

	private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String msg = "This is default welcome page!";
		if (authentication != null){
			msg = "Welcome [" + authentication.getName() + "] !";
		}		
		
		model.addObject("message", msg);
		model.setViewName("hello");
		return model;
	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage(Authentication authentication) {

		LOG.debug("in adminPage controller for [{}]",  authentication.getName());
		
		ModelAndView model = new ModelAndView();
		
		model.addObject("title", "Spring Security");
		model.addObject("message", "This is protected page!");
		model.setViewName("admin");

		return model;
	}

	
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			String errorMessage = "Invalid username and password!";
						
			Object exception =   request.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
						
			if (exception != null){
				if (exception instanceof AuthenticationException){
					errorMessage = ((AuthenticationException)exception).getMessage();	
				}else if (exception instanceof AccessDeniedException) {
					//TODO: StepUp
					errorMessage = ((AccessDeniedException)exception).getMessage();
					model.addObject("stepup", true);				
				}
				
				LOG.debug("... login controller received an error [{}]", errorMessage);
			}
								
			model.addObject("error", errorMessage);			
		}

		
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
	
		model.setViewName("login");

		return model;
	}
}
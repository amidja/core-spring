package au.amidja.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GuestController {

	private static final Logger LOG = LoggerFactory.getLogger(GuestController.class);

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

	@RequestMapping(value = "/guest**", method = RequestMethod.GET)
	public ModelAndView guestPage(Authentication authentication) {

		LOG.debug("in guestPage controller for [{}]",  authentication.getName());
		
		ModelAndView model = new ModelAndView();
		
		model.addObject("title", "Spring Security");
		model.addObject("message", "This is protected guest page!");
		
		model.setViewName("guest");
		return model;
	}

}
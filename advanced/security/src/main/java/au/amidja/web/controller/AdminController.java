package au.amidja.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

	private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage(Authentication authentication) {

		LOG.debug("in adminPage controller for [{}]",  authentication.getName());
		
		ModelAndView model = new ModelAndView();
		
		model.addObject("title", "Spring Security");
		model.addObject("message", "This is protected admin page!");
		model.setViewName("admin");

		return model;
	}

}
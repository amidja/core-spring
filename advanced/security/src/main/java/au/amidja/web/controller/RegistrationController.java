package au.amidja.web.controller;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import au.amidja.persistence.dao.ISystemUserReadDao;
import au.amidja.persistence.model.SystemUser;
import au.amidja.service.IUserService;

@Controller
public class RegistrationController {

	private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);
	
	@Autowired(required=true)
	private ISystemUserReadDao systemUserReadDao;
	
	@Autowired(required=true)
	private IUserService userService;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView setupTwoFactorRegistration(Authentication authentication) {
						
		LOG.debug("in registration controller for [{}]", authentication.getName());
			
		String qrUrl = null; 
		try{
			qrUrl= userService.generateQRUrl(authentication.getName());
			LOG.debug("in registration controller, qrUrl [{}]", qrUrl);
		}catch (UnsupportedEncodingException ex){
			LOG.error(ex.getMessage());
		}
		
		SystemUser systemUser = systemUserReadDao.findByUserName(authentication.getName());
		
		
		
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security");
		model.addObject("message", "This is protected registration page!");		
		model.addObject("using2FA", systemUser.isUsing2FA().toString());
	
		if (!systemUser.isUsing2FA()){
			model.addObject("qrUrl", qrUrl);	
		}else{
			model.addObject("message", authentication.getName() + " is registered for 2FA!");
		}
				
		model.setViewName("registration");
		return model;
	}
		
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registerUserForTwoFactorAuth(Authentication authentication) {
						
		LOG.debug("in registration controller for [{}]", authentication.getName());
			
		userService.registerFor2FA(authentication.getName());
		
		LOG.debug("in registration controller, user [{}] is registered for 2FA", authentication.getName());
		
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security");
		model.addObject("message", authentication.getName() + " is registered for 2FA!");
		model.addObject("using2FA", true);
				
		model.setViewName("registration");						
		return model;
	}
}
package au.amidja.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author QJA266
 */	
@Controller
public class EchoController {

	private static final Logger logger = LoggerFactory.getLogger(EchoController.class);
				
	@RequestMapping(method=RequestMethod.GET, value="/echo")	
	public @ResponseBody String echoText(@RequestParam(value="text", defaultValue="hello there", required=false ) String text) {
		logger.trace("text = {}", text);		
		return text;			
	}
	
}

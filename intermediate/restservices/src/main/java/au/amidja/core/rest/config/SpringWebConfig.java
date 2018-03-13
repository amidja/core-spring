package au.amidja.core.rest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"au.amidja.core.rest.controller"})
public class SpringWebConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringWebConfig.class);

	public SpringWebConfig() {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("\t Rest WebConfig: started ....");
		}
	}
}

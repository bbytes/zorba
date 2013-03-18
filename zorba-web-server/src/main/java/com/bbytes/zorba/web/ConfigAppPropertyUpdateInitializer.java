package com.bbytes.zorba.web;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.ResourcePropertySource;


public class ConfigAppPropertyUpdateInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	private static Logger LOG = Logger.getLogger(ConfigAppPropertyUpdateInitializer.class);

	
	public void initialize(ConfigurableApplicationContext applicationContext) {
		ConfigurableEnvironment environment = applicationContext.getEnvironment();
		try {
			environment.getPropertySources().addFirst(new ResourcePropertySource("classpath:zorbaConf/zorba_app.properties"));
			LOG.info("Zorba properties loaded");
		} catch (IOException e) {
			// it's ok if the file is not there. we will just log that info.
			LOG.info("Didn't find zorbaConf/zorba_app.properties loaded in classpath so not loading it in the AppContextInitialized");
		}
	}

}

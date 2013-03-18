package com.bbytes.zorba.test;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/jetty-server.xml" })
public class EmbeddedServerTest {

	private static Logger logger = Logger.getLogger(EmbeddedServerTest.class);

	private static final String IDE_WAR_LOCATION = "src/main/webapp";

	@Autowired
	Server server;

	@Test
	public void startServer() {

		try {

			WebAppContext webAppContext = new WebAppContext();
			webAppContext.setContextPath("/");
			webAppContext.setWar(IDE_WAR_LOCATION);

			webAppContext.setServer(server);
			server.setHandler(webAppContext);
			Thread th = new Thread() {
				
				public void run() {
					try {
						server.start();
						server.join();
					} catch (Exception e) {
						logger.error("Error when starting", e);
					}
				}
			};
			th.start();
			
		} catch (Exception e) {
			logger.error("Error when starting", e);
		}
	}
	
	@Test
	public void stopServer() {
		try {
			server.stop();
		} catch (Exception e) {
			logger.error("Error when stoping", e);
		}
	}
}
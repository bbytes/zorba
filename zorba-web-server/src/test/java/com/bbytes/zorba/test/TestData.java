package com.bbytes.zorba.test;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/rootContext.xml" })
public class TestData {

	private static Logger logger = Logger.getLogger(EmbeddedServerTest.class);

	@Autowired
	Server server;

	@Before
	public void setUp() throws Exception {
		logger.debug("Hibernate session is bound");
	}

	@After
	public void tearDown() throws Exception {
		// single session mode

		logger.debug("Hibernate session is closed");
	}

	/**
	 * This simply verifies that test base loads and unloads stuff
	 * 
	 * @throws Exception
	 */
	@Test
	public void startServer() throws Exception {

		final AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();

		ServletHolder servletHolder = new ServletHolder(new DispatcherServlet(
				applicationContext));
		ServletContextHandler context = new ServletContextHandler();
		context.setContextPath("/test");
		context.addServlet(servletHolder, "/*");
		
		server.setHandler(context);

		server.start();
		server.join();

		Thread.currentThread().sleep(1000000);

	}

	private void startServer(Server server) throws Exception,
			InterruptedException {
		server.start();
		server.join();
	}

	private void setupServer(Server server, WebAppContext context) {
		server.setHandler(context);
	}

	private void setupContext(Server server, WebAppContext context) {
		context.setServer(server);
		context.setContextPath("/test");
		try {
			context.setWar(new ClassPathResource("webapp").getURI().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

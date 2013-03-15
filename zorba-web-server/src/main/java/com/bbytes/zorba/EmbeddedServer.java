package com.bbytes.zorba;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmbeddedServer {

	private static Logger logger = Logger.getLogger(EmbeddedServer.class);

	private static final String IDE_WAR_LOCATION = "src/main/webapp";

	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/jetty-server.xml");

		Server server = appContext.getBean(Server.class);

		try {

			WebAppContext webAppContext = new WebAppContext();
			webAppContext.setContextPath("/");
			webAppContext.setWar(IDE_WAR_LOCATION);

			webAppContext.setServer(server);
			server.setHandler(webAppContext);
			server.start();
			server.join();
			System.out.println("Tache web server running....");
			System.out.println("Enter :q and hit enter to quit: ");
			while (true) {

				BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
				String str = bufferRead.readLine();
				if (str != null && !str.isEmpty())
					if (str.trim().equals(":q")) {
						System.out.println("exiting system...");
						server.stop();
						System.exit(0);
					}
				Thread.sleep(1000);
			}
			
		} catch (Exception e) {
			logger.error("Error when starting", e);
		}
	}
}
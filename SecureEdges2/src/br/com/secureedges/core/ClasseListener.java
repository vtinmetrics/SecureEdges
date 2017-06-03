package br.com.secureedges.core;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.zu.ardulink.Link;

public class ClasseListener implements ServletContextListener {

	private Link link=Link.getDefaultInstance(); // 1
	public Link getLink() {
		return link;
	}
	public void setLink(Link link) {
		this.link = link;
	}

	public void contextInitialized(ServletContextEvent event) {
		// Comment this row if you use just the default connection
		// link = getDigisparkConnection();
		// Get connected port on your pc
		List<String> portList = link.getPortList(); // 2
		if (portList != null && portList.size() > 0) {
			String port = portList.get(0);
			System.out.println("Connecting on port: " + port);

			boolean connected = link.connect(port); // 3
			System.out.println("Connected:" + connected);
		} else

		{
			System.out.println("No port found!");
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}
}
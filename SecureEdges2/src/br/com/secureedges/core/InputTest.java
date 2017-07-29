
package br.com.secureedges.core;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.zu.ardulink.Link;
import org.zu.ardulink.event.AnalogReadChangeEvent;
import org.zu.ardulink.event.AnalogReadChangeListener;
import org.zu.ardulink.event.ConnectionEvent;
import org.zu.ardulink.event.ConnectionListener;
import org.zu.ardulink.event.DigitalReadChangeEvent;
import org.zu.ardulink.event.DigitalReadChangeListener;
import org.zu.ardulink.event.DisconnectionEvent;
import org.zu.ardulink.protocol.IProtocol;

public class InputTest implements ServletContextListener {
	public String temperatura;
	public String umidade;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		ClasseListener listener = new ClasseListener();
		Link link = listener.getLink();

		link.addAnalogReadChangeListener(new AnalogReadChangeListener() {

			@Override
			public void stateChanged(AnalogReadChangeEvent e) {
				// System.out.println("PIN: " + e.getPin() + " STATE: " + e.getValue());
				Integer aa = e.getValue();
				String aux = aa.toString();
				umidade = aux.substring(0, 2);
				temperatura = aux.substring(2, 4);
				 System.out.println("Essa e a temperatura " + temperatura);
				 System.out.println("Essa e a umidade " + umidade);
				// System.out.println(e.getIncomingMessage());
			}

			@Override
			public int getPinListening() {
				return 1;
			}

		});

	}

}

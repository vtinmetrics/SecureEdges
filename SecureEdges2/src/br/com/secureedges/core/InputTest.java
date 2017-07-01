/**
Copyright 2013 Luciano Zu project Ardulink http://www.ardulink.org/

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

@author Luciano Zu
*/

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
	public String umidade;
	public String temperatura;

	public String getTemperatura() {
		return temperatura;
	}

	public String getUmidade() {
		return umidade;
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public InputTest() {
		teste();
	}
	

	
	public void teste() {
		ClasseListener listener = new ClasseListener();
		Link link = listener.getLink();

		link.addAnalogReadChangeListener(new AnalogReadChangeListener() {

			@Override
			public void stateChanged(AnalogReadChangeEvent e) {
				//System.out.println("PIN: " + e.getPin() + " STATE: " + e.getValue());
				Integer aa = e.getValue();
				String aux = aa.toString();
				umidade = aux.substring(0, 2);
				temperatura = aux.substring(2, 4);
		//		System.out.println("Essa e a temperatura " + temperatura);
		//		System.out.println("Essa e a umidade " + umidade);
		//		System.out.println(e.getIncomingMessage());
			}

			@Override
			public int getPinListening() {
				return 1;
			}

		});

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

package br.com.secureedges.core;

import java.util.List;

import org.zu.ardulink.Link;
import org.zu.ardulink.event.AnalogReadChangeEvent;
import org.zu.ardulink.event.AnalogReadChangeListener;

public class verificaTemperatura {
	public static String umidade = null;
	public static String temperatura= null;

	public void run() {
		ClasseListener classeListener =  new ClasseListener();
		Link link = classeListener.getLink().getDefaultInstance();
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
		
		
		try {
			System.out.println("wait for a while");
			Thread.sleep(2000);
			System.out.println("proceed");
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		System.out.println("start Listening");
		link.addAnalogReadChangeListener(new AnalogReadChangeListener() {

			@Override
			public int getPinListening() {
				return 1;
			}

			@Override
			public void stateChanged(AnalogReadChangeEvent e) {
				System.out.println("PIN: " + e.getPin() + " STATE: " + e.getValue());
				System.out.println(e.getIncomingMessage());
				Integer aa = e.getValue();
				String aux = aa.toString();
				umidade = aux.substring(0, 2);
				temperatura = aux.substring(2, 4);

			}
		});

		for (int i = 0; i < 500; i++) {

		}

		try {
			System.out.println("wait for a while");
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		link.disconnect();
	}

}

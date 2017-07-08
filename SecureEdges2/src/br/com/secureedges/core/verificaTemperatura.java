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

		
		try {
			System.out.println("wait for a while");
			Thread.sleep(1000);
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
	}

}

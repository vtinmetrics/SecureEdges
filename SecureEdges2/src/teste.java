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


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.zu.ardulink.Link;
import org.zu.ardulink.RawDataListener;
import org.zu.ardulink.gui.ConnectionStatus;
import org.zu.ardulink.gui.DigitalPinStatus;
import org.zu.ardulink.gui.SerialConnectionPanel;
import org.zu.ardulink.gui.SwitchController;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;

public class teste extends JFrame {

	private static final long serialVersionUID = -8011033975724290405L;
	private JPanel contentPane;
	
	private JButton btnConnect;
	private JButton btnDisconnect;
	private SerialConnectionPanel serialConnectionPanel;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(NimbusLookAndFeel.class.getCanonicalName());
					teste frame = new teste();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public teste() {
		setTitle("Input Signal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		/*
		 * GUI to select connection port
		 */
		serialConnectionPanel = new SerialConnectionPanel();
		contentPane.add(serialConnectionPanel, BorderLayout.NORTH);
		
		/*
		 * GUI to show a PIN input status ON/OFF
		 */
		DigitalPinStatus digitalPinStatus = new DigitalPinStatus();
		contentPane.add(digitalPinStatus, BorderLayout.EAST);
		digitalPinStatus.setPin(3);
		
		/*
		 * Code for connect and disconnect buttons
		 */
		JPanel connectPanel = new JPanel();
		contentPane.add(connectPanel, BorderLayout.SOUTH);

		btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String comPort = serialConnectionPanel.getConnectionPort();
				String baudRateS = serialConnectionPanel.getBaudRate();
						
				try {
					int baudRate = Integer.parseInt(baudRateS);
										
					Link.getDefaultInstance().connect(comPort, baudRate);
				}
				catch(Exception ex) {
					ex.printStackTrace();
					String message = ex.getMessage();
					if(message == null || message.trim().equals("")) {
						message = "Generic Error on connection";
					}
					JOptionPane.showMessageDialog(btnConnect, message, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		connectPanel.add(btnConnect);
		
		btnDisconnect = new JButton("Disconnect");
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Link.getDefaultInstance().disconnect();
			}
		});
		connectPanel.add(btnDisconnect);
		
		ConnectionStatus connectionStatus = new ConnectionStatus();
		connectPanel.add(connectionStatus);
		
		/*
		 * Button to turn on/off a PIN
		 */
		SwitchController switchController = new SwitchController();
		contentPane.add(switchController, BorderLayout.WEST);
		switchController.setPin(5);
		
		/*
		 * Code to print to stdout messages from Arduino
		 */
		Link.getDefaultInstance().addRawDataListener(new RawDataListener() {
			
			@Override
			public void parseInput(String id, int numBytes, int[] message) {
				StringBuilder build = new StringBuilder(numBytes + 1);
				for (int i = 0; i < numBytes; i++) {
					build.append((char)message[i]);
				}
				System.out.println(build.toString());
			}
		});
	}
}

package com.github.awvalenti.bauhinia.coronata.demo3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.github.awvalenti.bauhinia.coronata.Coronata;
import com.github.awvalenti.bauhinia.coronata.CoronataBuilder;
import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemote;
import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemoteButton;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleStateObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataDisconnectionObserver;

public class WindowWithIntegratedObserver extends JFrame implements CoronataConnectionObserver,
		CoronataButtonObserver, CoronataLifecycleStateObserver, CoronataDisconnectionObserver {

	private static final long serialVersionUID = 1L;

	private final JLabel lblStatus = new JLabel("", JLabel.CENTER);
	private final JButton btnConnect = new JButton("Connect");

	public WindowWithIntegratedObserver() {
		super("Coronata Demo");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(lblStatus, BorderLayout.CENTER);
		add(btnConnect, BorderLayout.SOUTH);

		setSize(300, 150);
		setLocationRelativeTo(null);
	}

	public void setCoronata(final Coronata coronata) {
		btnConnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				coronata.run();
				btnConnect.setEnabled(false);
			}
		});
	}

	@Override
	public void connected(CoronataWiiRemote wiiRemote) {
		wiiRemote.setLightedLEDs(CoronataWiiRemote.LED_0);
	}

	@Override
	public void disconnected() {
		lblStatus.setText("Wii Remote disconnected");
	}

	@Override
	public void buttonPressed(CoronataWiiRemoteButton button) {
		lblStatus.setText(button + " pressed");
	}

	@Override
	public void buttonReleased(CoronataWiiRemoteButton button) {
		lblStatus.setText(button + " released");
	}

	@Override
	public void enteredIdleState() {
		lblStatus.setText("Idle");
	}

	@Override
	public void enteredInProcessState() {
		lblStatus.setText("In process...");
	}

	@Override
	public void enteredConnectedState() {
		lblStatus.setText("Connected! Press Wii Remote buttons to test.");
	}

	public static void main(String[] args) {
		WindowWithIntegratedObserver window = new WindowWithIntegratedObserver();
	
		Coronata coronata = CoronataBuilder.beginConfig()
				.asynchronous()	// Because this is a graphical application
				.oneWiiRemote()
				.onConnection(window)
				.onButton(window)
				.onLifecycleState(window)
				.onDisconnection(window)
				.endConfig();
	
		window.setCoronata(coronata);
	
		window.setVisible(true);
	}

}

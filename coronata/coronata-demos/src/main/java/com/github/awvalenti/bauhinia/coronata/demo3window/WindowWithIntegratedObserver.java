package com.github.awvalenti.bauhinia.coronata.demo3window;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.github.awvalenti.bauhinia.coronata.CoronataBuilder;
import com.github.awvalenti.bauhinia.coronata.CoronataConnectionProcess;
import com.github.awvalenti.bauhinia.coronata.CoronataException;
import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemote;
import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemoteButton;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataDisconnectionObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataErrorObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleStateObserver;

public class WindowWithIntegratedObserver extends JFrame implements CoronataConnectionObserver,
		CoronataButtonObserver, CoronataLifecycleStateObserver, CoronataDisconnectionObserver,
		CoronataErrorObserver {

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

	public void setCoronata(final CoronataConnectionProcess coronata) {
		btnConnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnConnect.setEnabled(false);
				coronata.start();
			}
		});
	}

	@Override
	public void errorOccurred(CoronataException e) {
		lblStatus.setText("Error: " + e.toString());
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
		btnConnect.setEnabled(true);
	}

	@Override
	public void enteredInProcessState() {
		lblStatus.setText("In process...");
	}

	@Override
	public void enteredConnectedState() {
		lblStatus.setText("Connected! Press buttons to test.");
	}

	public static void main(String[] args) {
		WindowWithIntegratedObserver window = new WindowWithIntegratedObserver();

		CoronataConnectionProcess coronata = CoronataBuilder.beginConfig()
				.onError(window)
				.onConnection(window)
				.onButton(window)
				.onLifecycleState(window)
				.onDisconnection(window)
				.endConfig();

		window.setCoronata(coronata);

		window.setVisible(true);
	}

}

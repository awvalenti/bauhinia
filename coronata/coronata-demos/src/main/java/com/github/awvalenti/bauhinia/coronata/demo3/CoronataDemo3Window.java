package com.github.awvalenti.bauhinia.coronata.demo3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.github.awvalenti.bauhinia.coronata.CoronataConnector;
import com.github.awvalenti.bauhinia.coronata.WiiRemote;
import com.github.awvalenti.bauhinia.coronata.WiiRemoteButton;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteButtonListener;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionStateObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataWiiRemoteConnectionObserver;

public class CoronataDemo3Window extends JFrame implements CoronataWiiRemoteConnectionObserver,
		WiiRemoteButtonListener, CoronataConnectionStateObserver {

	private static final long serialVersionUID = 1L;

	private final JLabel lblStatus = new JLabel("", JLabel.CENTER);
	private final JButton btnConnect = new JButton("Connect");

	public CoronataDemo3Window() {
		super("Coronata Demo");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(lblStatus, BorderLayout.CENTER);
		add(btnConnect, BorderLayout.SOUTH);

		setSize(300, 150);
		setLocationRelativeTo(null);
	}

	public void setConnector(final CoronataConnector connector) {
		btnConnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				connector.run();
				btnConnect.setEnabled(false);
			}
		});
	}

	@Override
	public void wiiRemoteConnected(WiiRemote wiiRemote) {
		wiiRemote.turnLedOn(0);
	}

	@Override
	public void buttonPressed(WiiRemoteButton button) {
		lblStatus.setText(button + " pressed");
	}

	@Override
	public void buttonReleased(WiiRemoteButton button) {
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

}

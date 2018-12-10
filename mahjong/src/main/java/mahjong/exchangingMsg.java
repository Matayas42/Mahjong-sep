package mahjong;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*; 

public class exchangingMsg  implements Serializable, ActionListener, WindowListener {

	static final int TALK = 1, LOGOUT = 2;
	private int type;
	private String message;
	
	private JFrame frame;
	private JPanel btnPanel,mainPanel;
	private JButton connect, wait, exit;
	private JLabel title;
	
	exchangingMsg(int type, String message) {
		this.type = type;
		this.message = message;
	}
	
	// getters
	int getType() {
		return type;
	}
	String getMessage() {
		return message;
	}

	public exchangingMsg()
	{
		frame = new JFrame();
		btnPanel = new JPanel();
		mainPanel = new JPanel(new GridLayout(3, 3));
		connect = new JButton("Start Server");
		wait = new JButton("New Player");
		exit = new JButton("Exit");
		title = new JLabel("Multiplayer Majong Game", SwingConstants.CENTER);
		title.setFont(new Font("Helvetica Neue", Font.PLAIN, 32));
		
		connect.addActionListener(this);
		wait.addActionListener(this);
		exit.addActionListener(this);
		
		btnPanel.add(connect);
		btnPanel.add(wait);
		btnPanel.add(exit);
		
		mainPanel.add(title, BorderLayout.NORTH);
		mainPanel.add(btnPanel, BorderLayout.CENTER);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mainPanel);
		frame.addWindowListener(this);		
		frame.setSize(400, 600);		
		frame.setVisible(true);

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Start Server")) {
			
			ServerGUI ser = new ServerGUI(1500);
			connect.setEnabled(false);
		     
		    }else if(e.getActionCommand().equals("New Player")) {
		    	
		    	ClientGUI cli = new ClientGUI("localhost", 1500);
			     	    	
		    }else if(e.getActionCommand().equals("Exit"))
		    {
		    	System.exit(0);
		    }
		
	}
	
	public static void main(String args[])
	{
		new exchangingMsg();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}

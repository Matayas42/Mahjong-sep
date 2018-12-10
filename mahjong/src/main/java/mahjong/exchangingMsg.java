package mahjong;

import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel; 

public class exchangingMsg  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -984123784695014109L;
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
	
}

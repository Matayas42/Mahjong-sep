package mahjong;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

// most source taken from CS3201 project
// added code is marked with comments
/*
 * The server that can be run both as a console application or a GUI
 */
public class Server {
	// a unique ID for each connection
	private static int uniqueId = 0;
	// an ArrayList to keep the list of the Client
	private ArrayList<ClientThread> al;
	// if I am in a GUI
	private ServerGUI sg;

	private ObjectInputStream sInput; // to read from the socket
	private ObjectOutputStream sOutput; // to write on the socket
	private Socket socket;
	// to display time
	private int port;
	// the boolean that will be turned of to stop the server
	private boolean keepGoing;

	/*
	 * server constructor that receive the port to listen to for connection as
	 * parameter in console
	 */
	public Server(int port) {
		this(port, null);
	}

	public Server(int port, ServerGUI sg) {
		// GUI or not
		this.sg = sg;
		// the port
		this.port = port;
		// to display hh:mm:ss
		// ArrayList for the Client list
		al = new ArrayList<ClientThread>();
	}

	public void start() {
		keepGoing = true;
		/* create socket server and wait for connection requests */

		try {
			// the socket used by the server
			ServerSocket serverSocket = new ServerSocket(port);

			// infinite loop to wait for connections
			while (keepGoing) {
				// format message saying we are waiting
				display("Server waiting for Clients on port " + port + ".");

				Socket socket = serverSocket.accept();
				// if I was asked to stop
				if (!keepGoing)
					break;
				ClientThread t = new ClientThread(socket); // make a thread of it
				al.add(t); // save it in the ArrayList
				t.start();
///////////////////////////////////////////////////////////////////////////////////////
// CODE ADDED FOR MAHJONG GAME
///////////////////////////////////////////////////////////////////////////////////////
				if (al.size() == 4) {
					Game g = new Game(true);
					g.start();
					while (g.isAlive()) {
						int currentUser = -1;
						String currentMsg = "";
						while (g.isOutputNeeded()) {
							currentUser = g.getInteractUserId();
							currentMsg = g.getOutputString();
							if (currentUser == -1)
								broadcast(currentMsg);
							else {
								for (ClientThread thread : al) {
									if (thread.id == currentUser) {
										currentMsg = currentMsg + "\n";
										thread.writeMsg(currentMsg);
									}
								}
							}
						}
						if (g.isInputNeeded() && !g.isNewInput() && g.isInputStringEmpty()) {
							for (ClientThread thread : al) {
								if (thread.id == currentUser) {
									String tmpInput = "";
									do {
										tmpInput = thread.getUserInput();
									} while (tmpInput.isEmpty());
									g.setInputString(tmpInput);
									g.setNewInput();
									break;
								}
							}
						}
					}
				}
////////////////////////////////////////////////////////////////////////////////////////				
			}

			try {
				serverSocket.close();
				for (int i = 0; i < al.size(); ++i) {
					ClientThread tc = al.get(i);
					try {
						tc.sInput.close();
						tc.sOutput.close();
						tc.socket.close();
					} catch (IOException ioE) {
						// not much I can do
					}
				}
			} catch (Exception e) {
				display("Exception closing the server and clients: " + e);
			}
		}
		// something went bad
		catch (IOException e) {
			String msg = " Exception on new ServerSocket: " + e + "\n";
			display(msg);
		}
	}

	/*
	 * For the GUI to stop the server
	 */
	protected void stop() {
		keepGoing = false;
		// connect to myself as Client to exit statement
		// Socket socket = serverSocket.accept();
		try {
			new Socket("localhost", port);
		} catch (Exception e) {
			// nothing I can really do
		}
	}

	/*
	 * Display an event (not a message) to the console or the GUI
	 */
	private void display(String msg) {
		String show = msg;
		if (sg == null)
			System.out.println(show);
		else
			sg.appendEvent(show + "\n");
	}

	/*
	 * to broadcast a message to all Clients
	 */
	private synchronized void broadcast(String message) {
		String messageLf = message + "\n";
		// display message on console or GUI
		if (sg == null)
			System.out.print(messageLf);
		else
			sg.appendRoom(messageLf); // append in the room window

		// we loop in reverse order in case we would have to remove a Client
		// because it has disconnected
		for (int i = al.size(); --i >= 0;) {
			ClientThread ct = al.get(i);
			// try to write to the Client if it fails remove it from the list
			if (!ct.writeMsg(messageLf)) {
				al.remove(i);
				display("Disconnected Client " + ct.username + " removed from list.");
			}
		}
	}

	// for a client who logoff using the LOGOUT message
	synchronized void remove(int id) {
		// scan the array list until we found the Id
		for (int i = 0; i < al.size(); ++i) {
			ClientThread ct = al.get(i);
			// found it
			if (ct.id == id) {
				al.remove(i);
				return;
			}
		}
	}

	/*
	 * To run as a console application just open a console window and: > java Server
	 * > java Server portNumber If the port number is not specified 1500 is used
	 */
	public static void main(String[] args) {
		// start server on port 1500 unless a PortNumber is specified
		int portNumber = 1500;
		switch (args.length) {
		case 1:
			try {
				portNumber = Integer.parseInt(args[0]);
			} catch (Exception e) {
				System.out.println("Invalid port number.");
				System.out.println("Usage is: > java Server [portNumber]");
				return;
			}
		case 0:
			break;
		default:
			System.out.println("Usage is: > java Server [portNumber]");
			return;

		}
		// create a server object and start it
		Server server = new Server(portNumber);
		server.start();
	}

	/** One instance of this thread will run for each client */
	class ClientThread extends Thread {
		// the socket where to listen/talk
		Socket socket;
		ObjectInputStream sInput;
		ObjectOutputStream sOutput;
		int id;
		String username;
		exchangingMsg chitchat;
		private String userInput = "";

		public String getUserInput() {
			String tmpUserInput = userInput;
			userInput = "";
			return tmpUserInput;
		}

		ClientThread(Socket socket) {
			id = ++uniqueId;
			this.socket = socket;
			System.out.println("Thread trying to create Object Input/Output Streams");
			try {
				sOutput = new ObjectOutputStream(socket.getOutputStream());
				sInput = new ObjectInputStream(socket.getInputStream());
				username = (String) sInput.readObject();
				display(username + " just connected.");
			} catch (IOException e) {
				display("Exception creating new Input/output Streams: " + e);
				return;
			}
			// have to catch ClassNotFoundException
			// but I read a String, I am sure it will work
			catch (ClassNotFoundException e) {
			}

		}

		// what will run forever
		public void run() {
			// to loop until LOGOUT
			boolean keepGoing = true;
			while (keepGoing) {
				// read a String (which is an object)
				try {
					chitchat = (exchangingMsg) sInput.readObject();
				} catch (IOException e) {
					display(username + " Exception reading Streams: " + e);
					break;
				} catch (ClassNotFoundException e2) {
					break;
				}
				// the messaage part of the ChatMessage
				String message = chitchat.getMessage();

				// Switch on the type of message receive
				switch (chitchat.getType()) {

				case exchangingMsg.TALK:
				// next line changed for Mahjong Game
					userInput = message;
					// broadcast(username + ": " + message);
					break;
				case exchangingMsg.LOGOUT:
					display(username + " disconnected with a LOGOUT message.");
					keepGoing = false;
					break;
				}
			}
			// remove myself from the arrayList containing the list of the
			// connected Clients
			remove(id);
			close();
		}

		// try to close everything
		private void close() {
			// try to close the connection
			try {
				if (sOutput != null)
					sOutput.close();
			} catch (Exception e) {
			}
			try {
				if (sInput != null)
					sInput.close();
			} catch (Exception e) {
			}
			;
			try {
				if (socket != null)
					socket.close();
			} catch (Exception e) {
			}
		}

		/*
		 * Write a String to the Client output stream
		 */
		private boolean writeMsg(String msg) {
			// if Client is still connected send the message to it
			if (!socket.isConnected()) {
				close();
				return false;
			}
			// write the message to the stream
			try {
				sOutput.writeObject(msg);
			}
			// if an error occurs, do not abort just inform the user
			catch (IOException e) {
				display("Error sending message to " + username);
				display(e.toString());
			}
			return true;
		}
	}

	public void sendMessage(exchangingMsg exchangingMsg) {
		// TODO Auto-generated method stub

	}
}

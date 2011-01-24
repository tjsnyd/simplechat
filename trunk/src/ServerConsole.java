
import java.io.BufferedReader;
import java.io.InputStreamReader;

import server.EchoServer;

import common.ChatIF;

public class ServerConsole implements ChatIF {

	public EchoServer server;

	/**
	 * The default port to listen on.
	 */
	final public static int DEFAULT_PORT = 5555;

	public ServerConsole(int port) {
		server = new EchoServer(port, this);
	}

	public void display(String message) {
		System.out.println(message);
	}

	public void accept() {
		try {
			BufferedReader fromConsole = new BufferedReader(new InputStreamReader(System.in));
			String message;

			while (true) {
				message = fromConsole.readLine();
				server.handleMessageFromServerUI(message);
			}
		} catch (Exception ex) {
			System.out.println("Unexpected error while reading from console!");
		}
	}

	/**
	 * This method is responsible for the creation of the server instance (there is no UI in this phase).
	 * 
	 * @param args
	 *            [0] The port number to listen on. Defaults to 5555 if no argument is entered.
	 */
	public static void main(String[] args) {
		int port = 0; // Port to listen on

		try {
			port = Integer.parseInt(args[0]); // Get port from command line
		} catch (Throwable t) {
			port = DEFAULT_PORT; // Set port to 5555
		}

		ServerConsole sv = new ServerConsole(port);
		sv.accept();
	}
}

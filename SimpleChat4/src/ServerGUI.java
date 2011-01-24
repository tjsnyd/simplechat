import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import server.EchoServer;

import common.ChatIF;

public class ServerGUI extends JFrame implements ChatIF {

	final public static int DEFAULT_PORT = 5555;
	private static final long serialVersionUID = 1L;

	private JTextPane log;
	private JScrollPane scroll;
	private JButton start;
	private JButton stop;
	private JButton close;
	private JButton quit;
	private JLabel numPort;
	private JButton setPort;
	private JPanel topPanel;
	private JPanel topLeftPanel;
	private JPanel topRightPanel;
	private JPanel botPanel;
	private JTextField message;
	private JButton sendMessage;
	private Container contentPane;

	public EchoServer server;

	public ServerGUI(int port) {
		super("GCAM Chat Server");

		log = new JTextPane();
		server = new EchoServer(port, this);
		start = new JButton("Demarrer le serveur");
		stop = new JButton("Arreter le server");
		close = new JButton("Fermer le server");
		quit = new JButton("Quitter");
		numPort = new JLabel("Port = " + EchoServer.getPort());
		setPort = new JButton("Changer de port");
		topRightPanel = new JPanel();
		topLeftPanel = new JPanel();
		topPanel = new JPanel();
		scroll = new JScrollPane(log);
		botPanel = new JPanel();
		sendMessage = new JButton("Envoyer");
		message = new JTextField(50);

		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		log.setEditable(false);
		log.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent arg0) {
				message.requestFocus();
			}

			public void mousePressed(MouseEvent arg0) {
				message.requestFocus();
			}

			public void mouseExited(MouseEvent arg0) {
				message.requestFocus();
			}

			public void mouseEntered(MouseEvent arg0) {
				message.requestFocus();
			}

			public void mouseClicked(MouseEvent arg0) {
				message.requestFocus();
			}
		});

		message.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent arg0) {
			}

			public void keyTyped(KeyEvent arg0) {
			}

			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER && !message.getText().equals("")) {
					server.handleMessageFromServerUI(message.getText());
					message.setText("");
				}
			}
		});

		sendMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!message.getText().equals("")) {
					server.handleMessageFromServerUI(message.getText());
					message.setText("");
				}
			}
		});

		sendMessage.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent arg0) {
				message.requestFocus();
			}

			public void mousePressed(MouseEvent arg0) {
				message.requestFocus();
			}

			public void mouseExited(MouseEvent arg0) {
				message.requestFocus();
			}

			public void mouseEntered(MouseEvent arg0) {
				message.requestFocus();
			}

			public void mouseClicked(MouseEvent arg0) {
				message.requestFocus();
			}
		});

		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				server.handleMessageFromServerUI("#start");
			}
		});

		start.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent arg0) {
				message.requestFocus();
			}

			public void mousePressed(MouseEvent arg0) {
				message.requestFocus();
			}

			public void mouseExited(MouseEvent arg0) {
				message.requestFocus();
			}

			public void mouseEntered(MouseEvent arg0) {
				message.requestFocus();
			}

			public void mouseClicked(MouseEvent arg0) {
				message.requestFocus();
			}
		});

		stop.setEnabled(false);
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				server.handleMessageFromServerUI("#stop");
			}
		});

		stop.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent arg0) {
				message.requestFocus();
			}

			public void mousePressed(MouseEvent arg0) {
				message.requestFocus();
			}

			public void mouseExited(MouseEvent arg0) {
				message.requestFocus();
			}

			public void mouseEntered(MouseEvent arg0) {
				message.requestFocus();
			}

			public void mouseClicked(MouseEvent arg0) {
				message.requestFocus();
			}
		});

		close.setEnabled(false);
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				server.handleMessageFromServerUI("#close");
			}
		});

		close.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent arg0) {
				message.requestFocus();
			}

			public void mousePressed(MouseEvent arg0) {
				message.requestFocus();
			}

			public void mouseExited(MouseEvent arg0) {
				message.requestFocus();
			}

			public void mouseEntered(MouseEvent arg0) {
				message.requestFocus();
			}

			public void mouseClicked(MouseEvent arg0) {
				message.requestFocus();
			}
		});

		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				server.handleMessageFromServerUI("#quit");
			}
		});

		setPort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str =
						JOptionPane.showInputDialog(null, "Veuillez saisir votre port",
								"Changer de port", JOptionPane.QUESTION_MESSAGE);
				if (str != null) {
					server.handleMessageFromServerUI("#setport " + str);
				}
				message.requestFocus();
			}
		});

		contentPane = getContentPane();
		contentPane.add(topPanel, BorderLayout.NORTH);
		contentPane.add(scroll, BorderLayout.CENTER);
		contentPane.add(botPanel, BorderLayout.SOUTH);
		topPanel.add(topLeftPanel);
		topPanel.add(topRightPanel);
		topLeftPanel.setLayout(new GridLayout(0, 1));
		topLeftPanel.add(start);
		topLeftPanel.add(stop);
		topLeftPanel.add(close);
		topRightPanel.setLayout(new GridLayout(0, 1));
		topRightPanel.add(numPort);
		topRightPanel.add(setPort);
		topRightPanel.add(quit);
		botPanel.setLayout(new BoxLayout(botPanel, BoxLayout.LINE_AXIS));
		botPanel.add(message);
		botPanel.add(sendMessage);

		setVisible(true);
		setSize(350, 400);
		setLocationRelativeTo(null);
		setLocation(getLocation().x - 300, getLocation().y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void display(String message) {

		if (message.equals(EchoServer.getServerStarted() + EchoServer.getPort())) {
			start.setEnabled(false);
			stop.setEnabled(true);
			close.setEnabled(true);
			setPort.setEnabled(false);
		} else if (message.equals(EchoServer.getServerClosed())) {
			start.setEnabled(true);
			stop.setEnabled(false);
			close.setEnabled(false);
			setPort.setEnabled(true);
		} else if (message.equals(EchoServer.getServerStopped())) {
			start.setEnabled(true);
			stop.setEnabled(false);
		} else if (message.equals(EchoServer.getPortChanged() + EchoServer.getPort())) {
			numPort.setText("Port = " + EchoServer.getPort());
		}
		Calendar c = Calendar.getInstance();
		log.setText(log.getText() + "[" + (c.get(Calendar.HOUR_OF_DAY) < 10 ? "0" : "")
				+ c.get(Calendar.HOUR_OF_DAY) + ":" + (c.get(Calendar.MINUTE) < 10 ? "0" : "")
				+ c.get(Calendar.MINUTE) + ":" + (c.get(Calendar.SECOND) < 10 ? "0" : "")
				+ c.get(Calendar.SECOND) + "] " + message + '\n');
		log.setCaretPosition((int) log.getDocument().getLength());
	}

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		int port = 0; // Port to listen on

		try {
			port = Integer.parseInt(args[0]); // Get port from command line
		} catch (Throwable t) {
			port = DEFAULT_PORT; // Set port to 5555
		}

		new ServerGUI(port);
	}
}

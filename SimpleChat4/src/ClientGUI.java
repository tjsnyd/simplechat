import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import client.ChatClient;

import common.ChatIF;

public class ClientGUI extends JFrame implements ChatIF {

	final public static int DEFAULT_PORT = 5555;
	private static final long serialVersionUID = 1L;

	private JMenuBar menu;
	private JMenu connection;
	private JMenuItem login;
	private JMenuItem logoff;
	private JMenuItem quit;
	private JMenu settings;
	private JMenuItem pseudo;
	private JMenuItem mhost;
	private JMenuItem mport;
	private JMenu time;
	private JScrollPane scroll;
	private JScrollPane scrollUserList;
	private JTextField message;
	private JTextPane showMessage;
	private JTextPane userList;
	private JButton sendMessage;
	private Container contentPane;
	private JPanel botPanel;

	private JRadioButtonMenuItem showTime = new JRadioButtonMenuItem(
			"Afficher l'heure");
	private JRadioButtonMenuItem hideTime = new JRadioButtonMenuItem(
			"Ne pas afficher l'heure");

	ChatClient client;

	public ClientGUI(String host, int port) {
		super("GCAM Chat Client");

		menu = new JMenuBar();
		connection = new JMenu("Connexion");
		login = new JMenuItem("Se connecter");
		logoff = new JMenuItem("Se deconnecter");
		quit = new JMenuItem("Quitter");
		settings = new JMenu("Configuration");
		pseudo = new JMenuItem("Changer de pseudo");
		mhost = new JMenuItem("Changer d'hote");
		mport = new JMenuItem("Changer de port");
		time = new JMenu("Heure");
		botPanel = new JPanel();
		showMessage = new JTextPane();
		userList = new JTextPane();
		sendMessage = new JButton("Envoyer");
		message = new JTextField(50);
		scroll = new JScrollPane(showMessage);
		scrollUserList = new JScrollPane(userList);
		ButtonGroup bg = new ButtonGroup();

		menu.add(connection);
		connection.add(login);
		connection.add(logoff);
		connection.addSeparator();
		connection.add(quit);
		menu.add(settings);
		bg.add(showTime);
		bg.add(hideTime);
		time.add(showTime);
		time.add(hideTime);
		settings.add(pseudo);
		settings.addSeparator();
		settings.add(mhost);
		settings.add(mport);
		settings.add(time);

		hideTime.setSelected(true);

		this.setJMenuBar(menu);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollUserList.setPreferredSize(new Dimension(100, 0));
		showMessage.setEditable(false);
		userList.setEditable(false);

		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = JOptionPane.showInputDialog(null,
						"Veuillez saisir votre pseudo", "Se connecter",
						JOptionPane.QUESTION_MESSAGE);
				if (str != null) {
					sendMessage("#login " + str);
				}
			}
		});

		logoff.setEnabled(false);
		logoff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendMessage("#logoff");
				login.setEnabled(true);
				logoff.setEnabled(false);
			}
		});

		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendMessage("#quit");
			}
		});

		pseudo.setEnabled(false);
		pseudo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = JOptionPane.showInputDialog(null,
						"Veuillez saisir votre pseudo", "Changer de pseudo",
						JOptionPane.QUESTION_MESSAGE);
				if (str != null) {
					sendMessage("#pseudo " + str);
				}
			}
		});

		mhost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = JOptionPane.showInputDialog(null,
						"Veuillez saisir votre hote", "Changer d'hote",
						JOptionPane.QUESTION_MESSAGE);
				if (str != null) {
					sendMessage("#sethost " + str);
				}
			}
		});

		mport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = JOptionPane.showInputDialog(null,
						"Veuillez saisir votre port", "Changer de port",
						JOptionPane.QUESTION_MESSAGE);
				if (str != null) {
					sendMessage("#setport " + str);
				}
			}
		});

		message.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent arg0) {
			}

			public void keyTyped(KeyEvent arg0) {
			}

			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER
						&& !message.getText().equals("")) {
					sendMessage();
				}
			}
		});

		sendMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!message.getText().equals("")) {
					sendMessage();
				}
				message.requestFocus();
			}
		});

		showMessage.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				message.requestFocus();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				message.requestFocus();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				message.requestFocus();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				message.requestFocus();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				message.requestFocus();
			}
		});

		userList.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				message.requestFocus();
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				message.requestFocus();
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				message.requestFocus();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				message.requestFocus();
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				message.requestFocus();
			}
		});

		botPanel.setLayout(new BoxLayout(botPanel, BoxLayout.LINE_AXIS));
		botPanel.add(message);
		botPanel.add(sendMessage);

		contentPane = getContentPane();
		contentPane.add(scroll, BorderLayout.CENTER);
		contentPane.add(scrollUserList, BorderLayout.EAST);
		contentPane.add(botPanel, BorderLayout.SOUTH);

		setVisible(true);
		setSize(680, 400);
		setLocationRelativeTo(null);
		setLocation(getLocation().x + 215, getLocation().y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			client = new ChatClient(host, port, this);
		} catch (IOException exception) {
			System.out.println("Error: Can't setup connection!"
					+ " Terminating client.");
			System.exit(1);
		}
	}

	public void sendMessage() {
		client.handleMessageFromClientUI(message.getText());
		message.setText("");
	}

	public void sendMessage(String message) {
		client.handleMessageFromClientUI(message);
	}

	public void display(String message) {

		if (message.startsWith("$")) {
			userList.setText(message.substring(1));
		} else {
			if (message.equals(ChatClient.getConnectionClosed())) {
				login.setEnabled(true);
				logoff.setEnabled(false);
				pseudo.setEnabled(false);
				mhost.setEnabled(true);
				mport.setEnabled(true);
				userList.setText("");
			} else if (message.equals(ChatClient.getConnectionEstablished())) {
				login.setEnabled(false);
				logoff.setEnabled(true);
				pseudo.setEnabled(true);
				mhost.setEnabled(false);
				mport.setEnabled(false);
			}

			if (showTime.isSelected()) {
				Calendar c = Calendar.getInstance();
				message = "[" + (c.get(Calendar.HOUR_OF_DAY) < 10 ? "0" : "")
						+ c.get(Calendar.HOUR_OF_DAY) + ":"
						+ (c.get(Calendar.MINUTE) < 10 ? "0" : "")
						+ c.get(Calendar.MINUTE) + ":"
						+ (c.get(Calendar.SECOND) < 10 ? "0" : "")
						+ c.get(Calendar.SECOND) + "] " + message;
			} else {
				showMessage.setText(showMessage.getText() + message + '\n');
			}
			showMessage.setCaretPosition((int) showMessage.getDocument()
					.getLength());
		}
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

		String host = "";
		int port = 0; // The port number

		try {
			host = args[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			host = "localhost";
		}

		try {
			port = Integer.parseInt(args[1]);
		} catch (ArrayIndexOutOfBoundsException e) {
			port = DEFAULT_PORT;
		}

		new ClientGUI(host, port);
	}
}

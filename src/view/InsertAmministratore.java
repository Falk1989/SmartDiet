package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import dbAccess.DbInterface;

public class InsertAmministratore extends JInternalFrame {
	private JLabel lblNickname;
	private JLabel lblPassword;
	private JTextField fieldNickName;
	private JLabel label;
	private JComboBox comboProfilo;
	private DesktopWindow desktopWindow;
	private DbInterface dbInterface = new DbInterface();
	private JLabel imgCheckNickName;
	private JButton btnRegistra;
	private JPasswordField fieldPassword;

	/**
	 * Create the frame.
	 */
	public InsertAmministratore() {
		// ----------------------------------------------------------------------------
		// disegno la finestra
		drawGUI();
		// -----------------------------------------------------------------------------

		// --------listener non implementabili nel controller
		// ----------------------------------------------------------------------------

		// permette di abilitare il chack di nickname fruibile grazie al fatto
		// che non è stato giaassegnato ad un altro utente
		fieldNickName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (!(fieldNickName.getText().equals(""))) // se non è vuoto il
															// campo nickname
				{
					if (dbInterface.verifyNickName(fieldNickName.getText())) // se
																				// il
																				// nickname
																				// è
																				// già
																				// presente
					{
						fieldNickName.setText("");
						fieldNickName.requestFocus();
						desktopWindow.getAllertWindow()
								.aggiungiMessaggio("NickName già in uso da un altro utente!!! \nOptare per una scelta di differente...");
						desktopWindow.getAllertWindow().setVisible(true);
						imgCheckNickName.setVisible(false);
					} else {
						imgCheckNickName.setVisible(true);
						fieldPassword.requestFocus();
					}
				}
			}
		});

		// quando la finestra viene chiusa con "x" in alto a destra si procede a
		// riabilitare la voce di menù Logout del menù FILE
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent arg0) {
				desktopWindow.getItemLogout().setEnabled(true);
				desktopWindow.getAllertWindow().setVisible(false);
			}
		});

		// ---------------fine listener-----------------------------------------

	} // fine costruttore

	// metodi che permettono al CONTROLLER di referenziare il listener che
	// effettua le modifiche al MODEL
	public void AddMyInsertAmministratoreListener(ActionListener listener) {
		btnRegistra.addActionListener(listener);
	}


	public void AddMyInsertAmministratoreListenerKey(KeyListener listener) {
		fieldPassword.addKeyListener(listener);
	}

	// ----------------------------------------------------------------------------------------------------

	public JComboBox getComboProfilo() {
		return comboProfilo;
	}

	public void importaDesktopWindow(DesktopWindow dw) {

		this.desktopWindow = dw;

	}

	public JTextField getFieldNickName() {
		return fieldNickName;
	}

	public JLabel getImgCheckNickName() {
		return imgCheckNickName;
	}

	public JPasswordField getFieldPassword() {
		return fieldPassword;
	}

	private void drawGUI() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setFrameIcon(new ImageIcon(
				InsertAmministratore.class
						.getResource("/icons/vestizione_24.png")));
		// setIcon(true);
		setTitle("Creazione NUOVO Amministratore");
		setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
		setClosable(true);
		setBounds(100, 100, 499, 264);
		getContentPane().setLayout(null);

		lblNickname = new JLabel("NickName");
		lblNickname.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNickname.setBounds(56, 73, 107, 34);
		getContentPane().add(lblNickname);

		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPassword.setBounds(56, 118, 107, 34);
		getContentPane().add(lblPassword);

		fieldNickName = new JTextField();
		fieldNickName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldNickName.setBounds(182, 73, 250, 34);
		getContentPane().add(fieldNickName);
		fieldNickName.setColumns(10);

		label = new JLabel("Profilo");
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setAlignmentX(0.5f);
		label.setBounds(56, 28, 107, 34);
		getContentPane().add(label);

		comboProfilo = new JComboBox(dbInterface.getElencoProfili().toArray());
		comboProfilo.setSelectedIndex(0);
		comboProfilo.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboProfilo.setEnabled(false);
		comboProfilo.setBounds(182, 28, 250, 37);
		getContentPane().add(comboProfilo);
		comboProfilo.setEnabled(false);

		imgCheckNickName = new JLabel("");
		imgCheckNickName.setVisible(false);
		imgCheckNickName.setIcon(new ImageIcon(InsertAmministratore.class
				.getResource("/icons/check_ok_32.png")));
		imgCheckNickName.setBounds(442, 75, 46, 32);
		getContentPane().add(imgCheckNickName);

		btnRegistra = new JButton("Registra");
		btnRegistra.setFont(new Font("Tahoma", Font.ITALIC, 18));
		btnRegistra.setBounds(56, 188, 376, 37);
		getContentPane().add(btnRegistra);

		fieldPassword = new JPasswordField();
		fieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldPassword.setBounds(181, 118, 251, 34);
		getContentPane().add(fieldPassword);
	}

}

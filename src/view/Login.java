package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Login extends JInternalFrame {
	private DesktopWindow desktopWindow;
	private JTextField fieldUsarName;
	private JPasswordField fieldPassword;
	private JLabel imgLock = new JLabel("");
	private JLabel imgUnlock = new JLabel("");
	private JButton btnSubmit;
	private JLabel lblUserName;
	private JLabel lblPassword;

	/**
	 * Create the frame.
	 */
	public Login() {
		// ----------------------------------------------------------------------------
		// disegno la finestra
		drawGUI();
		// -----------------------------------------------------------------------------

		// --------listener non implementabili nel controller
		// ----------------------------------------------------------------------------

		// ---------------fine LISTENER-----------------------------------

	} //fine costruttore


	// metodi che permettono al CONTROLLER di referenziare il listener che
		// effettua le modifiche al MODEL
		public void AddMyLoginListener(ActionListener listener) {
			btnSubmit.addActionListener(listener);
		}

		public void AddMyLoginListenerKey(KeyListener listener) {
			fieldPassword.addKeyListener(listener);
		}

		// ----------------------------------------------------------------------------------------------------
		
	
	// metodi GET per gli oggetti della
	// JInternalWindow---------------------------------------------------
	public JTextField getFieldUsarName() {
		return fieldUsarName;
	}

	public void setFieldUsarName(JTextField fieldUsarName) {
		this.fieldUsarName = fieldUsarName;
	}

	public JPasswordField getFieldPassword() {
		return fieldPassword;
	}

	public void setFieldPassword(JPasswordField fieldPassword) {
		this.fieldPassword = fieldPassword;
	}
	

	public JLabel getImgLock() {
		return imgLock;
	}

	public JLabel getImgUnlock() {
		return imgUnlock;
	}

	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	public void importaDesktopWindow(DesktopWindow dw) {

		this.desktopWindow = dw;

	}

	//metodo per la graficaione della finestra
	private void drawGUI() {
		setFrameIcon(new ImageIcon(
				Login.class.getResource("/icons/chiave_24.png")));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setRootPaneCheckingEnabled(false);
		setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
		setTitle("Login");
		setBounds(100, 100, 713, 253);
		getContentPane().setLayout(null);

		imgLock.setIcon(new ImageIcon(Login.class
				.getResource("/icons/close.png")));
		imgLock.setBounds(20, 14, 200, 200);
		getContentPane().add(imgLock);

		fieldUsarName = new JTextField();
		fieldUsarName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldUsarName.setBounds(384, 29, 272, 40);
		getContentPane().add(fieldUsarName);
		fieldUsarName.setColumns(10);

		fieldPassword = new JPasswordField();

		fieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldPassword.setBounds(384, 80, 272, 40);
		getContentPane().add(fieldPassword);

		btnSubmit = new JButton("Submit");
		btnSubmit.setIcon(new ImageIcon(Login.class
				.getResource("/icons/check_32.png")));
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSubmit.setBounds(442, 154, 162, 40);
		getContentPane().add(btnSubmit);

		lblUserName = new JLabel("User Name");
		lblUserName.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUserName.setBounds(234, 29, 122, 40);
		getContentPane().add(lblUserName);

		lblPassword = new JLabel("Password");
		lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPassword.setBounds(234, 77, 122, 40);
		getContentPane().add(lblPassword);

		imgUnlock = new JLabel("");
		imgUnlock.setVisible(false);
		imgUnlock.setIcon(new ImageIcon(Login.class
				.getResource("/icons/open.png")));
		imgUnlock.setBounds(10, 14, 200, 200);
		getContentPane().add(imgUnlock);

	}
}

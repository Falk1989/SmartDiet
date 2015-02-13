package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class FirstEnter extends JInternalFrame {
	private DesktopWindow desktopWindow;
	private JTextField fieldUsarName;
	private JPasswordField fieldPassword;

	private DbInterface dbInterface = new DbInterface();
	private JButton btnSubmit;

	private JComboBox comboProfilo;

	/**
	 * Create the frame.
	 */
	public FirstEnter() {
		// ----------------------------------------------------------------------------
				// disegno la finestra
				drawGUI();
				// -----------------------------------------------------------------------------

				// --------listener non implementabili nel controller
				// ----------------------------------------------------------------------------

				
			
				
				

	} // fine costruttore
	
	
	// metodi che permettono al CONTROLLER di referenziare il listener che
		// effettua le modifiche al MODEL
	public void AddMyFirstEnterListener(ActionListener listener) {
			btnSubmit.addActionListener(listener);
		}

	public void AddMyFirstEnterListenerKey(KeyListener listener) {
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

	public void importaDesktopWindow(DesktopWindow dw) {

		this.desktopWindow = dw;

	}
	
	public JComboBox getComboProfilo() {
		return comboProfilo;
	}
	
	// disegno della finestra
	
	

	private void drawGUI(){
		setFrameIcon(new ImageIcon(
				FirstEnter.class.getResource("/icons/certificate_24.png")));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		setRootPaneCheckingEnabled(false);

		setBorder(new LineBorder(Color.BLUE));
		setTitle("Registra Amministratori di Sistema");
		setBounds(100, 100, 524, 477);
		getContentPane().setLayout(null);

		fieldUsarName = new JTextField();
		fieldUsarName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldUsarName.setBounds(202, 275, 272, 40);
		getContentPane().add(fieldUsarName);
		fieldUsarName.setColumns(10);

		fieldPassword = new JPasswordField();

		fieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldPassword.setBounds(202, 326, 272, 40);
		getContentPane().add(fieldPassword);

		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubmit.setIcon(new ImageIcon(FirstEnter.class
				.getResource("/icons/check_32.png")));
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSubmit.setBounds(52, 398, 423, 40);
		getContentPane().add(btnSubmit);

		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUserName.setBounds(52, 275, 122, 40);
		getContentPane().add(lblUserName);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPassword.setBounds(52, 323, 122, 40);
		getContentPane().add(lblPassword);

		JLabel imgAmministratore = new JLabel("");
		imgAmministratore.setIcon(new ImageIcon(FirstEnter.class
				.getResource("/icons/Amministratore_sistema.png")));
		imgAmministratore.setBounds(105, 22, 301, 203);
		getContentPane().add(imgAmministratore);

		JLabel lblProfilo = new JLabel("Profilo");
		lblProfilo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProfilo.setAlignmentX(0.5f);
		lblProfilo.setBounds(52, 224, 122, 40);
		getContentPane().add(lblProfilo);

		comboProfilo = new JComboBox(dbInterface.getElencoProfili().toArray());
		comboProfilo.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboProfilo.setBounds(202, 224, 272, 40);
		getContentPane().add(comboProfilo);
		comboProfilo.setSelectedIndex(0);
		comboProfilo.setEnabled(false);
	}
}

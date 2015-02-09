package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import dbAccess.DbInterface;

public class UpdateAmministratore extends JInternalFrame {

	private JLabel lblNickname;
	private JLabel lblNewPassword;
	private JLabel label;
	private JComboBox comboBox;
	private JComboBox combNickName;
	private DesktopWindow desktopWindow;
	private DbInterface dbInterface = new DbInterface();
	private JButton btnModifica;
	private JPasswordField fieldNewPassword;
	private JLabel lblNewNickName;
	private JPanel panelUpdate;
	private JTextField fieldNewNickName;
	private JLabel imgCheckNickName;
	/**
	 * Create the frame.
	 */
	public UpdateAmministratore() {
		// ----------------------------------------------------------------------------
		// disegno la finestra
		drawGUI();
		// -----------------------------------------------------------------------------

		// --------listener non implementabili nel controller
		// ----------------------------------------------------------------------------

		// pilota la compilazione automatica del campo nickname sulla base di
		// quello che viene selezionato nel combo
		combNickName.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				fieldNewNickName.setEnabled(true);
				fieldNewPassword.setEnabled(true);
				fieldNewNickName.setText((String) combNickName
						.getSelectedItem());
			}
		});
		
		// permette di abilitare il chack di nickname fruibile grazie al fatto
				// che non è stato gia assegnato ad un altro utente
				fieldNewNickName.addFocusListener(new FocusAdapter() {
					@Override
					public void focusLost(FocusEvent arg0) {
						if (!(fieldNewNickName.getText().equals(""))) // se non è vuoto il
																	// campo nickname
						{
							if (dbInterface.verifyNickName(fieldNewNickName.getText())) // se
																						// il
																						// nickname
																						// è
																						// già
																						// presente
							{
								fieldNewNickName.setText("");
								fieldNewNickName.requestFocus();
								desktopWindow.getAllertWindow()
										.aggiungiMessaggio("NickName già in uso da un altro utente!!! \nOptare per una scelta di differente...");
								desktopWindow.getAllertWindow().setVisible(true);
								imgCheckNickName.setVisible(false);
							} else {
								imgCheckNickName.setVisible(true);
								fieldNewPassword.requestFocus();
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

		// ---------------fine LISTENER-----------------------------------

	} // fine del costruttore

	// metodi che permettono al CONTROLLER di referenziare il listener che
	// effettua le modifiche al MODEL
	public void AddMyUpdateAmministratoreListener(ActionListener listener) {
		btnModifica.addActionListener(listener);
	}

	public void AddMyUpdateAmministratoreListenerKey(KeyListener listener) {
		fieldNewPassword.addKeyListener(listener);
	}

	// ----------------------------------------------------------------------------------------------------

	// metodi GET per gli oggetti della
	// JInternalWindow---------------------------------------------------
	public JComboBox getCombNickName() {
		return combNickName;
	}

	public void importaDesktopWindow(DesktopWindow dw) {

		this.desktopWindow = dw;

	}

	public JPasswordField getFieldNewPassword() {
		return fieldNewPassword;
	}

	public JTextField getFieldNewNickName() {
		return fieldNewNickName;
	}
	
	public JLabel getImgCheckNickName() {
		return imgCheckNickName;
	}


	// ----------------------------------------------------------------------------------------------------

	// metodo per disegnare la form
	// ----------------------------------------------------------------------
	private void drawGUI() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setFrameIcon(new ImageIcon(
				UpdateAmministratore.class
						.getResource("/icons/vestizione_24.png")));
		setTitle("Modifica Amministratore");
		setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
		setClosable(true);
		setBounds(100, 100, 566, 308);
		getContentPane().setLayout(null);

		lblNickname = new JLabel("NickName");
		lblNickname.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNickname.setBounds(66, 69, 107, 34);
		getContentPane().add(lblNickname);

		label = new JLabel("Profilo");
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setAlignmentX(0.5f);
		label.setBounds(66, 24, 107, 34);
		getContentPane().add(label);

		comboBox = new JComboBox(dbInterface.getElencoProfili().toArray());
		comboBox.setSelectedIndex(0);
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboBox.setEnabled(false);
		comboBox.setBounds(227, 24, 251, 37);
		comboBox.setEnabled(false);
		getContentPane().add(comboBox);

		btnModifica = new JButton("Modifica");
		btnModifica.setIcon(new ImageIcon(UpdateAmministratore.class
				.getResource("/icons/ingranaggi_modifica_32.png")));
		btnModifica.setFont(new Font("Tahoma", Font.ITALIC, 18));
		btnModifica.setBounds(145, 228, 291, 37);
		getContentPane().add(btnModifica);

		combNickName = new JComboBox(
				(dbInterface.getElencoNickName((String) comboBox
						.getSelectedItem())).toArray());
		combNickName.setAutoscrolls(true);
		combNickName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		combNickName.setBounds(227, 69, 251, 35);
		getContentPane().add(combNickName);

		panelUpdate = new JPanel();
		panelUpdate.setForeground(Color.BLACK);
		panelUpdate.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Nuove Credenziali",
				TitledBorder.RIGHT, TitledBorder.TOP, null, Color.BLUE));
		panelUpdate.setBounds(10, 119, 544, 98);
		getContentPane().add(panelUpdate);
		panelUpdate.setLayout(null);

		lblNewPassword = new JLabel("New Password");
		lblNewPassword.setBounds(27, 57, 133, 25);
		panelUpdate.add(lblNewPassword);
		lblNewPassword.setFont(new Font("Tahoma", Font.BOLD, 18));

		fieldNewPassword = new JPasswordField();
		fieldNewPassword.setBounds(197, 57, 281, 28);
		panelUpdate.add(fieldNewPassword);
		fieldNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblNewNickName = new JLabel("New NickName");
		lblNewNickName.setForeground(Color.RED);
		lblNewNickName.setBounds(27, 18, 139, 35);
		panelUpdate.add(lblNewNickName);
		lblNewNickName.setFont(new Font("Tahoma", Font.BOLD, 18));

		fieldNewNickName = new JTextField();
		fieldNewNickName.setText((String) combNickName.getSelectedItem());
		fieldNewNickName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldNewNickName.setBounds(197, 18, 281, 28);
		panelUpdate.add(fieldNewNickName);
		fieldNewNickName.setColumns(10);
		
		imgCheckNickName = new JLabel("");
		imgCheckNickName.setBounds(488, 18, 36, 32);
		panelUpdate.add(imgCheckNickName);
		imgCheckNickName.setVisible(false);
		imgCheckNickName.setIcon(new ImageIcon(InsertAmministratore.class
				.getResource("/icons/check_ok_32.png")));

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setFrameIcon(new ImageIcon(
				UpdateAmministratore.class
						.getResource("/icons/vestizione_24.png")));
		setTitle("Modifica Amministratore");
		setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
		setClosable(true);
		setBounds(100, 100, 566, 308);
		getContentPane().setLayout(null);

		lblNickname = new JLabel("NickName");
		lblNickname.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNickname.setBounds(66, 69, 107, 34);
		getContentPane().add(lblNickname);

		label = new JLabel("Profilo");
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setAlignmentX(0.5f);
		label.setBounds(66, 24, 107, 34);
		getContentPane().add(label);

		comboBox = new JComboBox(dbInterface.getElencoProfili().toArray());
		comboBox.setSelectedIndex(0);
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboBox.setEnabled(false);
		comboBox.setBounds(227, 24, 251, 37);
		comboBox.setEnabled(false);
		getContentPane().add(comboBox);

	}

	
}

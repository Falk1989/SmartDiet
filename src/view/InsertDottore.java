package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import dbAccess.DbInterface;
import enumerativi.Giorni;

public class InsertDottore extends JInternalFrame {
	private JLabel lblNickname;
	private JLabel lblPassword;
	private JTextField fieldNickName;
	private JLabel label;
	private JComboBox comboProfilo;
	private DesktopWindow desktopWindow;
	private DbInterface dbInterface = new DbInterface();
	private JLabel imgCheckNickName;
	private JButton btnRegistra;
	private AllertWindow allertWindow = new AllertWindow();
	private JPasswordField fieldPassword;
	private JLabel lblCognome;
	private JTextField fieldCognome;
	private JLabel lblNome;
	private JTextField fieldNome;
	private JPanel panelCredenziali;
	private JPanel panel;
	private JLabel lblDataDiNascita;
	private JLabel lblCodiceFiscale;
	private JTextField fieldCodiceFiscale;
	private JLabel lblCellulare;
	private JTextField fieldCellulare;
	private JLabel lblMail;
	private JTextField fieldMail;
	private JLabel lblFax;
	private JTextField fieldFax;
	private JComboBox comboGiorno;
	private JLabel label_1;
	private JComboBox comboMese;
	private JLabel label_2;
	private JComboBox comboAnno;

	/**
	 * Create the frame.
	 */
	public InsertDottore() {
		// ----------------------------------------------------------------------------
		// disegno la finestra
		drawGUI();
		// -----------------------------------------------------------------------------

		// --------listener non implementabili nel controller
		// ----------------------------------------------------------------------------

		// conrolla l'unicit� del nickname scelto nell'inserimento
		fieldNickName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (!(fieldNickName.getText().equals(""))) // se non � vuoto il
															// campo nickname
				{
					if (dbInterface.verifyNickName(fieldNickName.getText())) // se
																				// il
																				// nickname
																				// �
																				// gi�
																				// presente
					{
						fieldNickName.setText("");
						fieldNickName.requestFocus();
						allertWindow
								.aggiungiMessaggio("NickName gi� in uso da un altro utente!!! \nOptare per una scelta di differente...");
						allertWindow.setVisible(true);
						imgCheckNickName.setVisible(false);
					} else {
						imgCheckNickName.setVisible(true);
						fieldPassword.requestFocus();
					}
				}
			}
		});

		// quando la finestra viene chiusa con "x" in alto a destra si procede a
		// riabilitare la voce di men� Logout del men� FILE
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent arg0) {
				desktopWindow.getItemLogout().setEnabled(true);
				desktopWindow.getAllertWindow().setVisible(false);
			}
		});
		
	} //fine costruttore
		
		// ---------------fine LISTENER-----------------------------------

//		btnRegistra.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseReleased(MouseEvent arg0) {
//				boolean esitoScrittura;
//				if (!(fieldNickName.getText().equals(""))
//						&& (!(new String(fieldPassword.getPassword())
//								.equals("")))
//						&& (!(new String(fieldCognome.getText()).equals("")))
//						&& (!(new String(fieldNome.getText()).equals("")))
//						&& (!(new String(fieldCodiceFiscale.getText())
//								.equals("")))) // se entrambi
//				// sono stati
//				// caricati con
//				// qualcosa
//				// diverso da
//				// vuoto
//				{
//
//					esitoScrittura = dbInterface.registraDottore(
//							(String) comboProfilo.getSelectedItem(),
//							fieldNickName.getText(),
//							(new String(fieldPassword.getPassword())),
//							fieldCognome.getText(), fieldNome.getText(),
//							Giorni.castDate(new GregorianCalendar(Integer
//									.parseInt((String) comboAnno
//											.getSelectedItem()),
//									(((Giorni) comboMese.getSelectedItem())
//											.getNumero()), Integer
//											.parseInt((String) comboGiorno
//													.getSelectedItem()))),
//							fieldCodiceFiscale.getText(), fieldCellulare
//									.getText(), fieldMail.getText(), fieldFax
//									.getText());
//					if (esitoScrittura == true)
//						hide();
//					else {
//						fieldNickName.setText("");
//						fieldPassword.setText("");
//						fieldCognome.setText("");
//						fieldNome.setText("");
//						fieldCodiceFiscale.setText("");
//						fieldCellulare.setText("");
//						fieldMail.setText("");
//						fieldFax.setText("");
//
//						fieldNickName.requestFocus();
//						allertWindow
//								.aggiungiMessaggio("Caricamento credenziali NON andato a buon fine!!! /nRipetere l'azione...");
//						allertWindow.setVisible(true);
//					}
//				} else {
//					fieldNickName.setBackground(Color.GREEN);
//					fieldPassword.setBackground(Color.GREEN);
//					fieldCognome.setBackground(Color.GREEN);
//					fieldNome.setBackground(Color.GREEN);
//					fieldCodiceFiscale.setBackground(Color.GREEN);
//					Object[] options = { "OK" };
//					int answer = JOptionPane
//							.showOptionDialog(
//									null,
//									"Uno dei campi OBBLIGATORI evvidenziati in verde risulta essere VUOTO",
//									"Attenzione!!!", JOptionPane.PLAIN_MESSAGE,
//									JOptionPane.INFORMATION_MESSAGE, null,
//									options, options[0]);
//
//				}
//			}
//		});
//
//		btnRegistra.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyReleased(KeyEvent arg0) {
//				boolean esitoScrittura;
//				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
//					if (!(fieldNickName.getText().equals(""))
//							&& (!(new String(fieldPassword.getPassword())
//									.equals("")))
//							&& (!(new String(fieldCognome.getText()).equals("")))
//							&& (!(new String(fieldNome.getText()).equals("")))
//							&& (!(new String(fieldCodiceFiscale.getText())
//									.equals("")))) // se entrambi
//					// sono stati
//					// caricati con
//					// qualcosa
//					// diverso da
//					// vuoto
//					{
//
//						esitoScrittura = dbInterface.registraDottore(
//								(String) comboProfilo.getSelectedItem(),
//								fieldNickName.getText(), (new String(
//										fieldPassword.getPassword())),
//								fieldCognome.getText(), fieldNome.getText(),
//								Giorni.castDate(new GregorianCalendar(Integer
//										.parseInt((String) comboAnno
//												.getSelectedItem()),
//										(((Giorni) comboMese.getSelectedItem())
//												.getNumero()), Integer
//												.parseInt((String) comboGiorno
//														.getSelectedItem()))),
//								fieldCodiceFiscale.getText(), fieldCellulare
//										.getText(), fieldMail.getText(),
//								fieldFax.getText());
//						if (esitoScrittura == true)
//							hide();
//						else {
//							fieldNickName.setText("");
//							fieldPassword.setText("");
//							fieldCognome.setText("");
//							fieldNome.setText("");
//							fieldCodiceFiscale.setText("");
//							fieldCellulare.setText("");
//							fieldMail.setText("");
//							fieldFax.setText("");
//
//							fieldNickName.requestFocus();
//							allertWindow
//									.aggiungiMessaggio("Caricamento credenziali NON andato a buon fine!!! /nRipetere l'azione...");
//							allertWindow.setVisible(true);
//						}
//					} else {
//						fieldNickName.setBackground(Color.GREEN);
//						fieldPassword.setBackground(Color.GREEN);
//						fieldCognome.setBackground(Color.GREEN);
//						fieldNome.setBackground(Color.GREEN);
//						fieldCodiceFiscale.setBackground(Color.GREEN);
//						Object[] options = { "OK" };
//						int answer = JOptionPane
//								.showOptionDialog(
//										null,
//										"Uno dei campi OBBLIGATORI evvidenziati in verde risulta essere VUOTO",
//										"Attenzione!!!",
//										JOptionPane.PLAIN_MESSAGE,
//										JOptionPane.INFORMATION_MESSAGE, null,
//										options, options[0]);
//
//					}
//
//				}
//			}
//		});

	
	// metodi che permettono al CONTROLLER di referenziare il listener che
		// effettua le modifiche al MODEL
		public void AddMyInsertDottoreListener(ActionListener listener) {
			btnRegistra.addActionListener(listener);
		}

		

		public void AddMyInsertDottoreListenerKey(KeyListener listener) {
			btnRegistra.addKeyListener(listener);
		}

		// ----------------------------------------------------------------------------------------------------

		// metodi GET per gli oggetti della
		// JInternalWindow---------------------------------------------------
	public void importaDesktopWindow(DesktopWindow dw) {

		this.desktopWindow = dw;

	}

	public JComboBox getComboProfilo() {
		return comboProfilo;
	}
	public JTextField getFieldNickName() {
		return fieldNickName;
	}

	public JPasswordField getFieldPassword() {
		return fieldPassword;
	}

	public JTextField getFieldCognome() {
		return fieldCognome;
	}

	public JTextField getFieldNome() {
		return fieldNome;
	}

	public JTextField getFieldCodiceFiscale() {
		return fieldCodiceFiscale;
	}

	public JTextField getFieldCellulare() {
		return fieldCellulare;
	}

	public JTextField getFieldMail() {
		return fieldMail;
	}

	public JTextField getFieldFax() {
		return fieldFax;
	}

	public JLabel getImgCheckNickName() {
		return imgCheckNickName;
	}

	public JComboBox getComboGiorno() {
		return comboGiorno;
	}

	public JComboBox getComboMese() {
		return comboMese;
	}

	public JComboBox getComboAnno() {
		return comboAnno;
	}

	public void caricaComboMesi() {
		Giorni arrayGiorni[] = Giorni.values();
		for (Giorni g : arrayGiorni)
			comboMese.addItem(g);
	}

	
	//definizione de''interfaccia grafica
	private void drawGUI() {

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setFrameIcon(new ImageIcon(
				InsertDottore.class.getResource("/icons/fornitore_add_24.png")));
		setTitle("Creazione NUOVO Biologo Nutrizionista");
		setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
		setClosable(true);
		setBounds(100, 100, 499, 536);
		getContentPane().setLayout(null);

		btnRegistra = new JButton("Registra");
		btnRegistra.setFont(new Font("Tahoma", Font.ITALIC, 18));
		btnRegistra.setBounds(56, 460, 376, 37);
		getContentPane().add(btnRegistra);

		panelCredenziali = new JPanel();
		panelCredenziali.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Credenziali",
				TitledBorder.RIGHT, TitledBorder.TOP, null,
				new Color(0, 0, 255)));
		panelCredenziali.setBounds(10, 0, 477, 155);
		getContentPane().add(panelCredenziali);
		panelCredenziali.setLayout(null);

		label = new JLabel("Profilo");
		label.setBounds(20, 17, 107, 34);
		panelCredenziali.add(label);
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setAlignmentX(0.5f);

		comboProfilo = new JComboBox(dbInterface.getElencoProfili().toArray());
		comboProfilo.setBounds(182, 16, 250, 37);
		panelCredenziali.add(comboProfilo);
		comboProfilo.setSelectedIndex(1);
		comboProfilo.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboProfilo.setEnabled(false);
		comboProfilo.setEnabled(false);

		lblNickname = new JLabel("NickName");
		lblNickname.setForeground(Color.ORANGE);
		lblNickname.setBounds(20, 64, 107, 34);
		panelCredenziali.add(lblNickname);
		lblNickname.setFont(new Font("Tahoma", Font.BOLD, 18));

		fieldNickName = new JTextField();
		fieldNickName.setBounds(182, 64, 250, 34);
		panelCredenziali.add(fieldNickName);
		fieldNickName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldNickName.setColumns(10);

		lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.ORANGE);
		lblPassword.setBounds(20, 109, 107, 34);
		panelCredenziali.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));

		fieldPassword = new JPasswordField();
		fieldPassword.setBounds(181, 109, 251, 34);
		panelCredenziali.add(fieldPassword);
		fieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));

		imgCheckNickName = new JLabel("");
		imgCheckNickName.setBounds(435, 66, 32, 32);
		panelCredenziali.add(imgCheckNickName);
		imgCheckNickName.setVisible(false);
		imgCheckNickName.setIcon(new ImageIcon(InsertDottore.class
				.getResource("/icons/check_ok_32.png")));

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Anagrafica",
				TitledBorder.RIGHT, TitledBorder.TOP, null, Color.BLUE));
		panel.setBounds(10, 154, 477, 303);
		getContentPane().add(panel);
		panel.setLayout(null);

		lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(20, 21, 107, 34);
		panel.add(lblCognome);
		lblCognome.setFont(new Font("Tahoma", Font.BOLD, 18));

		fieldCognome = new JTextField();
		fieldCognome.setBounds(182, 21, 272, 34);
		panel.add(fieldCognome);
		fieldCognome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldCognome.setColumns(10);

		lblNome = new JLabel("Nome");
		lblNome.setBounds(20, 61, 107, 34);
		panel.add(lblNome);
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 18));

		fieldNome = new JTextField();
		fieldNome.setBounds(182, 61, 272, 34);
		panel.add(fieldNome);
		fieldNome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldNome.setColumns(10);

		lblDataDiNascita = new JLabel("Data di Nascita");
		lblDataDiNascita.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDataDiNascita.setBounds(20, 101, 152, 34);
		panel.add(lblDataDiNascita);

		lblCodiceFiscale = new JLabel("Codice Fiscale");
		lblCodiceFiscale.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCodiceFiscale.setBounds(20, 141, 152, 34);
		panel.add(lblCodiceFiscale);

		fieldCodiceFiscale = new JTextField();
		fieldCodiceFiscale.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldCodiceFiscale.setColumns(10);
		fieldCodiceFiscale.setBounds(182, 141, 272, 34);
		panel.add(fieldCodiceFiscale);

		lblCellulare = new JLabel("Cellulare");
		lblCellulare.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCellulare.setBounds(20, 181, 107, 34);
		panel.add(lblCellulare);

		fieldCellulare = new JTextField();
		fieldCellulare.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldCellulare.setColumns(10);
		fieldCellulare.setBounds(182, 181, 272, 34);
		panel.add(fieldCellulare);

		lblMail = new JLabel("Mail");
		lblMail.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMail.setBounds(20, 221, 107, 34);
		panel.add(lblMail);

		fieldMail = new JTextField();
		fieldMail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldMail.setColumns(10);
		fieldMail.setBounds(182, 221, 272, 34);
		panel.add(fieldMail);

		lblFax = new JLabel("Fax");
		lblFax.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFax.setBounds(20, 261, 107, 34);
		panel.add(lblFax);

		fieldFax = new JTextField();
		fieldFax.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldFax.setColumns(10);
		fieldFax.setBounds(182, 261, 272, 34);
		panel.add(fieldFax);

		comboGiorno = new JComboBox();
		comboGiorno.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		comboGiorno.setBounds(182, 106, 55, 29);
		comboGiorno.setModel(new DefaultComboBoxModel(new String[] { "1", "2",
				"3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
				"14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
				"24", "25", "26", "27", "28", "29", "30", "31" }));
		panel.add(comboGiorno);

		label_1 = new JLabel("/");
		label_1.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 18));
		label_1.setBounds(238, 106, 13, 27);
		panel.add(label_1);

		comboMese = new JComboBox();
		comboMese.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		comboMese.setBounds(250, 107, 112, 28);
		caricaComboMesi();
		panel.add(comboMese);

		label_2 = new JLabel("/");
		label_2.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 18));
		label_2.setBounds(364, 105, 13, 27);
		panel.add(label_2);

		comboAnno = new JComboBox();
		comboAnno.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		comboAnno.setBounds(379, 106, 75, 28);
		comboAnno.setModel(new DefaultComboBoxModel(new String[] { "1950",
				"1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958",
				"1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966",
				"1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974",
				"1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982",
				"1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990",
				"1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998",
				"1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006",
				"2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014",
				"2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022",
				"2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030",
				"2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038",
				"2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046",
				"2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054",
				"2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062",
				"2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070",
				"2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078",
				"2079", "2080" }));
		panel.add(comboAnno);
	}

}

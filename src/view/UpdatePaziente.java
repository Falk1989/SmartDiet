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
import java.util.Random;

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

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class UpdatePaziente extends JInternalFrame {
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
	private JLabel lblCognome;
	private JTextField fieldCognome;
	private JLabel lblNome;
	private JTextField fieldNome;
	private JPanel panelCredenziali;
	private JPanel panel;
	private JLabel lblDataDiNascita;
	private JLabel lblCodiceFiscale;
	private JTextField fieldCodiceFiscale;
	private JLabel lblMail;
	private JTextField fieldMail;
	private JComboBox comboGiorno;
	private JLabel label_1;
	private JComboBox comboMese;
	private JLabel label_2;
	private JComboBox comboAnno;
	private JLabel lblSesso;
	private JRadioButton rbtMaschio;
	private JRadioButton rbtFemmina;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblCodiceDottore;
	private JTextField fieldCodiceDottore;

	public JRadioButton getRbtMaschio() {
		return rbtMaschio;
	}

	public JRadioButton getRbtFemmina() {
		return rbtFemmina;
	}

	/**
	 * Create the frame.
	 */
	public UpdatePaziente() {
		// ----------------------------------------------------------------------------
		// disegno la finestra
		drawGUI();
		// -----------------------------------------------------------------------------

		// --------listener non implementabili nel controller
		// ----------------------------------------------------------------------------

		// conrolla l'unicità del nickname scelto nell'inserimento
		fieldMail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
			
					if (!(fieldMail.getText().equals(""))) // se non è vuoto il
															// campo nickname
					{
						if (dbInterface.verifyNickName(fieldMail.getText())) // se
																				// il
																				// nickname
																				// è
																				// già
																				// presente
						{
							fieldMail.setText("");
							fieldNickName.setText("");
							fieldPassword.setText("");
							fieldMail.requestFocus();
							desktopWindow
									.getAllertWindow()
									.aggiungiMessaggio(
											"NickName già in uso da un altro utente!!! \nOptare per una scelta di differente...");
							desktopWindow.getAllertWindow().setVisible(true);
							imgCheckNickName.setVisible(false);
							fieldCodiceFiscale.requestFocus();
						} else {
							imgCheckNickName.setVisible(true);
							fieldNickName.setText(fieldMail.getText());
							fieldPassword
									.setText(generazioneAutomaticaPassword());
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

		btnRegistra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
					boolean esitoScrittura;
					if (!(fieldCodiceFiscale.getText().equals(""))
							&& (!(fieldNome.getText()).equals(""))
							&& (!(fieldCognome.getText()).equals(""))
							&& (!(fieldNome.getText()).equals(""))
							&& (!(fieldMail.getText()).equals(""))
							) // se tutti
																	// i
																	// campi
																	// obbligatori
																	// sono
																	// stati
																	// popolati
					{

						esitoScrittura = dbInterface.registraPaziente(
								desktopWindow.getActiveUser().getLevel(),
								desktopWindow.getActiveUser().getNickName(),
								fieldCodiceFiscale.getText(), fieldNome
										.getText(), fieldCognome.getText(),
								Giorni.castDate(new GregorianCalendar(Integer
										.parseInt((String) comboAnno
												.getSelectedItem()),
										(((Giorni) comboMese.getSelectedItem())
												.getNumero()), Integer
												.parseInt((String) comboGiorno
														.getSelectedItem()))),
								getSesso(), fieldMail.getText(),
								Integer.parseInt(fieldCodiceDottore.getText()),
								(String) comboProfilo.getSelectedItem(),
								new String(fieldPassword.getPassword()));

						if (esitoScrittura == true)
							hide();
						else {
							fieldCodiceFiscale.requestFocus();
							desktopWindow
									.getAllertWindow()
									.aggiungiMessaggio(
											"Caricamento credenziali NON andato a buon fine!!! /nRipetere l'azione...");
							desktopWindow.getAllertWindow().setVisible(true);
						}
					} else {
				
						fieldCodiceFiscale.setBackground(Color.GREEN);
						fieldNome.setBackground(Color.GREEN);
						fieldCognome.setBackground(Color.GREEN);
						fieldMail.setBackground(Color.GREEN);
						rbtMaschio.setBackground(Color.GREEN);
						rbtFemmina.setBackground(Color.GREEN);
						Object[] options = { "OK" };
						JOptionPane
								.showOptionDialog(
										null,
										"Uno dei campi OBBLIGATORI evvidenziati in verde risulta essere VUOTO",
										"Attenzione!!!",
										JOptionPane.PLAIN_MESSAGE,
										JOptionPane.INFORMATION_MESSAGE, null,
										options, options[0]);
						fieldCodiceFiscale.requestFocus();

					}
				}
			
		});

	} // fine costruttore

	// ---------------fine LISTENER-----------------------------------

	public JTextField getFieldCodiceDottore() {
		return fieldCodiceDottore;
	}

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
		return fieldMail;
	}

	public JTextField getFieldMail() {
		return fieldMail;
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

	// definizione de''interfaccia grafica
	private void drawGUI() {

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setFrameIcon(new ImageIcon(UpdatePaziente.class.getResource("/icons/profilo_upd_20.png")));
		setTitle("Modifica Paziente");
		setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
		setClosable(true);
		setBounds(100, 100, 523, 566);
		getContentPane().setLayout(null);

		btnRegistra = new JButton("Registra");
		btnRegistra.setFont(new Font("Tahoma", Font.ITALIC, 18));
		btnRegistra.setBounds(63, 490, 376, 37);
		getContentPane().add(btnRegistra);

		panelCredenziali = new JPanel();
		panelCredenziali.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Credenziali",
				TitledBorder.RIGHT, TitledBorder.TOP, null,
				new Color(0, 0, 255)));
		panelCredenziali.setBounds(10, 332, 502, 155);
		getContentPane().add(panelCredenziali);
		panelCredenziali.setLayout(null);

		label = new JLabel("Profilo");
		label.setBounds(20, 17, 107, 34);
		panelCredenziali.add(label);
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setAlignmentX(0.5f);

		comboProfilo = new JComboBox(dbInterface.getElencoProfili().toArray());
		comboProfilo.setEnabled(false);
		comboProfilo.setBounds(182, 16, 270, 37);
		panelCredenziali.add(comboProfilo);
		comboProfilo.setSelectedIndex(2);
		comboProfilo.setFont(new Font("Tahoma", Font.BOLD, 18));

		lblNickname = new JLabel("NickName");
		lblNickname.setForeground(Color.ORANGE);
		lblNickname.setBounds(20, 64, 107, 34);
		panelCredenziali.add(lblNickname);
		lblNickname.setFont(new Font("Tahoma", Font.BOLD, 18));

		fieldNickName = new JTextField();
		fieldNickName.setEditable(false);
		fieldNickName.setBounds(182, 64, 270, 34);
		panelCredenziali.add(fieldNickName);
		fieldNickName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldNickName.setColumns(10);

		lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.ORANGE);
		lblPassword.setBounds(20, 109, 107, 34);
		panelCredenziali.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));

		fieldPassword = new JPasswordField();
		fieldPassword.setEditable(false);
		fieldPassword.setBounds(181, 109, 271, 34);
		panelCredenziali.add(fieldPassword);
		fieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Anagrafica",
				TitledBorder.RIGHT, TitledBorder.TOP, null, Color.BLUE));
		panel.setBounds(10, 11, 502, 310);
		getContentPane().add(panel);
		panel.setLayout(null);

		lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(20, 103, 107, 34);
		panel.add(lblCognome);
		lblCognome.setFont(new Font("Tahoma", Font.BOLD, 18));

		fieldCognome = new JTextField();
		fieldCognome.setBounds(182, 103, 272, 34);
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
		lblDataDiNascita.setBounds(20, 141, 152, 34);
		panel.add(lblDataDiNascita);

		lblCodiceFiscale = new JLabel("Codice Fiscale");
		lblCodiceFiscale.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCodiceFiscale.setBounds(20, 21, 152, 34);
		panel.add(lblCodiceFiscale);

		fieldCodiceFiscale = new JTextField();
		fieldCodiceFiscale.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldCodiceFiscale.setColumns(10);
		fieldCodiceFiscale.setBounds(182, 21, 272, 34);
		panel.add(fieldCodiceFiscale);

		lblMail = new JLabel("Mail");
		lblMail.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMail.setBounds(20, 216, 107, 34);
		panel.add(lblMail);

		fieldMail = new JTextField();
		fieldMail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldMail.setColumns(10);
		fieldMail.setBounds(182, 216, 272, 34);
		panel.add(fieldMail);

		comboGiorno = new JComboBox();
		comboGiorno.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		comboGiorno.setBounds(182, 145, 55, 29);
		comboGiorno.setModel(new DefaultComboBoxModel(new String[] { "1", "2",
				"3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
				"14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
				"24", "25", "26", "27", "28", "29", "30", "31" }));
		panel.add(comboGiorno);

		label_1 = new JLabel("/");
		label_1.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 18));
		label_1.setBounds(240, 145, 13, 27);
		panel.add(label_1);

		comboMese = new JComboBox();
		comboMese.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		comboMese.setBounds(252, 145, 112, 28);
		caricaComboMesi();
		panel.add(comboMese);

		label_2 = new JLabel("/");
		label_2.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 18));
		label_2.setBounds(366, 145, 13, 27);
		panel.add(label_2);

		comboAnno = new JComboBox();
		comboAnno.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		comboAnno.setBounds(379, 145, 75, 28);
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

		lblSesso = new JLabel("Sesso");
		lblSesso.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSesso.setBounds(20, 180, 107, 34);
		panel.add(lblSesso);

		rbtMaschio = new JRadioButton("M");
		buttonGroup.add(rbtMaschio);
		rbtMaschio.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		rbtMaschio.setBounds(262, 186, 48, 23);
		panel.add(rbtMaschio);

		rbtFemmina = new JRadioButton("F");
		buttonGroup.add(rbtFemmina);
		rbtFemmina.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		rbtFemmina.setBounds(331, 186, 48, 23);
		panel.add(rbtFemmina);

		imgCheckNickName = new JLabel("");
		imgCheckNickName.setBounds(460, 216, 32, 32);
		panel.add(imgCheckNickName);
		imgCheckNickName.setVisible(false);
		imgCheckNickName.setIcon(new ImageIcon(UpdatePaziente.class
				.getResource("/icons/check_ok_32.png")));

		lblCodiceDottore = new JLabel("Cod. Dottore");
		lblCodiceDottore.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCodiceDottore.setBounds(20, 261, 136, 34);
		panel.add(lblCodiceDottore);

		fieldCodiceDottore = new JTextField();
		fieldCodiceDottore.setEditable(false);
		fieldCodiceDottore.setHorizontalAlignment(SwingConstants.CENTER);
		fieldCodiceDottore.setFont(new Font("Tahoma", Font.BOLD, 20));
		fieldCodiceDottore.setColumns(10);
		fieldCodiceDottore.setBounds(297, 261, 41, 34);
		panel.add(fieldCodiceDottore);
	}

	private String generazioneAutomaticaPassword() {
		final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final int LENGHT = 8;
		Random rnd = new Random(System.currentTimeMillis());

		StringBuilder sb = new StringBuilder(LENGHT);
		for (int i = 0; i < LENGHT; i++) {
			sb.append(ALPHABET.charAt(rnd.nextInt(ALPHABET.length())));
		}

		return sb.toString();
	}

	private int getSesso() {
		int result = -1;
		if (rbtMaschio.isSelected() == true)
			result = 1;
		else
			result = 0;

		return result;
	}

}

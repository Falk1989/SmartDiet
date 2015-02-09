package view;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import dbAccess.DbInterface;
import enumerativi.Giorni;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JScrollBar;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Stack;

public class PrimaVisita extends JInternalFrame {
	private DbInterface d = new DbInterface();
	private DesktopWindow desktopWindow;
	private JLabel imgUnlock = new JLabel("");
	private DbInterface dbInterface;
	private AllertWindow jd;
	private JTextField nameText;
	private JTextField cognomeText;
	private JTextField et‡Text;
	private JTextField attivit‡Text;
	private JTextField idratazioneText;
	private JTextField alcoliciText;
	private JTextField bevandeText;
	private JTextField pesoMinText;
	private JTextField pesoMaxText;
	private JTextField dopoCenaText;
	private JTextField cenaText;
	private JTextField spuntino2Text;
	private JTextField pranzoText;
	private JTextField spuntino1Text;
	private JTextField colazioneText;
	private JTextField condimentiText;
	private JTextField dolciText;
	private JTextField pizzaText;
	private JTextField fruttaText;
	private JTextField carneText;
	private JTextField pesceText;
	private JTextField formaggioText;
	private JTextField affettatiText;
	private JTextField uovaText;
	private JTextField tonnoText;
	private JTextField patateText;
	private JTextField verdureText;
	private JRadioButton alta;
	private JRadioButton bassa;
	private JRadioButton media;
	private JTextField CFText;
	private JRadioButton sexM;
	private JRadioButton sexF;
	private JTextField pesoText;
	private JTextField altezzaText;
	private JTextField circVitaText;
	private JTextField circFianchiText;
	private JTextField mestruazioniText;
	private JTextArea conclusioniText;
	private JTextArea noteAttivitaText;
	private JTextArea noteDietaArea;
	private JTextArea piaceText;
	private JTextArea nonPiaceText;
	private JComboBox comboMese;
	private JComboBox comboGiorno;
	private JComboBox comboAnno;
	private JTextField dataVisitaText;
	private JButton btnConfermaESalva;

	/**
	 * Create the frame.
	 */
	public PrimaVisita() {
		setClosable(true);
		setFrameIcon(new ImageIcon(
				PrimaVisita.class.getResource("/icons/certificate_24.png")));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setRootPaneCheckingEnabled(false);
		setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
		setTitle("Inserimento NUOVO Paziente");
		setBounds(100, 100, 900, 601);
		getContentPane().setLayout(null);

		// Definisco il TabbedPane
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(39, 29, 847, 472);
		getContentPane().add(tabbedPane);

		// DEFINIZIONE DELLE VARIE
		// TAB-----------------------------------------------------------------------------------
		JPanel tabDatiGenerali = new JPanel();
		JPanel tabGeneralit‡Clinica = new JPanel();
		JPanel tabAnamnesiAlimentare = new JPanel();
		JPanel tabFrequenzeAlimentari = new JPanel();
		JPanel tabPreferenze = new JPanel();
		JPanel tabMisureAntropometriche = new JPanel();
		JPanel tabConclusioni = new JPanel();

		// ISTANZIAZIONE DEGLI
		// ELEMENTI--------------------------------------------------------------
		dbInterface = new DbInterface();
		nameText = new JTextField();
		cognomeText = new JTextField();
		et‡Text = new JTextField();
		comboGiorno = new JComboBox();
		comboMese = new JComboBox();
		comboAnno = new JComboBox();
		comboAnno.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 16));
		CFText = new JTextField();
		dataVisitaText = new JTextField();
		dataVisitaText.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 16));
		alta = new JRadioButton("Alta");
		bassa = new JRadioButton("Bassa");
		media = new JRadioButton("Media");
		sexM = new JRadioButton("M");
		sexF = new JRadioButton("F");
		attivit‡Text = new JTextField();
		idratazioneText = new JTextField();
		alcoliciText = new JTextField();
		bevandeText = new JTextField();
		noteDietaArea = new JTextArea();
		pesoMinText = new JTextField();
		pesoMaxText = new JTextField();
		colazioneText = new JTextField();
		spuntino1Text = new JTextField();
		pranzoText = new JTextField();
		spuntino2Text = new JTextField();
		cenaText = new JTextField();
		dopoCenaText = new JTextField();
		condimentiText = new JTextField();
		dolciText = new JTextField();
		pizzaText = new JTextField();
		fruttaText = new JTextField();
		carneText = new JTextField();
		pesceText = new JTextField();
		formaggioText = new JTextField();
		affettatiText = new JTextField();
		uovaText = new JTextField();
		tonnoText = new JTextField();
		verdureText = new JTextField();
		patateText = new JTextField();
		piaceText = new JTextArea();
		nonPiaceText = new JTextArea();
		pesoText = new JTextField();
		altezzaText = new JTextField();
		circVitaText = new JTextField();
		circFianchiText = new JTextField();
		mestruazioniText = new JTextField();
		conclusioniText = new JTextArea();
		noteAttivitaText = new JTextArea();
		btnConfermaESalva = new JButton("Conferma e salva");

		// TAB DATI
		// PAZIENTE--------------------------------------------------------------------------------------------------
		tabbedPane.addTab("Dati Paziente", null, tabGeneralit‡Clinica, null);
		tabGeneralit‡Clinica.setLayout(null);

		JLabel labelNome = new JLabel("Nome:");
		labelNome.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		labelNome.setBounds(10, 58, 65, 33);
		tabGeneralit‡Clinica.add(labelNome);

		JLabel labelCognome = new JLabel("Cognome:");
		labelCognome.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN,
				16));
		labelCognome.setBounds(68, 103, 88, 38);
		tabGeneralit‡Clinica.add(labelCognome);

		JLabel labelData = new JLabel("Data di Nascita:");
		labelData.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		labelData.setBounds(68, 154, 120, 33);
		tabGeneralit‡Clinica.add(labelData);

		JLabel lblEt = new JLabel("Et\u00E0:");
		lblEt.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 18));
		lblEt.setBounds(68, 210, 56, 16);
		tabGeneralit‡Clinica.add(lblEt);

		nameText.setToolTipText("nome");
		nameText.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 16));
		nameText.setBounds(141, 63, 297, 22);
		tabGeneralit‡Clinica.add(nameText);
		nameText.setColumns(10);

		cognomeText.setToolTipText("Cognome");
		cognomeText.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		cognomeText.setBounds(161, 112, 277, 22);
		tabGeneralit‡Clinica.add(cognomeText);
		cognomeText.setColumns(10);

		et‡Text.setToolTipText("Et\u00E0");
		et‡Text.setBounds(119, 209, 319, 22);
		tabGeneralit‡Clinica.add(et‡Text);
		et‡Text.setColumns(10);

		comboGiorno.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		comboGiorno.setModel(new DefaultComboBoxModel(new String[] { "1", "2",
				"3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
				"14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
				"24", "25", "26", "27", "28", "29", "30", "31" }));
		comboGiorno.setBounds(191, 160, 56, 30);
		tabGeneralit‡Clinica.add(comboGiorno);

		JLabel label = new JLabel("/");
		label.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 18));
		label.setBounds(261, 160, 20, 27);
		tabGeneralit‡Clinica.add(label);

		caricaComboMesi();
		comboMese.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		comboMese.setBounds(284, 160, 122, 30);
		tabGeneralit‡Clinica.add(comboMese);

		JLabel label_1 = new JLabel("/");
		label_1.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 18));
		label_1.setBounds(418, 156, 20, 27);
		tabGeneralit‡Clinica.add(label_1);

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
		comboAnno.setBounds(445, 160, 65, 30);
		tabGeneralit‡Clinica.add(comboAnno);

		JLabel CFLabel = new JLabel("CF:");
		CFLabel.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 16));
		CFLabel.setBounds(68, 256, 88, 38);
		tabGeneralit‡Clinica.add(CFLabel);

		CFText.setToolTipText("Et\u00E0");
		CFText.setColumns(10);
		CFText.setBounds(119, 265, 319, 22);
		tabGeneralit‡Clinica.add(CFText);

		JLabel lblSesso = new JLabel("Sesso:");
		lblSesso.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 16));
		lblSesso.setBounds(68, 300, 88, 38);
		tabGeneralit‡Clinica.add(lblSesso);

		sexM.setBounds(141, 308, 47, 25);
		tabGeneralit‡Clinica.add(sexM);

		sexF.setBounds(213, 308, 56, 25);
		tabGeneralit‡Clinica.add(sexF);

		JLabel lblDataVisita = new JLabel("Data Visita:");
		lblDataVisita.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		lblDataVisita.setBounds(68, 351, 100, 30);
		tabGeneralit‡Clinica.add(lblDataVisita);

		dataVisitaText.setBounds(161, 356, 192, 22);
		tabGeneralit‡Clinica.add(dataVisitaText);
		dataVisitaText.setColumns(10);
		LocalDateTime dateNow = LocalDateTime.now();
		GregorianCalendar g = new GregorianCalendar(dateNow.getYear(), dateNow.getMonthValue(), dateNow.getDayOfMonth());
		java.util.Date u = g.getTime();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		dataVisitaText.setText(dateNow.format(formatter));
		dataVisitaText.setFocusable(false);

		// TAB GENERALIT‡ E
		// CLINICA---------------------------------------------------------------------------------------------------
		tabbedPane.addTab("Generalit‡ e clinica", null, tabDatiGenerali, null);
		tabDatiGenerali.setLayout(null);

		JLabel labelLavoro = new JLabel("Attivit\u00E0 lavortiva:");
		labelLavoro.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		labelLavoro.setBounds(22, 50, 132, 30);
		tabDatiGenerali.add(labelLavoro);

		JLabel labelAttivit‡ = new JLabel("Attivit\u00E0 fisica:");
		labelAttivit‡.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		labelAttivit‡.setBounds(22, 102, 122, 30);
		tabDatiGenerali.add(labelAttivit‡);

		media.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 16));
		media.setBounds(233, 102, 94, 30);
		tabDatiGenerali.add(media);

		alta.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 16));
		alta.setBounds(331, 102, 79, 30);
		tabDatiGenerali.add(alta);

		bassa.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 16));
		bassa.setBounds(152, 102, 77, 30);
		tabDatiGenerali.add(bassa);

		noteAttivitaText.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		noteAttivitaText.setToolTipText("note\r\n");
		noteAttivitaText.setBounds(152, 141, 646, 30);
		tabDatiGenerali.add(noteAttivitaText);

		attivit‡Text.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		attivit‡Text.setBounds(152, 51, 223, 30);
		tabDatiGenerali.add(attivit‡Text);
		attivit‡Text.setColumns(10);

		JLabel lblIdratazione = new JLabel("Idratazione");
		lblIdratazione.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		lblIdratazione.setBounds(22, 183, 118, 30);
		tabDatiGenerali.add(lblIdratazione);

		idratazioneText.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		idratazioneText.setBounds(152, 184, 234, 30);
		tabDatiGenerali.add(idratazioneText);
		idratazioneText.setColumns(10);

		JLabel alcoliciLabel = new JLabel("Alcolici");
		alcoliciLabel.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		alcoliciLabel.setBounds(22, 226, 69, 30);
		tabDatiGenerali.add(alcoliciLabel);

		alcoliciText.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		alcoliciText.setColumns(10);
		alcoliciText.setBounds(152, 227, 234, 30);
		tabDatiGenerali.add(alcoliciText);

		bevandeText.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		bevandeText.setColumns(10);
		bevandeText.setBounds(152, 270, 234, 30);
		tabDatiGenerali.add(bevandeText);

		JLabel bevandeLabel = new JLabel("Bevande Gasate");
		bevandeLabel.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		bevandeLabel.setBounds(22, 273, 132, 30);
		tabDatiGenerali.add(bevandeLabel);

		JLabel dietePassateLabel = new JLabel("Diete passate");
		dietePassateLabel.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		dietePassateLabel.setBounds(22, 316, 122, 30);
		tabDatiGenerali.add(dietePassateLabel);

		noteDietaArea.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		noteDietaArea.setBounds(152, 321, 625, 70);
		tabDatiGenerali.add(noteDietaArea);

		JScrollPane scrollBar = new JScrollPane(noteDietaArea);
		scrollBar.setBounds(152, 316, 646, 94);
		tabDatiGenerali.add(scrollBar);

		JLabel pesoMinLabel = new JLabel("Peso minimo");
		pesoMinLabel.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		pesoMinLabel.setBounds(448, 184, 106, 30);
		tabDatiGenerali.add(pesoMinLabel);

		pesoMinText.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		pesoMinText.setColumns(10);
		pesoMinText.setBounds(564, 184, 234, 30);
		tabDatiGenerali.add(pesoMinText);

		pesoMaxText.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		pesoMaxText.setColumns(10);
		pesoMaxText.setBounds(564, 231, 234, 30);
		tabDatiGenerali.add(pesoMaxText);

		JLabel pesoMaxLabel = new JLabel("Peso massimo");
		pesoMaxLabel.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		pesoMaxLabel.setBounds(448, 230, 106, 30);
		tabDatiGenerali.add(pesoMaxLabel);

		// TAB ANAMNESI ALIMENTARE
		// ------------------------------------------------------------------------------------------------
		tabbedPane.addTab("Anamnesi alimentare", null, tabAnamnesiAlimentare,
				null);
		tabAnamnesiAlimentare.setLayout(null);

		JLabel colazioneLabel = new JLabel("Colazione");
		colazioneLabel.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		colazioneLabel.setBounds(39, 57, 106, 30);
		tabAnamnesiAlimentare.add(colazioneLabel);

		JLabel pranzoLabel = new JLabel("Pranzo");
		pranzoLabel.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		pranzoLabel.setBounds(39, 143, 69, 30);
		tabAnamnesiAlimentare.add(pranzoLabel);

		JLabel spuntino1Label = new JLabel("Spuntino");
		spuntino1Label.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		spuntino1Label.setBounds(39, 100, 69, 30);
		tabAnamnesiAlimentare.add(spuntino1Label);

		JLabel spuntino2Label = new JLabel("Spuntino");
		spuntino2Label.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		spuntino2Label.setBounds(39, 186, 69, 30);
		tabAnamnesiAlimentare.add(spuntino2Label);

		JLabel cenaLabel = new JLabel("Cena");
		cenaLabel.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		cenaLabel.setBounds(39, 229, 69, 30);
		tabAnamnesiAlimentare.add(cenaLabel);

		JLabel dopoCenaLabel = new JLabel("Dopo cena");
		dopoCenaLabel.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		dopoCenaLabel.setBounds(39, 275, 89, 30);
		tabAnamnesiAlimentare.add(dopoCenaLabel);

		dopoCenaText.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		dopoCenaText.setColumns(10);
		dopoCenaText.setBounds(140, 275, 655, 30);
		tabAnamnesiAlimentare.add(dopoCenaText);

		cenaText.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 16));
		cenaText.setColumns(10);
		cenaText.setBounds(140, 229, 655, 30);
		tabAnamnesiAlimentare.add(cenaText);

		spuntino2Text.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		spuntino2Text.setColumns(10);
		spuntino2Text.setBounds(140, 186, 655, 30);
		tabAnamnesiAlimentare.add(spuntino2Text);

		pranzoText.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		pranzoText.setColumns(10);
		pranzoText.setBounds(140, 143, 655, 30);
		tabAnamnesiAlimentare.add(pranzoText);

		spuntino1Text.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		spuntino1Text.setColumns(10);
		spuntino1Text.setBounds(140, 100, 655, 30);
		tabAnamnesiAlimentare.add(spuntino1Text);

		colazioneText.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		colazioneText.setColumns(10);
		colazioneText.setBounds(140, 57, 655, 30);
		tabAnamnesiAlimentare.add(colazioneText);
		tabbedPane.addTab("Frequenze alimentari", null, tabFrequenzeAlimentari,
				null);

		// TAB FREQUENZE ALIMENTARI
		// ----------------------------------------------------------------------------------------------------------------

		tabFrequenzeAlimentari.setLayout(null);

		JLabel lblCarne = new JLabel("Carne");
		lblCarne.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 16));
		lblCarne.setBounds(37, 13, 106, 30);
		tabFrequenzeAlimentari.add(lblCarne);

		JLabel lblPesce = new JLabel("Pesce");
		lblPesce.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 16));
		lblPesce.setBounds(37, 48, 106, 30);
		tabFrequenzeAlimentari.add(lblPesce);

		JLabel lblFormaggio = new JLabel("Formaggio");
		lblFormaggio.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		lblFormaggio.setBounds(37, 83, 106, 30);
		tabFrequenzeAlimentari.add(lblFormaggio);

		JLabel lblAffettati = new JLabel("Affettati");
		lblAffettati.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		lblAffettati.setBounds(37, 118, 106, 30);
		tabFrequenzeAlimentari.add(lblAffettati);

		JLabel lblUova = new JLabel("Uova");
		lblUova.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 16));
		lblUova.setBounds(37, 153, 106, 30);
		tabFrequenzeAlimentari.add(lblUova);

		JLabel lblTonno = new JLabel("Tonno");
		lblTonno.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 16));
		lblTonno.setBounds(37, 188, 106, 30);
		tabFrequenzeAlimentari.add(lblTonno);

		JLabel lblPatate = new JLabel("Patate");
		lblPatate.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		lblPatate.setBounds(37, 223, 106, 30);
		tabFrequenzeAlimentari.add(lblPatate);

		JLabel lblVerdure = new JLabel("Verdure");
		lblVerdure.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		lblVerdure.setBounds(37, 258, 106, 30);
		tabFrequenzeAlimentari.add(lblVerdure);

		JLabel lblFrutta = new JLabel("Frutta");
		lblFrutta.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		lblFrutta.setBounds(37, 293, 106, 30);
		tabFrequenzeAlimentari.add(lblFrutta);

		JLabel lblCondimenti = new JLabel("Condimenti");
		lblCondimenti.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		lblCondimenti.setBounds(37, 328, 106, 30);
		tabFrequenzeAlimentari.add(lblCondimenti);

		JLabel lblPizza = new JLabel("Pizza");
		lblPizza.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 16));
		lblPizza.setBounds(37, 398, 106, 30);
		tabFrequenzeAlimentari.add(lblPizza);

		JLabel lblDolci = new JLabel("Dolci");
		lblDolci.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 16));
		lblDolci.setBounds(37, 363, 106, 30);
		tabFrequenzeAlimentari.add(lblDolci);

		condimentiText.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		condimentiText.setColumns(10);
		condimentiText.setBounds(140, 333, 655, 30);
		tabFrequenzeAlimentari.add(condimentiText);

		dolciText.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		dolciText.setColumns(10);
		dolciText.setBounds(140, 368, 655, 30);
		tabFrequenzeAlimentari.add(dolciText);

		pizzaText.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		pizzaText.setColumns(10);
		pizzaText.setBounds(140, 403, 655, 30);
		tabFrequenzeAlimentari.add(pizzaText);

		fruttaText.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		fruttaText.setColumns(10);
		fruttaText.setBounds(140, 298, 655, 30);
		tabFrequenzeAlimentari.add(fruttaText);

		carneText.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		carneText.setColumns(10);
		carneText.setBounds(140, 18, 655, 30);
		tabFrequenzeAlimentari.add(carneText);

		pesceText.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		pesceText.setColumns(10);
		pesceText.setBounds(140, 53, 655, 30);
		tabFrequenzeAlimentari.add(pesceText);

		formaggioText.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		formaggioText.setColumns(10);
		formaggioText.setBounds(140, 88, 655, 30);
		tabFrequenzeAlimentari.add(formaggioText);

		affettatiText.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		affettatiText.setColumns(10);
		affettatiText.setBounds(140, 123, 655, 30);
		tabFrequenzeAlimentari.add(affettatiText);

		uovaText.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 16));
		uovaText.setColumns(10);
		uovaText.setBounds(140, 158, 655, 30);
		tabFrequenzeAlimentari.add(uovaText);

		tonnoText.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		tonnoText.setColumns(10);
		tonnoText.setBounds(140, 193, 655, 30);
		tabFrequenzeAlimentari.add(tonnoText);

		patateText.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		patateText.setColumns(10);
		patateText.setBounds(140, 228, 655, 30);
		tabFrequenzeAlimentari.add(patateText);

		verdureText.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		verdureText.setColumns(10);
		verdureText.setBounds(140, 263, 655, 30);
		tabFrequenzeAlimentari.add(verdureText);

		// TAB
		// PREFERENZE--------------------------------------------------------------------------------------------------------------------
		tabbedPane.addTab("Preferenze", null, tabPreferenze, null);
		tabPreferenze.setLayout(null);

		JLabel lblGliPiace = new JLabel("Gli piace:");
		lblGliPiace.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		lblGliPiace.setBounds(24, 13, 147, 30);
		tabPreferenze.add(lblGliPiace);

		piaceText.setBounds(34, 56, 757, 146);
		tabPreferenze.add(piaceText);

		JScrollPane scrollPiace = new JScrollPane(piaceText);
		scrollPiace.setBounds(24, 53, 806, 153);
		tabPreferenze.add(scrollPiace);

		JLabel lblNonPiace = new JLabel("Non piace:");
		lblNonPiace.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		lblNonPiace.setBounds(24, 219, 147, 30);
		tabPreferenze.add(lblNonPiace);

		nonPiaceText.setBounds(34, 262, 796, 167);
		tabPreferenze.add(nonPiaceText);

		JScrollPane scrollNonPiace = new JScrollPane(nonPiaceText);
		scrollNonPiace.setBounds(24, 252, 806, 177);
		tabPreferenze.add(scrollNonPiace);

		// TAB MISURE
		// ANTROPOMETRICHE--------------------------------------------------------------------------------------------------------------------
		tabbedPane.addTab("Misure antropometriche", null,
				tabMisureAntropometriche, null);
		tabMisureAntropometriche.setLayout(null);

		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 16));
		lblPeso.setBounds(90, 33, 67, 30);
		tabMisureAntropometriche.add(lblPeso);

		JLabel lblAltezza = new JLabel("Altezza:");
		lblAltezza.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		lblAltezza.setBounds(90, 85, 76, 30);
		tabMisureAntropometriche.add(lblAltezza);

		JLabel lblCircVita = new JLabel("Circ. vita:");
		lblCircVita.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		lblCircVita.setBounds(89, 144, 83, 30);
		tabMisureAntropometriche.add(lblCircVita);

		JLabel lblCircFianchi = new JLabel("Circ. fianchi:");
		lblCircFianchi.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		lblCircFianchi.setBounds(90, 201, 90, 30);
		tabMisureAntropometriche.add(lblCircFianchi);

		JLabel lblMestruazioni = new JLabel("Mestruazioni:");
		lblMestruazioni.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		lblMestruazioni.setBounds(90, 260, 103, 30);
		tabMisureAntropometriche.add(lblMestruazioni);

		pesoText.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 16));
		pesoText.setColumns(10);
		pesoText.setBounds(187, 33, 76, 30);
		tabMisureAntropometriche.add(pesoText);

		JLabel lblKg = new JLabel("Hg");
		lblKg.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 16));
		lblKg.setBounds(275, 33, 67, 30);
		tabMisureAntropometriche.add(lblKg);

		altezzaText.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		altezzaText.setColumns(10);
		altezzaText.setBounds(187, 90, 76, 30);
		tabMisureAntropometriche.add(altezzaText);

		JLabel lblM = new JLabel("cm");
		lblM.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 16));
		lblM.setBounds(275, 85, 67, 30);
		tabMisureAntropometriche.add(lblM);

		circVitaText.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		circVitaText.setColumns(10);
		circVitaText.setBounds(187, 149, 76, 30);
		tabMisureAntropometriche.add(circVitaText);

		circFianchiText.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		circFianchiText.setColumns(10);
		circFianchiText.setBounds(187, 206, 76, 30);
		tabMisureAntropometriche.add(circFianchiText);

		JLabel lblCm_1 = new JLabel("cm");
		lblCm_1.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 16));
		lblCm_1.setBounds(275, 152, 67, 30);
		tabMisureAntropometriche.add(lblCm_1);

		JLabel lblCm = new JLabel("cm");
		lblCm.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 16));
		lblCm.setBounds(275, 209, 67, 30);
		tabMisureAntropometriche.add(lblCm);

		mestruazioniText.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		mestruazioniText.setColumns(10);
		mestruazioniText.setBounds(187, 265, 284, 30);
		tabMisureAntropometriche.add(mestruazioniText);

		// TAB
		// CONCLUSIONI--------------------------------------------------------------------------------------------------------------------
		tabbedPane.addTab("Conclusioni", null, tabConclusioni, null);
		tabConclusioni.setLayout(null);

		JLabel lblNewLabel = new JLabel("Conclusioni");
		lblNewLabel.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		lblNewLabel.setBounds(12, 13, 101, 30);
		tabConclusioni.add(lblNewLabel);

		conclusioniText.setBounds(22, 67, 808, 362);
		tabConclusioni.add(conclusioniText);

		JScrollPane scrollConclusioni = new JScrollPane(conclusioniText);
		scrollConclusioni.setBounds(12, 56, 818, 373);
		tabConclusioni.add(scrollConclusioni);

		// BOTTONE
		// CONFERMA-----------------------------------------------------------------------------------------------------------------
		btnConfermaESalva.setFont(new Font("Microsoft JhengHei UI Light",
				Font.BOLD, 18));
		btnConfermaESalva.setBounds(329, 514, 232, 40);
		getContentPane().add(btnConfermaESalva);
	}

	// METODI VARI
	// USATI------------------------------------------------------------------------------------------------------------
	public char convertiAttivit‡() {

		if (alta.isSelected())
			return 'H';
		if (media.isSelected())
			return 'M';

		return 'L';
	}

	// CONTROLLO DEI CAMPI OBBLIGATORI PER IL SALVATAGGIO DELL'UTENTE
	public boolean controllaCampi() {

		boolean valor = true;

		if (nameText.getText().equals("")) {
			valor = false;
			nameText.setBackground(Color.GREEN);
		} else {
			nameText.setBackground(Color.WHITE);
		}
		if (cognomeText.getText().equals("")) {
			valor = false;
			cognomeText.setBackground(Color.GREEN);
		} else
			cognomeText.setBackground(Color.WHITE);

		if (et‡Text.getText().equals("")) {
			valor = false;
			et‡Text.setBackground(Color.GREEN);
		} else
			et‡Text.setBackground(Color.WHITE);

		if (!(alta.isSelected() || media.isSelected() || bassa.isSelected())) {

			valor = false;
			alta.setBackground(Color.GREEN);
			media.setBackground(Color.GREEN);
			bassa.setBackground(Color.GREEN);
		} else {
			alta.setBackground(getBackground());
			media.setBackground(getBackground());
			bassa.setBackground(getBackground());

		}

		if (!(sexM.isSelected() || sexF.isSelected())) {
			valor = false;
			sexM.setBackground(Color.GREEN);
			sexF.setBackground(Color.GREEN);
		} else {
			sexM.setBackground(getBackground());
			sexF.setBackground(getBackground());
		}

		if (CFText.getText().equals("")) {
			CFText.setBackground(Color.GREEN);
			valor = false;
		} else
			CFText.setBackground(Color.WHITE);

		if (pesoText.getText().equals("")) {
			pesoText.setBackground(Color.GREEN);
			valor = false;
		} else
			pesoText.setBackground(Color.WHITE);

		if (altezzaText.getText().equals("")) {
			altezzaText.setBackground(Color.GREEN);
			valor = false;
		} else
			altezzaText.setBackground(Color.WHITE);

		return valor;
	}

	public boolean isNumeric(String str) {
		try {
			int d = Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public void reset() {
		nameText.setText("");
		CFText.setText("");
		cognomeText.setText("");
		et‡Text.setText("");
		attivit‡Text.setText("");
		idratazioneText.setText("");
		alcoliciText.setText("");
		bevandeText.setText("");
		pesoMinText.setText("");
		pesoMaxText.setText("");
		dopoCenaText.setText("");
		noteDietaArea.setText("");
		noteAttivitaText.setText("");
		cenaText.setText("");
		spuntino2Text.setText("");
		pranzoText.setText("");
		spuntino1Text.setText("");
		colazioneText.setText("");
		condimentiText.setText("");
		dolciText.setText("");
		pizzaText.setText("");
		fruttaText.setText("");
		carneText.setText("");
		pesceText.setText("");
		formaggioText.setText("");
		affettatiText.setText("");
		uovaText.setText("");
		tonnoText.setText("");
		patateText.setText("");
		verdureText.setText("");
		alta.setSelected(false);
		bassa.setSelected(false);
		media.setSelected(false);
		CFText.setText("");
		sexM.setSelected(false);
		sexF.setSelected(false);
		pesoText.setText("");
		altezzaText.setText("");
		circVitaText.setText("");
		circFianchiText.setText("");
		mestruazioniText.setText("");
		conclusioniText.setText("");
		noteAttivitaText.setText("");
		noteDietaArea.setText("");
		nonPiaceText.setText("");
	}

	public void inizializzaStack(Stack<String> x) {
		x.push(conclusioniText.getText());
		x.push(mestruazioniText.getText());
		x.push(nonPiaceText.getText());
		x.push(piaceText.getText());
		x.push(pizzaText.getText());
		x.push(dolciText.getText());
		x.push(condimentiText.getText());
		x.push(fruttaText.getText());
		x.push(verdureText.getText());
		x.push(patateText.getText());
		x.push(tonnoText.getText());
		x.push(uovaText.getText());
		x.push(affettatiText.getText());
		x.push(formaggioText.getText());
		x.push(pesceText.getText());
		x.push(carneText.getText());
		x.push(dopoCenaText.getText());
		x.push(cenaText.getText());
		x.push(spuntino2Text.getText());
		x.push(pranzoText.getText());
		x.push(spuntino1Text.getText());
		x.push(colazioneText.getText());
		x.push(noteDietaArea.getText());
		x.push(pesoMaxText.getText());
		x.push(pesoMinText.getText());
		x.push(bevandeText.getText());
		x.push(alcoliciText.getText());
		x.push(idratazioneText.getText());
		x.push(noteAttivitaText.getText());
		x.push(attivit‡Text.getText());
		x.push(CFText.getText());
		x.push(cognomeText.getText());
		x.push(nameText.getText());
	}

	public void caricaComboMesi() {
		Giorni arrayGiorni[] = Giorni.values();
		for (Giorni g : arrayGiorni)
			comboMese.addItem(g);
	}

	public void importaDesktopWindow(DesktopWindow dw) {

		this.desktopWindow = dw;

	}

	// METODI PER I LISTENER
	// ---------------------------------------------------------------------------------------------------------
	public void AddSexMCheckListener(MouseListener listener) {
		sexM.addMouseListener(listener);
	}

	public void AddSexFCheckListener(MouseListener listener) {
		sexF.addMouseListener(listener);
	}

	public void AddAttivit‡BassaListener(MouseListener listener) {
		bassa.addMouseListener(listener);
	}

	public void AddAttivit‡MediaListener(MouseListener listener) {
		media.addMouseListener(listener);
	}

	public void AddAttivit‡AltaListener(MouseListener listener) {
		alta.addMouseListener(listener);
	}

	public void AddEt‡Listener(FocusListener listener) {
		et‡Text.addFocusListener(listener);
	}

	public void AddPesoListener(FocusListener listener) {
		pesoText.addFocusListener(listener);
	}

	public void AddAltezzaListener(FocusListener listener) {
		altezzaText.addFocusListener(listener);
	}

	public void AddCircVitaListener(FocusListener listener) {
		circVitaText.addFocusListener(listener);
	}

	public void AddCircFianchiListener(FocusListener listener) {
		circFianchiText.addFocusListener(listener);
	}

	public void AddbtnConfermaESalvaPrimaVisitaListener(ActionListener listener) {
		btnConfermaESalva.addActionListener(listener);
	}

	// GETTERS
	// ---------------------------------------------------------------------------------------------------------
	public JRadioButton getSexM() {
		return sexM;
	}

	public JRadioButton getSexF() {
		return sexF;
	}

	public DesktopWindow getDesktopWindow() {
		return desktopWindow;
	}

	public JLabel getImgUnlock() {
		return imgUnlock;
	}

	public DbInterface getDbInterface() {
		return dbInterface;
	}

	public JTextField getNameText() {
		return nameText;
	}

	public JTextField getCognomeText() {
		return cognomeText;
	}

	public JTextField getEt‡Text() {
		return et‡Text;
	}

	public JTextField getAttivit‡Text() {
		return attivit‡Text;
	}

	public JTextField getIdratazioneText() {
		return idratazioneText;
	}

	public JTextField getAlcoliciText() {
		return alcoliciText;
	}

	public JTextField getBevandeText() {
		return bevandeText;
	}

	public JTextField getPesoMinText() {
		return pesoMinText;
	}

	public JTextField getPesoMaxText() {
		return pesoMaxText;
	}

	public JTextField getDopoCenaText() {
		return dopoCenaText;
	}

	public JTextField getCenaText() {
		return cenaText;
	}

	public JTextField getSpuntino2Text() {
		return spuntino2Text;
	}

	public JTextField getPranzoText() {
		return pranzoText;
	}

	public JTextField getSpuntino1Text() {
		return spuntino1Text;
	}

	public JTextField getColazioneText() {
		return colazioneText;
	}

	public JTextField getCondimentiText() {
		return condimentiText;
	}

	public JTextField getDolciText() {
		return dolciText;
	}

	public JTextField getPizzaText() {
		return pizzaText;
	}

	public JTextField getFruttaText() {
		return fruttaText;
	}

	public JTextField getCarneText() {
		return carneText;
	}

	public JTextField getPesceText() {
		return pesceText;
	}

	public JTextField getFormaggioText() {
		return formaggioText;
	}

	public JTextField getAffettatiText() {
		return affettatiText;
	}

	public JTextField getUovaText() {
		return uovaText;
	}

	public JTextField getTonnoText() {
		return tonnoText;
	}

	public JTextField getPatateText() {
		return patateText;
	}

	public JTextField getVerdureText() {
		return verdureText;
	}

	public JRadioButton getAlta() {
		return alta;
	}

	public JRadioButton getBassa() {
		return bassa;
	}

	public JRadioButton getMedia() {
		return media;
	}

	public JTextField getCFText() {
		return CFText;
	}

	public JTextField getPesoText() {
		return pesoText;
	}

	public JTextField getAltezzaText() {
		return altezzaText;
	}

	public JTextField getCircVitaText() {
		return circVitaText;
	}

	public JTextField getCircFianchiText() {
		return circFianchiText;
	}

	public JTextField getMestruazioniText() {
		return mestruazioniText;
	}

	public JTextArea getConclusioniText() {
		return conclusioniText;
	}

	public JTextArea getNoteAttivitaText() {
		return noteAttivitaText;
	}

	public JTextArea getNoteDietaArea() {
		return noteDietaArea;
	}

	public JTextArea getPiaceText() {
		return piaceText;
	}

	public JTextArea getNonPiaceText() {
		return nonPiaceText;
	}

	public JComboBox getComboMese() {
		return comboMese;
	}

	public JComboBox getComboGiorno() {
		return comboGiorno;
	}

	public JComboBox getComboAnno() {
		return comboAnno;
	}

	public JTextField getDataVisitaText() {
		return dataVisitaText;
	}

}

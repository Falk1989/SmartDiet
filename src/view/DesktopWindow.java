package view;

import view.ScegliVisita;
import interfacce.interfaceDesktopWindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumn;

import model.ActiveUser;
import model.Message;
import dbAccess.DbInterface;
import enumerativi.Giorni;

public class DesktopWindow extends JFrame implements Observer {

	// 2 variabili che devono essere viste da tutti. Verranno poi sostituite da
	// Observable-Observer
	public String utente;
	public String profilo;
	// -----------------------------------------
	// serve per gestire MVC
	private ActiveUser activeUser;
	// -------------------------------------------

	// elenco di tutti gli oggetti grafici che sono presenti nella finestra.
	// Dichiarati e non ancora instanziati
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenuItem itemEsci;
	private JMenu menuAnagrafiche;
	private JMenu menuFile;
	private JSeparator separator;
	private JDesktopPane desktopPane;
	private JLabel imageLabel;
	private JPanel panelSx;
	private JPanel panelDx;
	private JPanel panelCenter;
	private JLabel lblUsers;
	private JLabel lblProfile;
	private JMenu smnDottDietologo;
	private JMenuItem itmInserisciDietologo;
	private JMenuItem itmModificaDietologo;
	private JMenuItem itmCancellaDietologo;
	private JMenu smnAmministratoriSistema;
	private JSeparator separator_1;
	private JMenuItem itmInserisciAmministratore;
	private JMenuItem itmModificaAmministratore;
	private JMenuItem itmCancellaAmministratore;
	private JMenu smnPaziente;
	private JMenuItem itmInserisciPaziente;
	private JMenuItem itmModificaPaziente;
	private JMenuItem itmCancellaPaziente;
	private JLabel labelSX;
	private JLabel labelDX;
	private JMenu menuDieta;
	private JMenuItem itmCreaDietaManuale;
	private JMenuItem itmVisualizza;
	private JMenuItem itmCreaDietaAutomatica;
	private JMenu menuLog;
	private JMenuItem itmVisualizzaAttivitàSuDB;
	private JMenuItem itemLogin;
	private JMenuItem itemLogout;

	// elenco di strutture utili nell'elaborazione
	private Dimension dimension;

	// Elenco delle external frame che possono essere attivate dalla finestra
	// principale DESKTOPWINDOW
	private FirstEnter firstEnterWindow;
	private Login loginWindow;
	private InsertAmministratore insertAmministratore;
	private UpdateAmministratore updateAmministratore;
	private DeleteAmministratore deleteAmministratore;
	private InsertDottore insertDottore;
	private UpdateDottore updateDottore;
	private DeleteDottore deleteDottore;
	
	private InsertPaziente insertPaziente;
	private UpdatePaziente updatePaziente;
	
	private ScegliVisita scegliVisita;
	private VisualizzaVisita visualizzaVisita;
	private DietaPatientChoose dietaParientChoose;
	private PrimaVisita primaVisita;
	private LogSistema logSistema;
	private AllertWindow allertWindow;

	// riferimento ad un oggetto che permette di interfacciarsi ai servizi del
	// DB
	private DbInterface dbInterface;
	private JMenu menuVisite;
	private JMenuItem itmScegliPaziente;
	private JMenuItem mntmCreaVisita;

	// --------------------------------------------------------------------------

	/**
	 * Create the frame.
	 */
	public DesktopWindow(ActiveUser au) {
		// --------------------------aggiungo al model la view come osservatore
		activeUser = au;
		activeUser.addObserver(this);
		// --------------------------------------------------------------------

		// istanzio subito gli oggeti perchè nel menu di chiamata, non mi
		// permette di passagli "this"
	

		loginWindow = new Login();
		loginWindow.importaDesktopWindow(this);

		firstEnterWindow = new FirstEnter();
		firstEnterWindow.importaDesktopWindow(this);

		dbInterface = new DbInterface();
		allertWindow = new AllertWindow();

		insertAmministratore = new InsertAmministratore();
		insertAmministratore.importaDesktopWindow(this);

		updateAmministratore = new UpdateAmministratore(); // gli
		updateAmministratore.importaDesktopWindow(this);

		deleteAmministratore = new DeleteAmministratore();
		deleteAmministratore.importaDesktopWindow(this);

		insertDottore = new InsertDottore();
		insertDottore.importaDesktopWindow(this);

		updateDottore = new UpdateDottore();
		updateDottore.importaDesktopWindow(this);

		deleteDottore = new DeleteDottore();
		deleteDottore.importaDesktopWindow(this);

		scegliVisita = new ScegliVisita();
		scegliVisita.importaDesktopWindow(this);

		visualizzaVisita = new VisualizzaVisita();
		visualizzaVisita.importaDesktopWindow(this);

		dietaParientChoose = new DietaPatientChoose();
		dietaParientChoose.importaDesktopWindow(this);

		primaVisita = new PrimaVisita();
		primaVisita.importaDesktopWindow(this);

		logSistema = new LogSistema();
		logSistema.importaDesktopWindow(this);
		
		insertPaziente = new InsertPaziente();
		insertPaziente.importaDesktopWindow(this);
		
		updatePaziente = new UpdatePaziente();
		updatePaziente.importaDesktopWindow(this);
		
		// ----------------------------------------------------------------------------
		// disegno la finestra
		drawGUI();
		// -----------------------------------------------------------------------------

		// gestisco
		// ----------accesso--------------------------------------------------
		accessoPrimoOSuccessivo();
		// ----------------------------------------------------------------------------

		// elenco completo
		// listener------------------------------------------------------------
		// -------------------------------------------------------------------------------------

		// in ascolto su quando viene cliccata la voce Login del menu FILE
		itemLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// internalFrame visibile e centramento
				loginWindow.setVisible(true);
				loginWindow.setLocation(
						(desktopPane.getSize().width - loginWindow.getSize().width) / 2,
						(desktopPane.getSize().height - loginWindow.getSize().height) / 2);
				loginWindow.getFieldUsarName().setText("");
				loginWindow.getFieldPassword().setText("");

			}
		});

		// in ascolto su quando viene cliccata la voce logOut del menu FILE
		itemLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logOutProcedure();
			}
		});

		// in ascolto su quando viene cliccata la voce exit del menu FILE
		itemEsci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(JFrame.EXIT_ON_CLOSE); // la finestra viene
				// distrutta

			}
		});

		// in ascolto su quando viene cliccata la voce di inserimetno
		// amministratore del menu ANAGRAFICA AMMINISTRATORE
		itmInserisciAmministratore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// disabilito la voce di logout dal menu file
				itemLogout.setEnabled(false);
				// AZZERO
				// i campi della jinternalframe perchè all'ultimo acceso
				// essa è stata semplicemnte nascosta per velocizzare
				// l'applicazione
				insertAmministratore.getFieldNickName().setText("");
				insertAmministratore.getFieldPassword().setText("");
				insertAmministratore.getImgCheckNickName().setVisible(false);

				// non ricarico nulla in quanto è un inserimento

				// redo visibile la jinternalframe
				insertAmministratore.setVisible(true);
				insertAmministratore.getFieldNickName().requestFocus();
				// posizionamento della finestra al centro
				// della DESKTOPWINDOW
				insertAmministratore.setLocation(
						(desktopPane.getSize().width - insertAmministratore
								.getSize().width) / 2,
						(desktopPane.getSize().height - insertAmministratore
								.getSize().height) / 2);

			}
		});
		// in ascolto su quando viene cliccata la voce di modifica
		// amministratore del menu ANAGRAFICA AMMINISTRATORE
		itmModificaAmministratore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// disabilito la voce di logout dal menu file
				itemLogout.setEnabled(false);

				// AZZERO i campi della jinternalframe perchè all'ultimo acceso
				// essa è stata semplicemnte nascosta per velocizzare
				// l'applicazione
				updateAmministratore.getFieldNewNickName().setText("");
				updateAmministratore.getFieldNewPassword().setText("");
				updateAmministratore.getCombNickName().removeAllItems();
				updateAmministratore.getImgCheckNickName().setVisible(false);

				// RICARICO
				// i i campi che mi servono cioè la lista degli utenti con
				// profilo ammistratore
				for (int i = 0; i < dbInterface.getElencoNickName(
						activeUser.getLevel()).size(); i++) {
					updateAmministratore.getCombNickName().addItem(
							dbInterface
									.getElencoNickName(activeUser.getLevel())
									.get(i));
				}
				// propongo come primo utente quello momentaneamente loggato
				updateAmministratore.getCombNickName().setSelectedItem(
						activeUser.getNickName());

				// redo visibile la jinternalframe
				updateAmministratore.setVisible(true);

				// posizionamento della finestra al centro
				// della DESKTOPWINDOW
				updateAmministratore.setLocation(
						(desktopPane.getSize().width - updateAmministratore
								.getSize().width) / 2,
						(desktopPane.getSize().height - updateAmministratore
								.getSize().height) / 2);

			}
		});

		// in ascolto su quando viene cliccata la voce di cancellazione
		// amministratore del menu ANAGRAFICA AMMINISTRATORE
		itmCancellaAmministratore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// disabilito la voce di logout dal menu file
				itemLogout.setEnabled(false);

				// AZZERO i campi della jinternalframe perchè all'ultimo acceso
				// essa è stata semplicemnte nascosta per velocizzare
				// l'applicazione
				deleteAmministratore.getCombNickName().removeAllItems();

				// RICARICO
				// i i campi che mi servono cioè la lista degli utenti con
				// profilo ammistratore
				for (int i = 0; i < dbInterface.getElencoNickName(
						activeUser.getLevel()).size(); i++) {
					deleteAmministratore.getCombNickName().addItem(
							dbInterface
									.getElencoNickName(activeUser.getLevel())
									.get(i));
				}
				// propongo come primo utente quello momentaneamente loggato
				deleteAmministratore.getCombNickName().setSelectedItem(
						activeUser.getNickName());

				// redo visibile la jinternalframe
				deleteAmministratore.setVisible(true);

				// posizionamento della finestra al centro
				// della DESKTOPWINDOW
				deleteAmministratore.setLocation(
						(desktopPane.getSize().width - deleteAmministratore
								.getSize().width) / 2,
						(desktopPane.getSize().height - deleteAmministratore
								.getSize().height) / 2);
			}
		});

		// in ascolto su quando viene cliccata la voce di inserimento
		// Biologo nutrizionista del menu ANAGRAFICA BIOLOGO NUTRIZIONISTA
		itmInserisciDietologo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// disabilito la voce di logout dal menu file
				itemLogout.setEnabled(false);

				// AZZERO i campi della jinternalframe perchè all'ultimo acceso
				// essa è stata semplicemnte nascosta per velocizzare
				// l'applicazione
				insertDottore.getFieldNickName().setText("");
				insertDottore.getFieldPassword().setText("");
				insertDottore.getFieldCognome().setText("");
				insertDottore.getFieldNome().setText("");
				insertDottore.getComboAnno().setSelectedIndex(0);
				insertDottore.getComboMese().setSelectedIndex(0);
				insertDottore.getComboGiorno().setSelectedIndex(0);
				insertDottore.getFieldCodiceFiscale().setText("");
				insertDottore.getFieldCellulare().setText("");
				insertDottore.getFieldMail().setText("");
				insertDottore.getFieldFax().setText("");
				insertDottore.getImgCheckNickName().setVisible(false);
				// sbianco i campi che per errore di compilazione potrebbero
				// essere stati segnalati verde
				insertDottore.getFieldNickName().setBackground(Color.white);
				insertDottore.getFieldPassword().setBackground(Color.white);
				insertDottore.getFieldCognome().setBackground(Color.white);
				insertDottore.getFieldNome().setBackground(Color.white);
				insertDottore.getFieldCodiceFiscale()
						.setBackground(Color.white);

				// non ricarico nulla in quanto è un inserimento

				// redo visibile la jinternalframe
				insertDottore.setVisible(true);

				// posizionamento della finestra al centro
				// della DESKTOPWINDOW
				insertDottore.setLocation(
						(desktopPane.getSize().width - insertDottore.getSize().width) / 2,
						(desktopPane.getSize().height - insertDottore.getSize().height) / 2);
			}
		});

		// in ascolto su quando viene cliccata la voce di modifica
		// Biologo nutrizionista del menu ANAGRAFICA BIOLOGO NUTRIZIONISTA
		itmModificaDietologo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// disabilito la voce di logout dal menu file
				itemLogout.setEnabled(false);

				// AZZERO i campi della jinternalframe perchè all'ultimo acceso
				// essa è stata semplicemnte nascosta per velocizzare
				// l'applicazione
				updateDottore.getFieldNickName().setText("");
				updateDottore.getFieldPassword().setText("");
				updateDottore.getFieldCognome().setText("");
				updateDottore.getFieldNome().setText("");
				insertDottore.getComboAnno().setSelectedIndex(0);
				insertDottore.getComboMese().setSelectedIndex(0);
				insertDottore.getComboGiorno().setSelectedIndex(0);
				updateDottore.getFieldCodiceFiscale().setText("");
				updateDottore.getFieldCellulare().setText("");
				updateDottore.getFieldMail().setText("");
				updateDottore.getFieldFax().setText("");
				// sbianco i campi che per errore di compilazione potrebbero
				// essere stati segnalati verde
				updateDottore.getFieldNickName().setBackground(Color.white);
				updateDottore.getFieldPassword().setBackground(Color.white);
				updateDottore.getFieldCognome().setBackground(Color.white);
				updateDottore.getFieldNome().setBackground(Color.white);
				updateDottore.getFieldCodiceFiscale()
						.setBackground(Color.white);

				// RICARICO
				// i campi che mi servono cioè le generalità del Biologo
				// Nutrizionista loggato (la modifica è permessa solo su se
				// stessi)

				Vector<String> v = dbInterface.getInfoAboutDoctor(activeUser
						.getNickName());

				updateDottore.getFieldNickName().setText(v.get(0));
				updateDottore.getFieldPassword().setText(v.get(1));
				updateDottore.getFieldCognome().setText(v.get(2));
				updateDottore.getFieldNome().setText(v.get(3));
				updateDottore.getComboAnno().setSelectedItem(
						new String(((v.get(4)).substring(0, 4))));
				updateDottore.getComboMese().setSelectedItem(
						Giorni.getMeseFromNumber((((String) (v.get(4))
								.substring(5, 7)))));
				updateDottore.getComboGiorno().setSelectedItem(
						((String) (v.get(4)).substring(8, 10)));
				updateDottore.getFieldCodiceFiscale().setText(v.get(5));
				updateDottore.getFieldCellulare().setText(v.get(6));
				updateDottore.getFieldMail().setText(v.get(7));
				updateDottore.getFieldFax().setText(v.get(8));
				updateDottore.getFieldNickName().setBackground(Color.white);
				updateDottore.getFieldPassword().setBackground(Color.white);
				updateDottore.getFieldCognome().setBackground(Color.white);
				updateDottore.getFieldNome().setBackground(Color.white);
				updateDottore.getFieldCodiceFiscale()
						.setBackground(Color.white);

				// redo visibile la jinternalframe
				updateDottore.setVisible(true);

				// posizionamento della finestra al centro
				// della DESKTOPWINDOW
				updateDottore.setLocation(
						(desktopPane.getSize().width - updateDottore.getSize().width) / 2,
						(desktopPane.getSize().height - updateDottore.getSize().height) / 2);
			}
		});

		// in ascolto su quando viene cliccata la voce di modifica
		// Biologo nutrizionista del menu ANAGRAFICA BIOLOGO NUTRIZIONISTA
		itmCancellaDietologo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// disabilito la voce di logout dal menu file
				itemLogout.setEnabled(false);

				// AZZERO i campi della jinternalframe perchè all'ultimo acceso
				// essa è stata semplicemnte nascosta per velocizzare
				// l'applicazione
				deleteDottore.getCombNickName().removeAllItems();

				// implemento il controllo sulla presenza di almeno un dottore
				// altrimenti lancio la schermata di Attenzione!!!
				Vector<String> appoggio = dbInterface
						.getElencoNickName((String) deleteDottore
								.getComboProfilo().getSelectedItem());

				int numDottori = appoggio.size();
				if (numDottori != 0) { // se c'è almeno un dottore
					// RICARICO
					// i i campi che mi servono ciè la combo
					// con i nickName con profilo Biologo
					// Nutrizionista
					// (la cancellazione di un Biologo
					// nutrizionista è permessa solo ad un
					// Ammistrtore)

					for (int i = 0; i < numDottori; i++) {
						deleteDottore.getCombNickName().addItem(
								(appoggio).get(i));
					}// fine for

					// redo visibile la jinternalframe
					deleteDottore.setVisible(true);

					// posizionamento della finestra al centro
					// della DESKTOPWINDOW
					deleteDottore.setLocation(
							(desktopPane.getSize().width - deleteDottore
									.getSize().width) / 2,
							(desktopPane.getSize().height - deleteDottore
									.getSize().height) / 2);
				} // fine then
				else {
					allertWindow
							.aggiungiMessaggio("Non sono presenti Utenti con il profilo \n<"
									+ ((String) deleteDottore.getComboProfilo()
											.getSelectedItem()).toUpperCase()
									+ "> !!! \nCancellazione non ammessa...");
					allertWindow.setVisible(true);
				}

			}
		});

		mntmCreaVisita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// disabilito la voce di logout dal menu file
				itemLogout.setEnabled(false);

				// AZZERO i campi della jinternalframe perchè all'ultimo acceso
				// essa è stata semplicemnte nascosta per velocizzare
				// l'applicazione
				primaVisita.reset();

				// RICARICO
				//ricarico i pazienti nella combo
				Vector<String> appoggio = dbInterface.getNomiPazienti(dbInterface.getCodDottByNickName(activeUser.getNickName()));


				for (int i = 0; i < appoggio.size(); i++) {
						primaVisita.getComboPazienti().addItem(
								(appoggio).get(i));
					}// fine for
				
				
				
				//riabilito i pulsanti di scelta
				primaVisita.getBtnPrimaVisita().setEnabled(true);
				primaVisita.getBtnSuccessiveVisite().setEnabled(true);
				primaVisita.getBtnConfermaESalva().setEnabled(false);
				primaVisita.getPanelSuper().setVisible(false);
				primaVisita.getComboPazienti().setEnabled(false);

				// rendo visibile la JinternalFrame
				primaVisita.setVisible(true);

				// posizionamento della finestra al centro
				// della DESKTOPWINDOW
				primaVisita.setLocation(
						(desktopPane.getSize().width - primaVisita.getSize().width) / 2,
						(desktopPane.getSize().height - primaVisita.getSize().height) / 2);
			}
		});
		
		
		// pazienti------------------------------------------------------------------
		itmScegliPaziente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// disabilito la voce di logout dal menu file
				itemLogout.setEnabled(false);

				// AZZERO i campi della jinternalframe perchè all'ultimo acceso
				// essa è stata semplicemnte nascosta per velocizzare
				// l'applicazione

				// RICARICO
				// i campi che mi servono cioè le generalità del Biologo
				// Nutrizionista loggato (la modifica è permessa solo su se
				// stessi)
				scegliVisita.populateComboPazienti();

				// rendo visibile la jinternalframe
				scegliVisita.setVisible(true);

				// posizionamento della finestra al centro
				// della DESKTOPWINDOW
				scegliVisita.setLocation(
						(desktopPane.getSize().width - scegliVisita.getSize().width) / 2,
						(desktopPane.getSize().height - scegliVisita.getSize().height) / 2);
			}
		});

		
	
		
		// in ascolto su quando viene cliccata la voce di Creazione manuale
		// Dieta
		// del menu DIETA

		itmVisualizzaAttivitàSuDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// disabilito la voce di logout dal menu file
				itemLogout.setEnabled(false);

				// AZZERO i campi della jinternalframe perchè all'ultimo acceso
				// essa è stata semplicemnte nascosta per velocizzare
				// l'applicazione
				// ...
				// ...

				// RICARICO
				// prima di tutto la variabiel Vector<Vector<?>> che ospita il
				// risultato della query select * from LogSistema
				// poi ricarico (refresh della tabella)
                
				Vector<Vector<Object>> d = dbInterface.getMatriceByLog(); // matrice
																		// "vettore di vettore"
																		// che
																		// ospiterà
																		// i
																		// dati
				Vector<String> c = dbInterface.getColonneByLog(); // vettore che
																	// ospiterà
																	// le
																	// colenne
				logSistema.setTable(d, c);
				logSistema.getTable().repaint();
				 
				// settaggio margini tabella tabella
				
				logSistema.getTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					TableColumn column2 = logSistema.getTable().getColumnModel().getColumn(1);
				column2.setPreferredWidth(200);
				TableColumn column3 = logSistema.getTable().getColumnModel().getColumn(2);
				column3.setPreferredWidth(120);
				TableColumn column4= logSistema.getTable().getColumnModel().getColumn(3);
				column4.setPreferredWidth(120);;
	    		TableColumn column5 = logSistema.getTable().getColumnModel().getColumn(logSistema.getTable().getColumnCount()-1);
				column5.setPreferredWidth(logSistema.getScrollPane().getWidth());
				
				
				
				// rendo visibile la JinternalFrame
				logSistema.setVisible(true);

				// posizionamento della finestra al centro
				// della DESKTOPWINDOW
				logSistema.setLocation(
						(desktopPane.getSize().width - logSistema.getSize().width) / 2,
						(desktopPane.getSize().height - logSistema.getSize().height) / 2);
			}
		});

		itmCreaDietaManuale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// disabilito la voce di logout dal menu file
				itemLogout.setEnabled(false);

				// AZZERO i campi della jinternalframe perchè all'ultimo acceso
				// essa è stata semplicemnte nascosta per velocizzare
				// l'applicazione
				// ...
				// ...

				// RICARICO
				// prima di tutto la variabiel Vector<Vector<?>> che ospita il
				// risultato della query select * from LogSistema
				// poi ricarico (refresh della tabella)

				dietaParientChoose.populateComboPazienti();

				// rendo visibile la JinternalFrame
				dietaParientChoose.setVisible(true);

				// posizionamento della finestra al centro
				// della DESKTOPWINDOW
				dietaParientChoose.setLocation(
						(desktopPane.getSize().width - dietaParientChoose
								.getSize().width) / 2,
						(desktopPane.getSize().height - dietaParientChoose
								.getSize().height) / 2);
			}
		});

		itmInserisciPaziente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// disabilito la voce di logout dal menu file
				itemLogout.setEnabled(false);

				// AZZERO i campi della jinternalframe perchè all'ultimo acceso
				// essa è stata semplicemnte nascosta per velocizzare
				// l'applicazione
				insertPaziente.getFieldCodiceFiscale().setText("");
				insertPaziente.getFieldNome().setText("");
				insertPaziente.getFieldCognome().setText("");
				insertPaziente.getFieldMail().setText("");
				insertPaziente.getFieldPassword().setText("");
				insertPaziente.getRbtMaschio().setSelected(true);
				insertPaziente.getFieldNickName().setText("");
				insertPaziente.getImgCheckNickName().setVisible(false);
				insertPaziente.getComboAnno().setSelectedIndex(0);
				insertPaziente.getComboMese().setSelectedIndex(0);
				insertPaziente.getComboGiorno().setSelectedIndex(0);
				// sbianco i campi che per errore di compilazione potrebbero
				// essere stati segnalati verde
				insertPaziente.getFieldCodiceFiscale().setBackground(Color.WHITE);
				insertPaziente.getFieldNome().setBackground(Color.WHITE);
				insertPaziente.getFieldCognome().setBackground(Color.WHITE);
				insertPaziente.getFieldMail().setBackground(Color.WHITE);
				insertPaziente.getRbtMaschio().setBackground(getBackground());
				insertPaziente.getRbtFemmina().setBackground(getBackground());


				// popolamento automatico del campo cod dottore
				try {
					insertPaziente.getFieldCodiceDottore().setText(String.valueOf(dbInterface.getHandler()
							.getCodDottByNickNameOnDB(
									activeUser.getNickName())));
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				// rendo visibile la JinternalFrame
				insertPaziente.setVisible(true);

				// posizionamento della finestra al centro
				// della DESKTOPWINDOW
				insertPaziente.setLocation(
						(desktopPane.getSize().width - insertPaziente.getSize().width) / 2,
						(desktopPane.getSize().height - insertPaziente.getSize().height) / 2);

			}
		});

		itmModificaPaziente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// disabilito la voce di logout dal menu file
				itemLogout.setEnabled(false);

				// AZZERO i campi della jinternalframe perchè all'ultimo acceso
				// essa è stata semplicemnte nascosta per velocizzare
				// l'applicazione
				updatePaziente.getFieldCodiceFiscale().setText("");
				updatePaziente.getFieldNome().setText("");
				updatePaziente.getFieldCognome().setText("");
				updatePaziente.getFieldMail().setText("");
				updatePaziente.getFieldPassword().setText("");
				updatePaziente.getRbtMaschio().setSelected(true);
				updatePaziente.getFieldNickName().setText("");
				updatePaziente.getImgCheckNickName().setVisible(false);
				updatePaziente.getComboAnno().setSelectedIndex(0);
				updatePaziente.getComboMese().setSelectedIndex(0);
				updatePaziente.getComboGiorno().setSelectedIndex(0);

				// popolamento automatico del campo cod dottore
				try {
					updatePaziente.getFieldCodiceDottore().setText(String.valueOf(dbInterface.getHandler()
							.getCodDottByNickNameOnDB(
									activeUser.getNickName())));
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				// rendo visibile la JinternalFrame
				updatePaziente.setVisible(true);

				// posizionamento della finestra al centro
				// della DESKTOPWINDOW
				updatePaziente.setLocation(
						(desktopPane.getSize().width - updatePaziente.getSize().width) / 2,
						(desktopPane.getSize().height - updatePaziente.getSize().height) / 2);

			}
		});
		
		// ----------------fine lista listener---------------------------

	}// FINE DEL COSTRUTTORE

	// procedura che mi permette di azzerare il contesto
	public void logOutProcedure() {
		// aggiungo la lista di tutte i menu da disabilitare
		// i menù principali FILE, ANAGRAFICA, DIETA E LOG
		// non compaiono mai poichè per scelta architetturale sono sempre
		// ENABLED, ma sono blindati i propri sotto menu
		// in futuro se cambiassero le policy di sistema e si volesse
		// disattivarli addirittura sarebbe possibile farlo da qui
		// .....
		//

		smnDottDietologo.setEnabled(false);
		itmInserisciDietologo.setEnabled(false);
		itmModificaDietologo.setEnabled(false);
		itmCancellaDietologo.setEnabled(false);
		smnPaziente.setEnabled(false);
		itmInserisciPaziente.setEnabled(false);
		itmModificaPaziente.setEnabled(false);
		itmCancellaPaziente.setEnabled(false);
		smnAmministratoriSistema.setEnabled(false);
		itmInserisciAmministratore.setEnabled(false);
		itmModificaAmministratore.setEnabled(false);
		itmCancellaAmministratore.setEnabled(false);
		itmCreaDietaManuale.setEnabled(false);
		itmCreaDietaAutomatica.setEnabled(false);
		itmVisualizza.setEnabled(false);
		itmVisualizzaAttivitàSuDB.setEnabled(false);
		itmScegliPaziente.setEnabled(false);

		// -------------------------------------------------------
		itemLogout.setEnabled(false); // disabilitazione della voce di loguot
		itemLogin.setEnabled(true); // abilitazione della voce di login
		itemEsci.setEnabled(true); // abilitazione della voce Esci

		//scrittura sul log che l'utente si è sloggato
		
		try {
			dbInterface.getHandler().registraLogOutOnDB(activeUser.getLevel(), activeUser.getNickName(),"L'utente si è sloggato");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// azzeramento del MODEL
		activeUser.setLevel("");
		activeUser.setNickName("");

		// creazione schermata di log in quanto distrutta ogni volta che ci si
		// logga
		
		loginWindow.getFieldUsarName().setText("");
		loginWindow.getFieldPassword().setText("");
		
		loginWindow.getBtnSubmit().setEnabled(true);
		loginWindow.getImgLock().setVisible(true);
		
		
		
		loginWindow.getImgUnlock().setVisible(false);
		loginWindow.setVisible(true);
		loginWindow.getFieldUsarName().requestFocus();
//		loginWindow = new Login();
//		loginWindow.importaDesktopWindow(this);
//		desktopPane.add(loginWindow);
//		loginWindow.requestFocusInWindow();
//		loginWindow.setVisible(true);
		loginWindow
				.setLocation(
						(desktopPane.getSize().width - loginWindow.getSize().width) / 2,
						(desktopPane.getSize().height - loginWindow.getSize().height) / 2);
		// -------------------------------------------------------------

	}

	/**
	 * Questo metodo permette di Abilitare e disabilitare
	 * (setEnable(true/false)) le voci di menù in base ai Grant relativi al
	 * profilo di un utente che effettua un Login con successo
	 * 
	 * @param riceve
	 *            una HashMap composta da: => chiave: una stringa costituita dal
	 *            nome del menù a cui l'utente ha/non ha accesso => campo
	 *            booleano con tru/false a seconda se l'accesso a tale menù è
	 *            consentito o meno
	 */
	public void abilitaMenu(Map<String, Boolean> lista) {

		for (Map.Entry<String, Boolean> entry : lista.entrySet()) {
			switch (entry.getKey().toString()) {
			case "AnaDietologi":
				if (new Boolean(entry.getValue()) == true) {
					smnDottDietologo.setEnabled(true);
				}
				break;
			case "InsertDietologo":
				if (new Boolean(entry.getValue()) == true) {
					itmInserisciDietologo.setEnabled(true);
				}
				break;
			case "ModifyDietologo":
				if (new Boolean(entry.getValue()) == true) {
					itmModificaDietologo.setEnabled(true);
				}
				break;
			case "DeleteDietologo":
				if (new Boolean(entry.getValue()) == true) {
					itmCancellaDietologo.setEnabled(true);
				}
				break;

			case "AnaPazienti":
				if (entry.getValue() == true) {
					smnPaziente.setEnabled(true);
				}
				break;
			case "InsertPaziente":
				if (entry.getValue() == true) {
					itmInserisciPaziente.setEnabled(true);
				}
				break;
			case "ModifyPaziente":
				if (entry.getValue() == true) {
					itmModificaPaziente.setEnabled(true);
				}
				break;
			case "DeletePaziente":
				if (entry.getValue() == true) {
					itmCancellaPaziente.setEnabled(true);
				}
				break;

			case "AnaAmministratori":
				if (entry.getValue() == true) {
					smnAmministratoriSistema.setEnabled(true);
				}
				break;
			case "InsertAdmin":
				if (entry.getValue() == true) {
					itmInserisciAmministratore.setEnabled(true);
				}
				break;
			case "ModifyAdmin":
				if (entry.getValue() == true) {
					itmModificaAmministratore.setEnabled(true);
				}
				break;
			case "DeleteAdmin":
				if (entry.getValue() == true) {
					itmCancellaAmministratore.setEnabled(true);
				}
				break;
			case "CreaDietaManuale":
				if (entry.getValue() == true) {
					itmCreaDietaManuale.setEnabled(true);
				}
				break;
			case "CreaDietaAutomatica":
				if (entry.getValue() == true) {
					itmCreaDietaAutomatica.setEnabled(true);
				}
				break;

			case "VisualizzaDieta":
				if (entry.getValue() == true) {
					itmVisualizza.setEnabled(true);
				}
				break;

			case "Visita":
				if (entry.getValue() == true) {
					menuVisite.setEnabled(true);
					itmScegliPaziente.setEnabled(true);
				}
				break;

			case "LogSistema":
				if (entry.getValue() == true) {
					menuLog.setEnabled(true);
				}
				break;
			case "VisualizzaLog":
				if (entry.getValue() == true) {
					itmVisualizzaAttivitàSuDB.setEnabled(true);
				}
				break;

			default:
				break;
			}

		}// fine for
	}

	// procedura in cui si verifica se il flusso d'informazione deve andare
	// verso il primo accesso assoluto al programma (nessun amministratore
	// registrato) dopo l'installazione o verso
	// una fase di logIn successiva al primo accesso (con quindi almenon un
	// amministratore gia registrato)
	public void accessoPrimoOSuccessivo() {
		if (!(dbInterface.verifyFirstAccess())) { // non è il primo accesso

			loginWindow.setVisible(true);

			// posizionamento della finestra LOGIN al centro dellaDESKTOPWINDOW
			loginWindow
					.setLocation((desktopPane.getSize().width - loginWindow
							.getSize().width) / 2,
							(desktopPane.getSize().height - loginWindow
									.getSize().height) / 2);
		} else { // è il primo accesso

			//settiamo il modello ad una sorta di SUPERUSER che governa il primo accesso
			activeUser.setLevel("SueprUser - FirstAccess");
			activeUser.setNickName("SuperUser - FirstAccess");
			
			itemLogin.setEnabled(false);

			firstEnterWindow.setVisible(true);
			firstEnterWindow
					.setLocation(
							(desktopPane.getSize().width - firstEnterWindow
									.getSize().width) / 2, (desktopPane
									.getSize().height - firstEnterWindow
									.getSize().height) / 2);

		}

	}

	// metodo per disegnare la FORM
	private void drawGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				DesktopWindow.class.getResource("/icons/logo_24.png")));
		setResizable(false);
		setTitle("SmartDietGFC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 1300, 750);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ----------------------------------------------------------------
		// barra dei menù. Fa da contenitore a tutte le voci di menù. Esso è
		// aggiunto al contentPane
		menuBar = new JMenuBar();
		menuBar.setBorder(new LineBorder(Color.BLACK));
		menuBar.setBounds(0, 0, 1294, 44);
		menuBar.setBackground(SystemColor.info);
		contentPane.add(menuBar);
		// menu FILE.
		menuFile = new JMenu("File");
		menuFile.setMnemonic(KeyEvent.VK_F);
		menuFile.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 18));
		menuFile.setIcon(new ImageIcon(DesktopWindow.class
				.getResource("/icons/file_32.png")));
		menuBar.add(menuFile);

		itemEsci = new JMenuItem("Esci", KeyEvent.VK_E);
		itemEsci.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 16));

		itemLogin = new JMenuItem("Login");
		itemLogin.setMnemonic(KeyEvent.VK_I);
		itemLogin
				.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 16));
		itemLogin.setIcon(new ImageIcon(DesktopWindow.class
				.getResource("/icons/key_32.png")));
		menuFile.add(itemLogin);

		itemLogout = new JMenuItem("Logout");
		itemLogout.setEnabled(false);
		itemLogout.setMnemonic(KeyEvent.VK_O);
		itemLogout.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD,
				16));
		itemLogout.setIcon(new ImageIcon(DesktopWindow.class
				.getResource("/icons/logout_32.png")));
		menuFile.add(itemLogout);

		separator = new JSeparator();
		menuFile.add(separator);

		itemEsci.setIcon(new ImageIcon(DesktopWindow.class
				.getResource("/icons/exit_32.png")));
		menuFile.add(itemEsci);

		// menù ANAGRAFICHE
		menuAnagrafiche = new JMenu("Anagrafiche");
		menuAnagrafiche.setMnemonic(KeyEvent.VK_A);
		menuAnagrafiche.setIcon(new ImageIcon(DesktopWindow.class
				.getResource("/icons/anagrafiche_32.png")));
		menuAnagrafiche.setFont(new Font("Microsoft JhengHei UI Light",
				Font.BOLD, 18));
		menuBar.add(menuAnagrafiche);
		// menù ANAGRAFICHE -> Biologo nutizionista
		smnDottDietologo = new JMenu("Biologo Nutrizionista");
		smnDottDietologo.setMnemonic(KeyEvent.VK_B);
		smnDottDietologo.setEnabled(false);
		smnDottDietologo.setIcon(new ImageIcon(DesktopWindow.class
				.getResource("/icons/analista_32.png")));
		smnDottDietologo.setFont(new Font("Microsoft JhengHei UI Light",
				Font.BOLD, 16));
		menuAnagrafiche.add(smnDottDietologo);

		itmInserisciDietologo = new JMenuItem("Inserisci");
		itmInserisciDietologo.setMnemonic(KeyEvent.VK_I);
		itmInserisciDietologo.setEnabled(false);
		itmInserisciDietologo.setIcon(new ImageIcon(DesktopWindow.class
				.getResource("/icons/iud_insert_24.png")));
		itmInserisciDietologo.setFont(new Font("Microsoft JhengHei UI Light",
				Font.BOLD, 16));
		smnDottDietologo.add(itmInserisciDietologo);

		itmModificaDietologo = new JMenuItem("Modifica");
		itmModificaDietologo.setMnemonic(KeyEvent.VK_M);
		itmModificaDietologo.setEnabled(false);
		itmModificaDietologo.setIcon(new ImageIcon(DesktopWindow.class
				.getResource("/icons/iud_update_24.png")));
		itmModificaDietologo.setFont(new Font("Microsoft JhengHei UI Light",
				Font.BOLD, 16));
		smnDottDietologo.add(itmModificaDietologo);

		itmCancellaDietologo = new JMenuItem("Cancella");
		itmCancellaDietologo.setMnemonic(KeyEvent.VK_C);
		itmCancellaDietologo.setEnabled(false);
		itmCancellaDietologo.setIcon(new ImageIcon(DesktopWindow.class
				.getResource("/icons/iud_delete_24.png")));
		itmCancellaDietologo.setFont(new Font("Microsoft JhengHei UI Light",
				Font.BOLD, 16));
		smnDottDietologo.add(itmCancellaDietologo);

		// menù ANAGRAFICHE -> Paziente
		smnPaziente = new JMenu("Paziente");
		smnPaziente.setMnemonic(KeyEvent.VK_P);
		smnPaziente.setEnabled(false);
		smnPaziente.setIcon(new ImageIcon(DesktopWindow.class
				.getResource("/icons/profilo_utente_32.png")));
		smnPaziente.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD,
				16));
		menuAnagrafiche.add(smnPaziente);

		itmInserisciPaziente = new JMenuItem("Nuovo Paziente");
		itmInserisciPaziente.setMnemonic(KeyEvent.VK_N);
		itmInserisciPaziente.setEnabled(false);
		itmInserisciPaziente.setIcon(new ImageIcon(DesktopWindow.class
				.getResource("/icons/iud_insert_24.png")));
		itmInserisciPaziente.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		smnPaziente.add(itmInserisciPaziente);

		itmModificaPaziente = new JMenuItem("Modifica Paziente");
		itmModificaPaziente.setMnemonic(KeyEvent.VK_M);
		itmModificaPaziente.setEnabled(false);
		itmModificaPaziente.setIcon(new ImageIcon(DesktopWindow.class
				.getResource("/icons/iud_update_24.png")));
		itmModificaPaziente.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		smnPaziente.add(itmModificaPaziente);

		itmCancellaPaziente = new JMenuItem("Cancella Paziente");
		itmCancellaPaziente.setMnemonic(KeyEvent.VK_C);
		itmCancellaPaziente.setEnabled(false);
		itmCancellaPaziente.setIcon(new ImageIcon(DesktopWindow.class
				.getResource("/icons/iud_delete_24.png")));
		itmCancellaPaziente.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		smnPaziente.add(itmCancellaPaziente);

		separator_1 = new JSeparator();
		menuAnagrafiche.add(separator_1);

		// menù ANAGRAFICHE -> Amministratore
		smnAmministratoriSistema = new JMenu("Amministratore Sistema");
		smnAmministratoriSistema.setMnemonic(KeyEvent.VK_M);
		smnAmministratoriSistema.setEnabled(false);
		smnAmministratoriSistema.setIcon(new ImageIcon(DesktopWindow.class
				.getResource("/icons/vestizione_32.png")));
		smnAmministratoriSistema.setFont(new Font(
				"Microsoft JhengHei UI Light", Font.BOLD, 16));
		menuAnagrafiche.add(smnAmministratoriSistema);

		itmInserisciAmministratore = new JMenuItem("Inserisci");
		itmInserisciAmministratore.setMnemonic(KeyEvent.VK_I);
		itmInserisciAmministratore.setEnabled(false);
		itmInserisciAmministratore.setIcon(new ImageIcon(DesktopWindow.class
				.getResource("/icons/iud_insert_24.png")));
		itmInserisciAmministratore
				.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		smnAmministratoriSistema.add(itmInserisciAmministratore);

		itmModificaAmministratore = new JMenuItem("Modifica");
		itmModificaAmministratore.setMnemonic(KeyEvent.VK_M);
		itmModificaAmministratore.setEnabled(false);
		itmModificaAmministratore.setIcon(new ImageIcon(DesktopWindow.class
				.getResource("/icons/iud_update_24.png")));
		itmModificaAmministratore.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		smnAmministratoriSistema.add(itmModificaAmministratore);

		itmCancellaAmministratore = new JMenuItem("Cancella");
		itmCancellaAmministratore.setMnemonic(KeyEvent.VK_C);
		itmCancellaAmministratore.setEnabled(false);
		itmCancellaAmministratore.setIcon(new ImageIcon(DesktopWindow.class
				.getResource("/icons/iud_delete_24.png")));
		itmCancellaAmministratore.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		smnAmministratoriSistema.add(itmCancellaAmministratore);
		// menù DIETA
		menuDieta = new JMenu("Dieta");
		menuDieta.setIcon(new ImageIcon(DesktopWindow.class.getResource("/icons/tavolozza_32.png")));
		menuDieta
				.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 18));
		menuBar.add(menuDieta);

		itmCreaDietaManuale = new JMenuItem("Crea Dieta Manuale");
		itmCreaDietaManuale.setEnabled(false);
		itmCreaDietaManuale.setIcon(new ImageIcon(DesktopWindow.class
				.getResource("/icons/fire_32.png")));
		itmCreaDietaManuale.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		menuDieta.add(itmCreaDietaManuale);

		itmCreaDietaAutomatica = new JMenuItem("Crea Dieta Automatica");
		itmCreaDietaAutomatica.setEnabled(false);
		itmCreaDietaAutomatica.setIcon(new ImageIcon(DesktopWindow.class
				.getResource("/icons/cubo_rubik_32.png")));
		itmCreaDietaAutomatica.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		menuDieta.add(itmCreaDietaAutomatica);

		itmVisualizza = new JMenuItem("Visualizza");
		itmVisualizza.setEnabled(false);
		itmVisualizza.setIcon(new ImageIcon(DesktopWindow.class
				.getResource("/icons/eye_vedi_tutte_32.png")));
		itmVisualizza.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		menuDieta.add(itmVisualizza);

		menuVisite = new JMenu("Visite");
		menuVisite.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD,
				18));
		menuVisite.setIcon(new ImageIcon(DesktopWindow.class.getResource("/icons/libro_aperto_32.png")));
		menuBar.add(menuVisite);
		
		mntmCreaVisita = new JMenuItem("Crea Visita");
		mntmCreaVisita.setIcon(new ImageIcon(DesktopWindow.class.getResource("/icons/calendario_insert_32.png")));
		mntmCreaVisita.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 18));
		menuVisite.add(mntmCreaVisita);

		itmScegliPaziente = new JMenuItem("Visualizza / Modifica Visite");
		itmScegliPaziente.setEnabled(false);
		itmScegliPaziente.setIcon(new ImageIcon(DesktopWindow.class.getResource("/icons/monitor_32.png")));
		itmScegliPaziente.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		menuVisite.add(itmScegliPaziente);

		// menù LOG su DB
		menuLog = new JMenu("Log");
		menuLog.setIcon(new ImageIcon(DesktopWindow.class
				.getResource("/icons/semaforo_verde_32.png")));
		menuLog.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 18));
		menuBar.add(menuLog);

		itmVisualizzaAttivitàSuDB = new JMenuItem(
				"Visualizza Attivit\u00E0 su DB");
		itmVisualizzaAttivitàSuDB.setEnabled(false);
		itmVisualizzaAttivitàSuDB.setIcon(new ImageIcon(DesktopWindow.class
				.getResource("/icons/microscopio_32.png")));
		itmVisualizzaAttivitàSuDB.setFont(new Font(
				"Microsoft JhengHei UI Light", Font.BOLD, 16));
		menuLog.add(itmVisualizzaAttivitàSuDB);

		// ------------------------------------------------------------------------------------
		// instanziazione dela pannello generale desktopPane che farà da
		// confine a tutte le JInternalFrame. Esso è aggiunto al contentPane
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 44, 1294, 652);
		contentPane.add(desktopPane);

		// aggiunta al desktopane di tutti le internal
		// frame instanziate all'inisio del
		// costruttore----------------------------------
		desktopPane.add(firstEnterWindow);
		desktopPane.add(loginWindow);
		desktopPane.add(insertAmministratore);
		desktopPane.add(updateAmministratore);
		desktopPane.add(deleteAmministratore);
		desktopPane.add(insertDottore);
		desktopPane.add(updateDottore);
		desktopPane.add(deleteDottore);
		desktopPane.add(scegliVisita);
		desktopPane.add(dietaParientChoose);
		desktopPane.add(primaVisita);
		desktopPane.add(visualizzaVisita);
		desktopPane.add(logSistema);
		desktopPane.add(insertPaziente);
		desktopPane.add(updatePaziente);
		
		
		
		// -----------------------------------------------------------------------------------

		// aggiunta dello sfondo al DESKTOPPANE che altro non è che un immagine
		// Lilla con il logo in basso a destra
		imageLabel = new JLabel("");
		imageLabel.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		desktopPane.add(imageLabel);
		imageLabel.setBounds(0, 0, 1294, 652);
		imageLabel.setIcon(new ImageIcon(DesktopWindow.class
				.getResource("/icons/sfondo_desktop.png")));

		// aggiunta delle 3 barre orizzontali in fondo al desktoppane.
		// Conterranno: Username, Profilo e Messaggi d'errore
		// -------usarname
		panelSx = new JPanel();
		panelSx.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelSx.setBackground(SystemColor.info);
		panelSx.setBounds(0, 696, 209, 25);
		contentPane.add(panelSx);
		panelSx.setLayout(null);

		lblUsers = new JLabel("");
		lblUsers.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsers.setBounds(61, 0, 148, 25);
		panelSx.add(lblUsers);

		labelSX = new JLabel("Utente:");
		labelSX.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelSX.setBounds(5, 0, 58, 25);
		panelSx.add(labelSX);

		// ---------messaggi d'errore
		panelCenter = new JPanel();
		panelCenter.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCenter.setBackground(SystemColor.info);
		panelCenter.setBounds(210, 696, 874, 25);
		contentPane.add(panelCenter);

		// ---------profilo
		panelDx = new JPanel();
		panelDx.setLayout(null);
		panelDx.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelDx.setBackground(SystemColor.info);
		panelDx.setBounds(1085, 696, 209, 25);
		contentPane.add(panelDx);

		lblProfile = new JLabel("");
		lblProfile.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProfile.setBounds(58, 0, 151, 25);
		panelDx.add(lblProfile);

		labelDX = new JLabel("Profilo:");
		labelDX.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelDX.setBounds(5, 0, 58, 25);
		panelDx.add(labelDX);

		// posizionamento della finestra princiale CENTRATA RISPETTO ALLO
		// SCHERMO
		dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(new Point((dimension.width - this.getSize().width) / 2,
				(dimension.height - this.getSize().height) / 2));
		// ---------------------------------------------------------------------------------------------

	}

	// get delle internal
	// frame---------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	public UpdateAmministratore getUpdateAmministratore() {
		return updateAmministratore;
	}

	public InsertAmministratore getInsertAmministratore() {
		return insertAmministratore;
	}

	public DeleteAmministratore getDeleteAmministratore() {
		return deleteAmministratore;
	}

	// -------------------------------------------------------------------------

	public InsertDottore getInsertDottore() {
		return insertDottore;
	}

	public UpdateDottore getUpdateDottore() {
		return updateDottore;
	}

	public DeleteDottore getDeleteDottore() {
		return deleteDottore;
	}

	// -------------------------------------------------------------------------

	public Login getLoginWindow() {
		return loginWindow;
	}

	public FirstEnter getFirstEnterWindow() {
		return firstEnterWindow;
	}
	
	// -------------------------------------------------------------------------
	
	public PrimaVisita getPrimaVisitaWindow(){
		return primaVisita;
	}

	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------

	// porto dietro sola una DBINTERFACE e una sola AlertWindow

	public AllertWindow getAllertWindow() {
		return allertWindow;
	}

	public DbInterface getDbInterface() {
		return dbInterface;
	}

	// ------------------------------------------------------

	// metodi GET dei vari oggetti grafici che gravitano nella FRAME
	public JMenuItem getItemLogin() {
		return itemLogin;
	}

	public JMenuItem getItemLogout() {
		return itemLogout;
	}
	
	public JMenuItem getItemEsci(){
		return itemEsci;
	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	public JMenu getMenuFile() {
		return menuFile;
	}

	public JLabel getLblUsers() {
		return lblUsers;
	}

	public JLabel getLblProfile() {
		return lblProfile;
	}

	public JMenu getSmnDottDietologo() {
		return smnDottDietologo;
	}

	public JMenu getSmnAmministratoriSistema() {
		return smnAmministratoriSistema;
	}

	public JMenu getSmnPaziente() {
		return smnPaziente;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public String getProfilo() {
		return profilo;
	}

	public void setProfilo(String profilo) {
		this.profilo = profilo;
	}

	public ActiveUser getActiveUser() {
		return activeUser;
	}

	// metodo che serve per implementare OSSERVABLE-OBSERVER
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

		Message msg = (Message) arg;
		lblUsers.setText(msg.getNickName());
		lblProfile.setText(msg.getLevel());

	}
}

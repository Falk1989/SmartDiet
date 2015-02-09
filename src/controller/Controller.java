package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.GregorianCalendar;
import java.util.Stack;

import javax.swing.JOptionPane;

import enumerativi.Giorni;
import view.DesktopWindow;
import view.UpdateAmministratore;
import model.ActiveUser;

public class Controller {

	private ActiveUser model;
	private DesktopWindow view;

	public Controller(ActiveUser model, DesktopWindow view) {
		this.model = model;
		this.view = view;

		// listener sulla registrazione
		// dell'InsertAmmnistratore-------------------
		this.view.getInsertAmministratore().AddMyInsertAmministratoreListener(
				new MyInsertAmministratoreListener());
		this.view.getInsertAmministratore()
				.AddMyInsertAmministratoreListenerKey(
						new MyInsertAmministratoreListenerKey());

		// listener sulla registrazione dell'UpdateAmmnistratore
		this.view.getUpdateAmministratore().AddMyUpdateAmministratoreListener(
				new MyUpdateAmministratoreListener());
		this.view.getUpdateAmministratore()
				.AddMyUpdateAmministratoreListenerKey(
						new MyUpdateAmministratoreListenerKey());

		// listener sulla registrazione dell'DeleteAmmnistratore
		this.view.getDeleteAmministratore().AddMyDeleteAmministratoreListener(
				new MyDeleteAmministratoreListener());
		this.view.getDeleteAmministratore()
				.AddMyDeleteAmministratoreListenerKey(
						new MyDeleteAmministratoreListenerKey());
		// -------------------------------------------------------------------------

		// listener sulla registrazione dell'InsertDottore
		this.view.getInsertDottore().AddMyInsertDottoreListener(
				new MyInsertDottoreListener());
		this.view.getInsertDottore().AddMyInsertDottoreListenerKey(
				new MyInsertDottoreListenerKey());

		// listener sulla registrazione dell'UpdateDottore
		this.view.getUpdateDottore().AddMyUpdateDottoreListener(
				new MyUpdateDottoreListener());
		this.view.getUpdateDottore().AddMyUpdateDottoreListenerKey(
				new MyUpdateDottoreListenerKey());

		// listener sulla registrazione dell'DeleteAmmnistratore
		this.view.getDeleteDottore().AddMyDeleteDottoreListener(
				new MyDeleteDottoreListener());
		this.view.getDeleteDottore().AddMyDeleteDottoreListenerKey(
				new MyDeleteDottoreListenerKey());
		// --------------------------------------------------------------------------
		// --------------------------------------------------------------------------
		// listener sulla registrazione del LOGIN
		this.view.getLoginWindow().AddMyLoginListener(new MyLoginListener());
		this.view.getLoginWindow().AddMyLoginListenerKey(
				new MyLoginListenerKey());
		// -------------------------------------------------------------------------
		// listener sulla registrazione del FirstEnter
		this.view.getFirstEnterWindow().AddMyFirstEnterListener(
				new MyFirstEnterListener());
		this.view.getFirstEnterWindow().AddMyFirstEnterListenerKey(
				new MyFirstEnterListenerKey());
		// -------------------------------------------------------------------------
		// listener sull'inserimento di una nuova visita
		this.view.getPrimaVisitaWindow().AddSexMCheckListener(
				new MySexMListener());
		this.view.getPrimaVisitaWindow().AddSexFCheckListener(
				new MySexFListener());
		this.view.getPrimaVisitaWindow().AddAttivit‡BassaListener(
				new MyAttivit‡BassaListener());
		this.view.getPrimaVisitaWindow().AddAttivit‡MediaListener(
				new MyAttivit‡MediaListener());
		this.view.getPrimaVisitaWindow().AddAttivit‡AltaListener(
				new MyAttivit‡AltaListener());
		this.view.getPrimaVisitaWindow().AddEt‡Listener(new MyEt‡Listener());
		this.view.getPrimaVisitaWindow().AddPesoListener(new MyPesoListener());
		this.view.getPrimaVisitaWindow().AddAltezzaListener(
				new MyAltezzaListener());
		this.view.getPrimaVisitaWindow().AddCircVitaListener(
				new MyCircVitaListener());
		this.view.getPrimaVisitaWindow().AddCircFianchiListener(
				new MyCircFianchiListener());
		this.view.getPrimaVisitaWindow()
				.AddbtnConfermaESalvaPrimaVisitaListener(
						new MyConfermaESalvaVisitaListener());
		// .....
		// .......

	}

	// per ogni listner attaccato creo una classe che estende il tipo di
	// listener

	class MyUpdateAmministratoreListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			gestoreBottoneUpdateAmministratore();

		}
	}

	class MyUpdateAmministratoreListenerKey implements KeyListener {

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
				gestoreBottoneUpdateAmministratore();

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

	// ----------------------------------------------------------------------

	class MyInsertAmministratoreListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			gestoreBottoneInsertAmministratore();

		}
	}

	class MyInsertAmministratoreListenerKey implements KeyListener {

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
				gestoreBottoneInsertAmministratore();

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

	// ---------------------------------------------------------------------------

	class MyInsertDottoreListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			gestoreBottoneInsertDottore();

		}
	}

	class MyInsertDottoreListenerKey implements KeyListener {

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
				gestoreBottoneInsertDottore();

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

	// ---------------------------------------------------------------------------

	class MyDeleteAmministratoreListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			gestoreBottoneDeleteAmministratore();

		}
	}

	class MyDeleteAmministratoreListenerKey implements KeyListener {

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
				gestoreBottoneDeleteAmministratore();

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

	// ---------------------------------------------------------------------------

	class MyUpdateDottoreListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			gestoreBottoneUpdateDottore();

		}
	}

	class MyUpdateDottoreListenerKey implements KeyListener {

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
				gestoreBottoneUpdateDottore();

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

	// ----------------------------------------------------------------------

	class MyDeleteDottoreListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			gestoreBottoneDeleteDottore();

		}
	}

	class MyDeleteDottoreListenerKey implements KeyListener {

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
				gestoreBottoneDeleteDottore();

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

	// ----------------------------------------------------------------------
	// LOGIN
	// ----------------------------------------------------------------------

	class MyLoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			confermaLogin();

		}
	}

	class MyLoginListenerKey implements KeyListener {

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
				confermaLogin();

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

	// ----------------------------------------------------------------------
	// FIRSTENTER
	// ----------------------------------------------------------------------

	class MyFirstEnterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			confermaFirstEnter();

		}
	}

	class MyFirstEnterListenerKey implements KeyListener {

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
				confermaFirstEnter();

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

	// ----------------------------------------------------------------------
	// PRIMA VISITA
	// ----------------------------------------------------------------------
	class MySexMListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			gestoreSexM();

		}
	}

	class MySexFListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			gestoreSexF();

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	}

	class MyAttivit‡BassaListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			gestoreAttivit‡Bassa();

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	}

	class MyAttivit‡MediaListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			gestoreAttivit‡Media();
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			gestoreSexF();

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	}

	class MyAttivit‡AltaListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			gestoreAttivit‡Alta();

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	}

	class MyEt‡Listener implements FocusListener {

		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			gestoreControlloEt‡();
		}

	}

	class MyPesoListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			gestoreControlloPeso();
		}

	}

	class MyAltezzaListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			gestoreControlloAltezza();
		}

	}

	class MyCircVitaListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			gestoreControlloCircVita();
		}

	}

	class MyCircFianchiListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			gestoreControlloCircFianchi();
		}

	}

	class MyConfermaESalvaVisitaListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			ConfermaESalvaVisitaListener();

		}
	}

	// ----------------------------------------------------------------------

	// implementazione delle azioni che devono essere svolte una volta che
	// l'evento Ë intercettato

	private void gestoreBottoneUpdateAmministratore() {

		boolean esitoScrittura;
		if (!(view.getUpdateAmministratore().getFieldNewNickName().getText()
				.equals(""))
				&& (!(new String(view.getUpdateAmministratore()
						.getFieldNewPassword().getPassword()).equals("")))) // se
		// entrambi
		// sono stati
		// caricati con
		// qualcosa
		// diverso da
		// vuoto
		{
			// inizia la procedura di modifica su DB
			esitoScrittura = view.getDbInterface().modificaAdmin(
					(String) view.getUpdateAmministratore().getCombNickName().getSelectedItem(),
					view.getUpdateAmministratore().getFieldNewNickName().getText(),
					new String(view.getUpdateAmministratore().getFieldNewPassword().getPassword()));
			
			if (esitoScrittura == true) {
				if (((String) view.getUpdateAmministratore().getCombNickName()
						.getSelectedItem()).equals(view.getActiveUser()
						.getNickName())
						&& (!((String) view.getUpdateAmministratore()
								.getCombNickName().getSelectedItem())
								.equals(view.getUpdateAmministratore()
										.getFieldNewNickName().getText()))) {
					// modifica effettiva del MODEL
					view.getActiveUser().setNickName(
							view.getUpdateAmministratore()
									.getFieldNewNickName().getText());
				}
				view.getUpdateAmministratore().hide();
			}
		} else {
			view.getUpdateAmministratore()
					.getFieldNewNickName()
					.setText(
							(String) view.getUpdateAmministratore()
									.getCombNickName().getSelectedItem());
			view.getUpdateAmministratore().getFieldNewPassword().setText("");
			view.getAllertWindow()
					.aggiungiMessaggio(
							"Uno dei campi: \n<New NickName> o <New Password> \nnon Ë stato compilato...");
			view.getAllertWindow().setVisible(true);

		}

	}

	private void gestoreBottoneInsertAmministratore() {
		boolean esitoScrittura;
		if (!(view.getInsertAmministratore().getFieldNickName().getText()
				.equals(""))
				&& (!(new String(view.getInsertAmministratore()
						.getFieldPassword().getPassword()).equals("")))) // se
		// entrambi
		// sono stati
		// caricati con
		// qualcosa
		// diverso da
		// vuoto
		{
			esitoScrittura = view.getDbInterface()
					.registraCredenziali(
							(String) view.getInsertAmministratore()
									.getComboProfilo().getSelectedItem(),
							view.getInsertAmministratore().getFieldNickName()
									.getText(),
							new String(view.getInsertAmministratore()
									.getFieldPassword().getPassword()));
			if (esitoScrittura == true)
				view.getInsertAmministratore().hide();
			else {
				view.getInsertAmministratore().getFieldNickName().setText("");
				view.getInsertAmministratore().getFieldPassword().setText("");
				view.getInsertAmministratore().getFieldNickName()
						.requestFocus();
				view.getAllertWindow()
						.aggiungiMessaggio(
								"Caricamento credenziali NON andato a buon fine!!! /nRipetere l'azione...");
				view.getAllertWindow().setVisible(true);
			}
		} else {
			view.getInsertAmministratore().getFieldNickName().setText("");
			view.getInsertAmministratore().getFieldPassword().setText("");
		}

	}

	private void gestoreBottoneDeleteAmministratore() {
		boolean esitoScrittura;
		// inizia la procedura di cancellazione su DB
		// view.getDeleteAmministratore().getCombNickName().removeAllItems();
		// for (int i = 0; i < view.getDbInterface().getElencoNickName(
		// view.getActiveUser().getLevel()).size(); i++) {
		// view.getDeleteAmministratore().getCombNickName().addItem(
		// view.getDbInterface().getElencoNickName(
		// view.getActiveUser().getLevel()).get(i));
		// }

		if (!(view.getDeleteAmministratore().getCombNickName().getItemCount() == 1)) // ci
		// sono
		// diversi
		// amministratori nel sistema (non Ë l'unico utente)
		{

			if (((String) view.getDeleteAmministratore().getCombNickName()
					.getSelectedItem()).equals(view.getActiveUser()
					.getNickName())) // sta
			// cancellando
			// se
			// stesso
			{
				Object[] options = { "OK", "NO" };
				int answer = JOptionPane
						.showOptionDialog(null,
								"Stai cancellando te stesso.\nSei sicuro???",
								"Attenzione!!!", JOptionPane.PLAIN_MESSAGE,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[1]);
				if (answer == 0) // ok confermo che voglio cancellarmi
				{
					esitoScrittura = view.getDbInterface().cancellaAdmin(
							(String) view.getDeleteAmministratore()
									.getCombNickName().getSelectedItem());
					if (esitoScrittura == true) {

						view.getDeleteAmministratore().hide();
						view.logOutProcedure();

						// viene effettuata la modifica al MODEL
						// ----------------------------------------
						view.getActiveUser().setNickName("");
						view.getActiveUser().setLevel("");
						// ---------------------------------------

					}
				} else
					// non voglio cancellarmi
					view.getDeleteAmministratore().hide();
			} else// non sta cancellando se stesso ma altri
					// amministratori
			{
				esitoScrittura = view.getDbInterface().cancellaAdmin(
						(String) view.getDeleteAmministratore()
								.getCombNickName().getSelectedItem());
				if (esitoScrittura == true)
					view.getDeleteAmministratore().hide();
			}

		} else// c'Ë un solo utente Amministratore nel sistema
		{
			view.getAllertWindow().aggiungiMessaggio(
					"Cancellazione Utente NON autorizzata!!! \nUtente: <"
							+ ((String) view.getDeleteAmministratore()
									.getCombNickName().getSelectedItem())
							+ "> Ë l'unico Amministratore di sistema!!!");
			view.getAllertWindow().setVisible(true);

		}
	}

	private void gestoreBottoneInsertDottore() {
		boolean esitoScrittura;
		if (!(view.getInsertDottore().getFieldNickName().getText().equals(""))
				&& (!(new String(view.getInsertDottore().getFieldPassword()
						.getPassword()).equals("")))
				&& (!(new String(view.getInsertDottore().getFieldCognome()
						.getText()).equals("")))
				&& (!(new String(view.getInsertDottore().getFieldNome()
						.getText()).equals("")))
				&& (!(new String(view.getInsertDottore()
						.getFieldCodiceFiscale().getText()).equals("")))) // se
		// entrambi
		// sono stati
		// caricati con
		// qualcosa
		// diverso da
		// vuoto
		{

			esitoScrittura = view.getDbInterface().registraDottore(
					view.getActiveUser().getLevel(),
					view.getActiveUser().getNickName(),
					(String) view.getInsertDottore().getComboProfilo()
							.getSelectedItem(),
					view.getInsertDottore().getFieldNickName().getText(),
					(new String(view.getInsertDottore().getFieldPassword()
							.getPassword())),
					view.getInsertDottore().getFieldCognome().getText(),
					view.getInsertDottore().getFieldNome().getText(),
					Giorni.castDate(new GregorianCalendar(Integer
							.parseInt((String) view.getInsertDottore()
									.getComboAnno().getSelectedItem()),
							(((Giorni) view.getInsertDottore().getComboMese()
									.getSelectedItem()).getNumero()),
							Integer.parseInt((String) view.getInsertDottore()
									.getComboGiorno().getSelectedItem()))),
					view.getInsertDottore().getFieldCodiceFiscale().getText(),
					view.getInsertDottore().getFieldCellulare().getText(),
					view.getInsertDottore().getFieldMail().getText(),
					view.getInsertDottore().getFieldFax().getText());
			if (esitoScrittura == true)
				view.getInsertDottore().hide();
			else {
				view.getInsertDottore().getFieldNickName().setText("");
				view.getInsertDottore().getFieldPassword().setText("");
				view.getInsertDottore().getFieldCognome().setText("");
				view.getInsertDottore().getFieldNome().setText("");
				view.getInsertDottore().getFieldCodiceFiscale().setText("");
				view.getInsertDottore().getFieldCellulare().setText("");
				view.getInsertDottore().getFieldMail().setText("");
				view.getInsertDottore().getFieldFax().setText("");

				view.getInsertDottore().getFieldNickName().requestFocus();
				view.getAllertWindow()
						.aggiungiMessaggio(
								"Caricamento credenziali NON andato a buon fine!!! /nRipetere l'azione...");
				view.getAllertWindow().setVisible(true);
			}
		} else {
			view.getInsertDottore().getFieldNickName()
					.setBackground(Color.GREEN);
			view.getInsertDottore().getFieldPassword()
					.setBackground(Color.GREEN);
			view.getInsertDottore().getFieldCognome()
					.setBackground(Color.GREEN);
			view.getInsertDottore().getFieldNome().setBackground(Color.GREEN);
			view.getInsertDottore().getFieldCodiceFiscale()
					.setBackground(Color.GREEN);
			Object[] options = { "OK" };
			JOptionPane
					.showOptionDialog(
							null,
							"Uno dei campi OBBLIGATORI evvidenziati in verde risulta essere VUOTO",
							"Attenzione!!!", JOptionPane.PLAIN_MESSAGE,
							JOptionPane.INFORMATION_MESSAGE, null, options,
							options[0]);

		}
	}

	private void gestoreBottoneUpdateDottore() {
		boolean esitoScrittura;
		if (!(view.getUpdateDottore().getFieldNickName().getText().equals(""))
				&& (!(new String(view.getUpdateDottore().getFieldPassword()
						.getPassword()).equals("")))
				&& (!(new String(view.getUpdateDottore().getFieldCognome()
						.getText()).equals("")))
				&& (!(new String(view.getUpdateDottore().getFieldNome()
						.getText()).equals("")))
				&& (!(new String(view.getUpdateDottore()
						.getFieldCodiceFiscale().getText()).equals("")))) // se
		// entrambi
		// sono stati
		// caricati con
		// qualcosa
		// diverso da
		// vuoto
		{

			esitoScrittura = view.getDbInterface().modificaDottore(
					(String) view.getUpdateDottore().getComboProfilo()
							.getSelectedItem(),
					view.getActiveUser().getNickName(),
					view.getUpdateDottore().getFieldNickName().getText(),
					(new String(view.getUpdateDottore().getFieldPassword()
							.getPassword())),
					view.getUpdateDottore().getFieldCognome().getText(),
					view.getUpdateDottore().getFieldNome().getText(),
					Giorni.castDate(new GregorianCalendar(Integer
							.parseInt((String) view.getUpdateDottore()
									.getComboAnno().getSelectedItem()),
							(((Giorni) view.getUpdateDottore().getComboMese()
									.getSelectedItem()).getNumero()),
							Integer.parseInt((String) view.getUpdateDottore()
									.getComboGiorno().getSelectedItem()))),
					view.getUpdateDottore().getFieldCodiceFiscale().getText(),
					view.getUpdateDottore().getFieldCellulare().getText(),
					view.getUpdateDottore().getFieldMail().getText(),
					view.getUpdateDottore().getFieldFax().getText(),
					view.getDbInterface().getCodDottByNickName(
							view.getActiveUser().getNickName()));
			if (esitoScrittura == true) {
				// modifica effettiva del MODEL
				view.getActiveUser().setNickName(
						view.getUpdateDottore().getFieldNickName().getText());
				view.getUpdateDottore().hide();
			} else {
				view.getUpdateDottore().getFieldNickName().setText("");
				view.getUpdateDottore().getFieldPassword().setText("");
				view.getUpdateDottore().getFieldCognome().setText("");
				view.getUpdateDottore().getFieldNome().setText("");
				view.getUpdateDottore().getFieldCodiceFiscale().setText("");
				view.getUpdateDottore().getComboAnno().setSelectedIndex(0);
				view.getUpdateDottore().getComboMese().setSelectedIndex(0);
				view.getUpdateDottore().getComboGiorno().setSelectedIndex(0);
				view.getUpdateDottore().getFieldCellulare().setText("");
				view.getUpdateDottore().getFieldMail().setText("");
				view.getUpdateDottore().getFieldFax().setText("");

				view.getUpdateDottore().getFieldNickName().requestFocus();
				view.getAllertWindow()
						.aggiungiMessaggio(
								"Caricamento credenziali NON andato a buon fine!!! /nRipetere l'azione...");
				view.getAllertWindow().setVisible(true);
			}
		} else {
			view.getUpdateDottore().getFieldNickName()
					.setBackground(Color.GREEN);
			view.getUpdateDottore().getFieldPassword()
					.setBackground(Color.GREEN);
			view.getUpdateDottore().getFieldCognome()
					.setBackground(Color.GREEN);
			view.getUpdateDottore().getFieldNome().setBackground(Color.GREEN);
			view.getUpdateDottore().getFieldCodiceFiscale()
					.setBackground(Color.GREEN);
			Object[] options = { "OK" };
			JOptionPane
					.showOptionDialog(
							null,
							"Uno dei campi OBBLIGATORI evvidenziati in verde risulta essere VUOTO",
							"Attenzione!!!", JOptionPane.PLAIN_MESSAGE,
							JOptionPane.INFORMATION_MESSAGE, null, options,
							options[0]);

		}

	}

	private void gestoreBottoneDeleteDottore() {
		boolean esitoScrittura;
		// inizia la procedura di cancellazione su DB

		esitoScrittura = view.getDbInterface().cancellaDottore(
				(String) view.getDeleteDottore().getCombNickName()
						.getSelectedItem());

		if (esitoScrittura == true)
			view.getDeleteDottore().hide();

	}

	private void confermaLogin() {
		if (!(view.getLoginWindow().getFieldUsarName().getText().equals(""))
				&& (!(new String(view.getLoginWindow().getFieldPassword()
						.getPassword()).equals("")))) {
			if (view.getDbInterface().verifyLogin(
					view.getLoginWindow().getFieldUsarName().getText(),
					new String(view.getLoginWindow().getFieldPassword()
							.getPassword()))) {

				view.getItemLogin().setEnabled(false);
				view.getItemLogout().setEnabled(true);
				// caricamento in maschera di User e Profilo
				// desktopWindow.getLblUsers().setText(fieldUsarName.getText());
				// desktopWindow.getLblProfile().setText(dbInterface.getProfileByNickname(fieldUsarName
				// .getText()));
				// desktopWindow.setUtente(desktopWindow.getLblUsers().getText());
				// desktopWindow.setProfilo(desktopWindow.getLblProfile().getText());

				// MODIFICO SOLO IL
				// MODEL----------------------------------------------
				view.getActiveUser().setLevel(
						view.getDbInterface().getProfileByNickname(
								view.getLoginWindow().getFieldUsarName()
										.getText()));
				view.getActiveUser().setNickName(
						view.getLoginWindow().getFieldUsarName().getText());
				// -------------------------------------------------------------------------
				view.getLoginWindow().getBtnSubmit().setEnabled(false);

				// abilitazioni dei men˘ confacenti al tipo di profilo

				view.abilitaMenu(view.getDbInterface()
						.getUserVisibilityByLevel(
								view.getDbInterface().getProfileByNickname(
										view.getLoginWindow()
												.getFieldUsarName().getText())));

				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub

						view.getLoginWindow().getImgLock().setVisible(false);
						view.getLoginWindow().getImgUnlock().setVisible(true);

						// TODO Auto-generated method stub

						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						view.getLoginWindow().dispose();

					}
				}).start();

			} else {
				view.getLoginWindow().getFieldUsarName().requestFocus();
				view.getAllertWindow().setVisible(true);
				view.getAllertWindow()
						.aggiungiMessaggio(
								"Credenziali NON valide!!! \nUsername o Password ERRATE!!! \nVerificare ed in caso contattale l'amministratore di sistema...");
				view.getLoginWindow().getFieldUsarName().setText("");
				view.getLoginWindow().getFieldPassword().setText("");

				view.getAllertWindow().setLocation(
						(view.getSize().width - view.getAllertWindow()
								.getSize().width) / 2,
						(view.getSize().height - view.getAllertWindow()
								.getSize().height) / 2);

			}
		} else
			view.getLoginWindow().getFieldUsarName().requestFocus();
	}

	private void confermaFirstEnter() {
		boolean esitoScrittura;
		if (!(view.getFirstEnterWindow().getFieldUsarName().getText()
				.equals(""))
				&& (!(new String(view.getFirstEnterWindow().getFieldPassword()
						.getPassword()).equals("")))) // se entrambi
		// sono stati
		// caricati con
		// qualcosa
		// diverso da
		// vuoto
		{
			// inizia la procedura di caricamento su DB
			esitoScrittura = view.getDbInterface().registraCredenziali(
					(String) view.getFirstEnterWindow().getComboProfilo()
							.getSelectedItem(),
					view.getFirstEnterWindow().getFieldUsarName().getText(),
					new String(view.getFirstEnterWindow().getFieldPassword()
							.getPassword()));
			if (esitoScrittura == true) {
				// modifica effettiva del MODEL
				view.getActiveUser()
						.setNickName(
								view.getFirstEnterWindow().getFieldUsarName()
										.getText());
				view.getActiveUser().setLevel(
						(String) view.getFirstEnterWindow().getComboProfilo()
								.getSelectedItem());
				// ------------------------------------------------------------------------
				view.getItemLogout().setEnabled(true);
				view.getItemLogin().setEnabled(false);
				view.abilitaMenu(view.getDbInterface()
						.getUserVisibilityByLevel(
								view.getDbInterface().getProfileByNickname(
										view.getFirstEnterWindow()
												.getFieldUsarName().getText())));
				view.getFirstEnterWindow().dispose();
			}
		} else {
			view.getFirstEnterWindow().getFieldUsarName().setText("");
			view.getFirstEnterWindow().getFieldPassword().setText("");
			view.getFirstEnterWindow().getFieldUsarName().requestFocus();
		}
	}

	private void gestoreSexM() {

		if (view.getPrimaVisitaWindow().getSexM().isSelected()) {
			view.getPrimaVisitaWindow().getSexF().setSelected(false);
		} else {
			view.getPrimaVisitaWindow().getSexF().setSelected(true);
		}
	}

	private void gestoreSexF() {

		if (view.getPrimaVisitaWindow().getSexF().isSelected()) {
			view.getPrimaVisitaWindow().getSexM().setSelected(false);
		} else {
			view.getPrimaVisitaWindow().getSexM().setSelected(true);
		}
	}

	private void gestoreAttivit‡Bassa() {

		if (view.getPrimaVisitaWindow().getBassa().isSelected()) {
			view.getPrimaVisitaWindow().getMedia().setSelected(false);
			view.getPrimaVisitaWindow().getAlta().setSelected(false);
		}
	}

	private void gestoreAttivit‡Media() {

		if (view.getPrimaVisitaWindow().getMedia().isSelected()) {
			view.getPrimaVisitaWindow().getBassa().setSelected(false);
			view.getPrimaVisitaWindow().getAlta().setSelected(false);
		}
	}

	private void gestoreAttivit‡Alta() {

		if (view.getPrimaVisitaWindow().getAlta().isSelected()) {
			view.getPrimaVisitaWindow().getMedia().setSelected(false);
			view.getPrimaVisitaWindow().getBassa().setSelected(false);
		}
	}

	private void gestoreControlloEt‡() {
		// controllo che il campo pesoText contenga effettivamente un numero, e
		// se Ë vuoto!
		// nel caso in cui fosse vuoto non eseguo il controllo per vedere se Ë
		// numerico
		if (!view.getPrimaVisitaWindow().getEt‡Text().getText().equals("")) {
			if (!(view.getPrimaVisitaWindow().isNumeric(view
					.getPrimaVisitaWindow().getEt‡Text().getText()))) {

				view.getPrimaVisitaWindow().getEt‡Text().setText("");
				view.getPrimaVisitaWindow().getEt‡Text().requestFocus();

				Object[] options = { "OK" };
				JOptionPane
						.showOptionDialog(
								null,
								"Non Ë stato inserito un valore numerico corretto per l'et‡!",
								"Attenzione!!!", JOptionPane.PLAIN_MESSAGE,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
			}
		}
	}

	private void gestoreControlloPeso() {
		// controllo che il campo Et‡text contenga effettivamente un numero, e
		// se Ë vuoto!
		// nel caso in cui fosse vuoto non eseguo il controllo per vedere se Ë
		// numerico
		if (!view.getPrimaVisitaWindow().getPesoText().getText().equals("")) {
			if (!(view.getPrimaVisitaWindow().isNumeric(view
					.getPrimaVisitaWindow().getPesoText().getText()))) {

				view.getPrimaVisitaWindow().getPesoText().setText("");
				view.getPrimaVisitaWindow().getPesoText().requestFocus();

				Object[] options = { "OK" };
				JOptionPane
						.showOptionDialog(
								null,
								"Non Ë stato inserito un valore numerico corretto per il peso!",
								"Attenzione!!!", JOptionPane.PLAIN_MESSAGE,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
			}
		}
	}

	private void gestoreControlloAltezza() {
		// controllo che il campo Et‡text contenga effettivamente un numero, e
		// se Ë vuoto!
		// nel caso in cui fosse vuoto non eseguo il controllo per vedere se Ë
		// numerico
		if (!view.getPrimaVisitaWindow().getAltezzaText().getText().equals("")) {
			if (!(view.getPrimaVisitaWindow().isNumeric(view
					.getPrimaVisitaWindow().getAltezzaText().getText()))) {

				view.getPrimaVisitaWindow().getAltezzaText().setText("");
				view.getPrimaVisitaWindow().getAltezzaText().requestFocus();

				Object[] options = { "OK" };
				JOptionPane
						.showOptionDialog(
								null,
								"Non Ë stato inserito un valore numerico corretto per l'Altezza!",
								"Attenzione!!!", JOptionPane.PLAIN_MESSAGE,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
			}
		}
	}

	private void gestoreControlloCircVita() {
		// controllo che il campo Et‡text contenga effettivamente un numero, e
		// se Ë vuoto!
		// nel caso in cui fosse vuoto non eseguo il controllo per vedere se Ë
		// numerico
		if (!view.getPrimaVisitaWindow().getCircVitaText().getText().equals("")) {
			if (!(view.getPrimaVisitaWindow().isNumeric(view
					.getPrimaVisitaWindow().getCircVitaText().getText()))) {

				view.getPrimaVisitaWindow().getCircVitaText().setText("");
				view.getPrimaVisitaWindow().getCircVitaText().requestFocus();

				Object[] options = { "OK" };
				JOptionPane
						.showOptionDialog(
								null,
								"Non Ë stato inserito un valore numerico corretto per la Circonferenza della Vita!",
								"Attenzione!!!", JOptionPane.PLAIN_MESSAGE,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
			}
		}
	}

	private void gestoreControlloCircFianchi() {
		// controllo che il campo Et‡text contenga effettivamente un numero, e
		// se Ë vuoto!
		// nel caso in cui fosse vuoto non eseguo il controllo per vedere se Ë
		// numerico
		if (!view.getPrimaVisitaWindow().getCircFianchiText().getText()
				.equals("")) {
			if (!(view.getPrimaVisitaWindow().isNumeric(view
					.getPrimaVisitaWindow().getCircFianchiText().getText()))) {

				view.getPrimaVisitaWindow().getCircFianchiText().setText("");
				view.getPrimaVisitaWindow().getCircFianchiText().requestFocus();

				Object[] options = { "OK" };
				JOptionPane
						.showOptionDialog(
								null,
								"Non Ë stato inserito un valore numerico corretto per la Circonferenza dei Fianchi!",
								"Attenzione!!!", JOptionPane.PLAIN_MESSAGE,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
			}
		}
	}

	private void ConfermaESalvaVisitaListener() {

		if (view.getPrimaVisitaWindow().controllaCampi()) {
			// esegui la query

			char sesso = 'F';
			if (view.getPrimaVisitaWindow().getSexM().isSelected())
				sesso = 'M';

			view.getDbInterface().registraCliente(
					view.getPrimaVisitaWindow().getCFText().getText(),
					view.getPrimaVisitaWindow().getNameText().getText(),
					view.getPrimaVisitaWindow().getCognomeText().getText(),
					Integer.parseInt(view.getPrimaVisitaWindow().getEt‡Text()
							.getText()),
					view.getPrimaVisitaWindow().convertiAttivit‡(),
					sesso,
					Integer.parseInt(view.getPrimaVisitaWindow().getPesoText()
							.getText()),
					Integer.parseInt(view.getPrimaVisitaWindow()
							.getAltezzaText().getText()),
					view.getDbInterface().getCodDottByNickName(
							view.getActiveUser().getNickName()));

			Stack<String> x = new Stack<String>();
			view.getPrimaVisitaWindow().inizializzaStack(x);

			int circVita = 0, circFianchi = 0;
			if (!view.getPrimaVisitaWindow().getCircVitaText().getText()
					.equals(""))
				circVita = Integer.parseInt(view.getPrimaVisitaWindow()
						.getCircVitaText().getText());

			if (!view.getPrimaVisitaWindow().getCircFianchiText().getText()
					.equals(""))
				circFianchi = Integer.parseInt(view.getPrimaVisitaWindow()
						.getCircFianchiText().getText());

			view.getDbInterface()
					.salvaVisita(
							x,
							Giorni.castDate(new GregorianCalendar(Integer
									.parseInt((String) view
											.getPrimaVisitaWindow()
											.getComboAnno().getSelectedItem()),
									(((Giorni) view.getPrimaVisitaWindow()
											.getComboMese().getSelectedItem())
											.getNumero()), Integer
											.parseInt((String) view
													.getPrimaVisitaWindow()
													.getComboGiorno()
													.getSelectedItem()))),
							sesso,
							Integer.parseInt(view.getPrimaVisitaWindow()
									.getEt‡Text().getText()),
							view.getPrimaVisitaWindow().convertiAttivit‡(),
							Integer.parseInt(view.getPrimaVisitaWindow()
									.getPesoText().getText()),
							Integer.parseInt(view.getPrimaVisitaWindow()
									.getAltezzaText().getText()), circVita,
							circFianchi);

			view.getPrimaVisitaWindow().hide(); // nascondi il frame

		} else {

			Object[] options = { "OK" };
			JOptionPane
					.showOptionDialog(
							null,
							"Alcuni messaggi obbligatori non sono stati inseriti\n \n"
									+ "Inserire i campi in verde prima di procedere al salvataggio.",
							"Attenzione!!!", JOptionPane.PLAIN_MESSAGE,
							JOptionPane.QUESTION_MESSAGE, null, options,
							options[0]);
		}
	}

}// fine classe

package view;

import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import dbAccess.DbInterface;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class ScegliVisita extends JInternalFrame {

	private DesktopWindow desktopWindow;
	private JLabel imgUnlock = new JLabel("");
	private DbInterface dbInterface = new DbInterface();
	private JDialog jd;
	private JButton btnVisualizza;
	private JButton btnModifica;
	private JLabel lblSelezionaUtente;
	private JLabel lblSelezionaData;
	private JComboBox comboPazienti;
	private JComboBox comboDate;
	/**
	 * Create the frame.
	 */
	public ScegliVisita() {
		setBorder(new LineBorder(Color.BLUE, 1, true));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setVerifyInputWhenFocusTarget(false);
		setTitle("Seleziona visita");
		setBounds(100, 100, 702, 472);
		getContentPane().setLayout(null);
		
		btnVisualizza = new JButton("Visualizza");
		btnVisualizza.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 16));
		btnVisualizza.setBounds(90, 359, 156, 40);
		getContentPane().add(btnVisualizza);
		
		btnModifica = new JButton("Modifica");
		
		btnModifica.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 16));
		btnModifica.setBounds(428, 359, 156, 40);
		getContentPane().add(btnModifica);
		
		lblSelezionaUtente = new JLabel("Seleziona  Utente:");
		lblSelezionaUtente.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 16));
		lblSelezionaUtente.setBounds(90, 82, 156, 30);
		getContentPane().add(lblSelezionaUtente);
		
		lblSelezionaData = new JLabel("Seleziona Data:");
		lblSelezionaData.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 16));
		lblSelezionaData.setBounds(428, 82, 156, 30);
		getContentPane().add(lblSelezionaData);
		
		comboPazienti = new JComboBox();
		comboPazienti.setBounds(64, 130, 247, 30);
		getContentPane().add(comboPazienti);
		
		comboDate = new JComboBox();
		comboDate.setEditable(true);
		comboDate.setBounds(398, 131, 229, 30);
		getContentPane().add(comboDate);
	
		
		
		
		//EVENTI-------------------------------------------------------------------------------------------------------------
		// una volta selezionato il paziente nel primo combo devo inizializzare in secondo con le visite di quel particolare paziente
		comboPazienti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome,cognome;
				nome=((String) comboPazienti.getSelectedItem()).substring(0,((String) comboPazienti.getSelectedItem()).indexOf('_'));
				cognome= ((String)  comboPazienti.getSelectedItem()).substring(((String) comboPazienti.getSelectedItem()).indexOf('_')+1, ((String) comboPazienti.getSelectedItem()).length());
				Vector<String> date = dbInterface.getDataVisitaFromPaziente(nome, cognome);
				System.out.println(nome + " " + cognome + " " + date);
				populateComboDate(date);
			}
		});
		
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}
	public void populateComboPazienti() {

		Vector<String> pazienti = dbInterface.getNomiPazienti(dbInterface
				.getCodDottByNickName(desktopWindow.getActiveUser()
						.getNickName()));

		for (int i = 0; i < pazienti.size(); i++) {
			comboPazienti.addItem(pazienti.get(i));
		}
	}
	
	public void populateComboDate(Vector<String> date){
		for(int i=0;i<date.size();i++)
			comboDate.addItem(date.get(i));
		
	}
	
	public void importaDesktopWindow(DesktopWindow dw) {

		this.desktopWindow = dw;

	}
}

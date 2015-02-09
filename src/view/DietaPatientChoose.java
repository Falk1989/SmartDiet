package view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import dbAccess.DbInterface;

import java.awt.Font;
import java.lang.reflect.Array;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class DietaPatientChoose extends JInternalFrame {

	private DesktopWindow desktopWindow;
	private JLabel imgUnlock = new JLabel("");
	private DbInterface dbInterface = new DbInterface();
	private JDialog jd;
	private JButton btnNewButton;
	private JButton buttonManuale;
	private JButton buttonAutomatica;
	private JComboBox comboPazienti;
	private JLabel lblSelezionareIlPaziente;

	public DietaPatientChoose() {
		setBorder(new LineBorder(Color.BLUE, 1, true));
		setTitle("Selezionare il paziente");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setBounds(100, 100, 667, 425);
		getContentPane().setLayout(null);

		buttonManuale = new JButton("Crea Manuale");

		buttonManuale.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		buttonManuale.setBounds(414, 105, 177, 30);
		getContentPane().add(buttonManuale);

		buttonAutomatica = new JButton("Crea Automatica");

		buttonAutomatica.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 16));
		buttonAutomatica.setBounds(414, 232, 177, 30);
		getContentPane().add(buttonAutomatica);

		comboPazienti = new JComboBox();
		comboPazienti.setEditable(true);
		comboPazienti.setBounds(61, 110, 296, 25);
		getContentPane().add(comboPazienti);

		lblSelezionareIlPaziente = new JLabel("Selezionare il Paziente:");
		lblSelezionareIlPaziente.setFont(new Font(
				"Microsoft JhengHei UI Light", Font.PLAIN, 16));
		lblSelezionareIlPaziente.setBounds(61, 54, 177, 30);
		getContentPane().add(lblSelezionareIlPaziente);

		// azioni sui pulsanti
		buttonManuale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Avviare generazione manuale della dieta con i dati inseriti
				// dall'utente.
				hide();
			}
		});

		buttonAutomatica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Avviare generazione automatica della dieta -> schermata con i
				// dati e con la selezione del tipo di dieta.
				hide();
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

	public void importaDesktopWindow(DesktopWindow dw) {

		this.desktopWindow = dw;

	}
}

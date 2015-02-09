package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.color.CMMException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import dbAccess.DbInterface;

public class DeleteAmministratore extends JInternalFrame {

	private JLabel lblNickname;
	private JLabel label;
	private JComboBox comboProfilo;
	private JComboBox combNickName;
	private DesktopWindow desktopWindow;
	private DbInterface dbInterface = new DbInterface();
	private JButton btnCancella;

	/**
	 * Create the frame.
	 */
	public DeleteAmministratore() {
		// ----------------------------------------------------------------------------
		// disegno la finestra
		drawGUI();
		// -----------------------------------------------------------------------------

		// --------listener non implementabili nel controller
		// ----------------------------------------------------------------------------
		// quando la finestra viene chiusa con "x" in alto a destra si procede a
		// riabilitare la voce di menù Logout del menù FILE
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent arg0) {
				desktopWindow.getItemLogout().setEnabled(true);
				desktopWindow.getAllertWindow().setVisible(false);
			}
		});


//		
//		 combNickName.addKeyListener(new KeyAdapter() {
//		 @Override
//		 public void keyReleased(KeyEvent arg0) {
//		 if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
//		 boolean esitoScrittura;
//		 // inizia la procedura di cancellazione su DB
//		 esitoScrittura = dbInterface
//		 .cancellaAdmin((String) combNickName
//		 .getSelectedItem());
//		 if ((esitoScrittura == true)
//		 && (((String) combNickName.getSelectedItem())
//		 .equals(desktopWindow.getUtente()))) {
//		 hide();
//		 desktopWindow.logOutProcedure();
//		 } else
//		 hide();
//		
//		 }
//		 }
//		 });

	}

	public JComboBox getCombNickName() {
		return combNickName;
	}

	public void importaDesktopWindow(DesktopWindow dw) {

		this.desktopWindow = dw;

	}

	private void drawGUI() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setFrameIcon(new ImageIcon(
				DeleteAmministratore.class
						.getResource("/icons/vestizione_24.png")));
		setTitle("Cancella Amministratore");
		setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
		setClosable(true);
		setBounds(100, 100, 566, 210);
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

		comboProfilo = new JComboBox(dbInterface.getElencoProfili().toArray());
		comboProfilo.setSelectedIndex(0);
		comboProfilo.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboProfilo.setEnabled(false);
		comboProfilo.setBounds(227, 24, 251, 37);
		getContentPane().add(comboProfilo);
		comboProfilo.setEnabled(false);

		btnCancella = new JButton("Cancella");
		btnCancella.setIcon(new ImageIcon(DeleteAmministratore.class
				.getResource("/icons/ingranaggi_stop_32.png")));
		btnCancella.setFont(new Font("Tahoma", Font.ITALIC, 18));
		btnCancella.setBounds(141, 125, 291, 37);
		getContentPane().add(btnCancella);

		combNickName = new JComboBox(
				(dbInterface.getElencoNickName((String) comboProfilo
						.getSelectedItem())).toArray());
		combNickName.setAutoscrolls(true);
		combNickName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		combNickName.setBounds(227, 69, 251, 35);
		getContentPane().add(combNickName);
	} //fine costruttore

	// metodi che permettono al CONTROLLER di referenziare il listener che
		// effettua le modifiche al MODEL
	
	
	public void AddMyDeleteAmministratoreListener(ActionListener listener) {
			btnCancella.addActionListener(listener);
		}

	public void AddMyDeleteAmministratoreListenerKey(KeyListener listener) {
			combNickName.addKeyListener(listener);
		}

		// ----------------------------------------------------------------------------------------------------

}

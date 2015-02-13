package view;

import interfacce.interfaceInsertAmministratore;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import dbAccess.DbInterface;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPasswordField;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.border.BevelBorder;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class LogSistema extends JInternalFrame {
	private DesktopWindow desktopWindow;
	private DbInterface dbInterface = new DbInterface();
	private AllertWindow allertWindow = new AllertWindow();
	private JPanel panel;
	private JTable table;
	private JScrollPane scrollPane;
	private JRadioButton rbtnTUTTO;
	private JRadioButton rbtnPROFILO;
	private JRadioButton rbtnNICKNAME;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel panel_1;
	private JPanel panel_2;
	private JComboBox comboItem;
	private Vector<Vector<Object>> dati; // matrice "vettore di vettore" che
											// ospiterà i dati
	private Vector<String> colonne; // dbInterface.getColonneByLog(); //vettore
									// che ospiterà le colenne

	/**
	 * Create the frame.
	 */
	public LogSistema() {

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setFrameIcon(new ImageIcon(
				LogSistema.class.getResource("/icons/puzzle_20.png")));
		// setIcon(true);
		setTitle("Visualizzazione LOG di sistema su Data Base");
		setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
		setClosable(true);
		setBounds(100, 100, 1257, 572);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 1255, 544);
		panel.setLayout(null);
		getContentPane().add(panel);

		table = new JTable();

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(22, 140, 1210, 393);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.add(scrollPane);

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"),
				"Opzioni di filtraggio DATI", TitledBorder.RIGHT,
				TitledBorder.TOP, null, Color.BLUE));
		panel_1.setBounds(22, 0, 1210, 50);
		panel.add(panel_1);
		panel_1.setLayout(null);

		rbtnTUTTO = new JRadioButton("Visualizza TUTTO");
		rbtnTUTTO.setSelected(true);
		rbtnTUTTO.setForeground(Color.RED);
		rbtnTUTTO.setBounds(72, 20, 207, 23);
		panel_1.add(rbtnTUTTO);
		rbtnTUTTO
				.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 18));
		buttonGroup.add(rbtnTUTTO);

		rbtnPROFILO = new JRadioButton("Raggruppa per PROFILO");
		rbtnPROFILO.setForeground(Color.RED);
		rbtnPROFILO.setBounds(442, 20, 259, 23);
		panel_1.add(rbtnPROFILO);
		rbtnPROFILO.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD,
				18));
		buttonGroup.add(rbtnPROFILO);

		rbtnNICKNAME = new JRadioButton("Raggruppa per NICKNAME");
		rbtnNICKNAME.setForeground(Color.RED);
		rbtnNICKNAME.setBounds(856, 20, 267, 23);
		panel_1.add(rbtnNICKNAME);
		rbtnNICKNAME.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD,
				18));
		buttonGroup.add(rbtnNICKNAME);

		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"),
				"Selezionare ITEM di categoria", TitledBorder.RIGHT,
				TitledBorder.TOP, null, new Color(0, 0, 255)));
		panel_2.setBounds(418, 61, 403, 68);
		panel.add(panel_2);
		panel_2.setLayout(null);

		comboItem = new JComboBox();
		comboItem.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN,
				16));
		comboItem.setEnabled(false);
		comboItem.setBounds(40, 23, 314, 34);
		panel_2.add(comboItem);

		// listener

		rbtnPROFILO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboItem.setEnabled(true);
				// svuotalo
				comboItem.removeAllItems();
				// caricalo con la lista profili
				Vector<String> app = dbInterface.getProfileByLog();
				for (int i = 0; i < app.size(); i++) {
					comboItem.addItem(app.get(i));
				}
				comboItem.setSelectedIndex(0);
			}
		});

		rbtnNICKNAME.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboItem.setEnabled(true);
				// svuotalo
				comboItem.removeAllItems();
				// caricalo con la lista profili
				Vector<String> app = dbInterface.getNickNameByLog();
				for (int i = 0; i < app.size(); i++) {
					comboItem.addItem(app.get(i));
				}
				comboItem.setSelectedIndex(0);
			}
		});

		rbtnTUTTO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboItem.setEnabled(false);
				// aggiorna la tabella
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
				setTable(d, c);
				getTable().repaint();

				// settaggio margini tabella tabella

				getTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				TableColumn column2 = getTable().getColumnModel()
						.getColumn(1);
				column2.setPreferredWidth(200);
				TableColumn column3 = getTable().getColumnModel()
						.getColumn(2);
				column3.setPreferredWidth(120);
				TableColumn column4 = getTable().getColumnModel()
						.getColumn(3);
				column4.setPreferredWidth(120);
				;
				TableColumn column5 = getTable().getColumnModel()
						.getColumn(getTable().getColumnCount() - 1);
				column5.setPreferredWidth(getScrollPane().getWidth());
			}
		});

				
		comboItem.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (rbtnPROFILO.isSelected())
				{			
				// aggiorna la tabella
				Vector<Vector<Object>> d = dbInterface.getMatriceByLogFiltroProfili((String)comboItem.getSelectedItem()); // matrice
				// "vettore di vettore"
				// che
				// ospiterà
				// i
				// dati
				Vector<String> c = dbInterface.getColonneByLog(); // vettore che
				// ospiterà
				// le
				// colenne
				setTable(d, c);
				getTable().repaint();

				// settaggio margini tabella tabella

				getTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				TableColumn column2 = getTable().getColumnModel()
						.getColumn(1);
				column2.setPreferredWidth(200);
				TableColumn column3 = getTable().getColumnModel()
						.getColumn(2);
				column3.setPreferredWidth(120);
				TableColumn column4 = getTable().getColumnModel()
						.getColumn(3);
				column4.setPreferredWidth(120);
				;
				TableColumn column5 = getTable().getColumnModel()
						.getColumn(getTable().getColumnCount() - 1);
				column5.setPreferredWidth(getScrollPane().getWidth());
				}
				else  //è selezionata l'option di filtraggio by nickname
				{
					// aggiorna la tabella
					Vector<Vector<Object>> d = dbInterface.getMatriceByLogFiltroNickName((String)comboItem.getSelectedItem()); // matrice
					// "vettore di vettore"
					// che
					// ospiterà
					// i
					// dati
					Vector<String> c = dbInterface.getColonneByLog(); // vettore che
					// ospiterà
					// le
					// colenne
					setTable(d, c);
					getTable().repaint();

					// settaggio margini tabella tabella

					getTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					TableColumn column2 = getTable().getColumnModel()
							.getColumn(1);
					column2.setPreferredWidth(200);
					TableColumn column3 = getTable().getColumnModel()
							.getColumn(2);
					column3.setPreferredWidth(120);
					TableColumn column4 = getTable().getColumnModel()
							.getColumn(3);
					column4.setPreferredWidth(120);
					;
					TableColumn column5 = getTable().getColumnModel()
							.getColumn(getTable().getColumnCount() - 1);
					column5.setPreferredWidth(getScrollPane().getWidth());
				}
			}
		});

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent arg0) {
				desktopWindow.getItemLogout().setEnabled(true);
				desktopWindow.getAllertWindow().setVisible(false);
			}
		});

	}

	public JComboBox getComboItem() {
		return comboItem;
	}

	public void setTable(Vector<Vector<Object>> dati, Vector<String> colonne) {
		// this.table = new JTable(dati,colonne);
		this.table.setModel(new DefaultTableModel(dati, colonne));

	}

	public void setDati(Vector<Vector<Object>> dati) {
		this.dati = dati;
	}

	public void setColonne(Vector<String> colonne) {
		this.colonne = colonne;
	}

	public JTable getTable() {
		return table;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void importaDesktopWindow(DesktopWindow dw) {

		this.desktopWindow = dw;

	}
}

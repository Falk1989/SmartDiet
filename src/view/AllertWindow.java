package view;

import interfacce.interfaceAllertWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class AllertWindow extends JDialog  {

	private JPanel contentPanel = new JPanel();
	private JButton btnOK;
	private JLabel lblAttenzione;
	private JScrollPane scrollPane;
	private JTextPane txtArea;


	/**
	 * Create the dialog.
	 */
	public AllertWindow() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AllertWindow.class.getResource("/icons/allarme_32.png")));
		setResizable(false);
		setBounds(100, 100, 396, 193);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		btnOK = new JButton("OK");
		btnOK.setBounds(123, 117, 133, 36);
		btnOK.setIcon(new ImageIcon(AllertWindow.class.getResource("/icons/martello_32.png")));
		btnOK.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		contentPanel.add(btnOK);
		
		lblAttenzione = new JLabel("Attenzione!!!");
		lblAttenzione.setBounds(123, 0, 133, 36);
		lblAttenzione.setForeground(Color.RED);
		lblAttenzione.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		contentPanel.add(lblAttenzione);
		
		scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 35, 370, 71);
		contentPanel.add(scrollPane);
		
		txtArea = new JTextPane();
		txtArea.setFont(new Font("Tahoma", Font.ITALIC, 14));
		scrollPane.setViewportView(txtArea);
		
		
		//listener
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
			}
		});
		
		txtArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					setVisible(false);

				}
			}
		});
		
		
		//quando l'utente non schiaccia ne OK ne X
				addFocusListener(new FocusAdapter() {
					@Override
					public void focusLost(FocusEvent e) {
						dispose();
					}
				});
				
			
		
		
	}
	
	public void aggiungiMessaggio(String str){
		txtArea.setText(str);
	}
}

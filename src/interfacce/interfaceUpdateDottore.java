package interfacce;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import view.DesktopWindow;

public interface interfaceUpdateDottore {
	
	public void importaDesktopWindow(DesktopWindow dw);
	public JTextField getFieldNickName();
	public JPasswordField getFieldPassword();
	public JTextField getFieldCognome();
	public JTextField getFieldNome();
	public JTextField getFieldCodiceFiscale();
	public JTextField getFieldCellulare();
	public JTextField getFieldMail();
	public JTextField getFieldFax();

	

}

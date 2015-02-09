package interfacce;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import view.DesktopWindow;

public interface interfaceUpdateAmministratore {

	public JComboBox getCombNickName();
	public void importaDesktopWindow(DesktopWindow dw);
	public JPasswordField getFieldNewPassword();
	public JTextField getFieldNewNickName();

	

}

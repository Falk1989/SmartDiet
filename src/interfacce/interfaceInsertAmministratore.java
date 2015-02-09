package interfacce;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import view.DesktopWindow;

public interface interfaceInsertAmministratore {
	
	public void importaDesktopWindow(DesktopWindow dw);
	public JTextField getFieldNickName();
	public JPasswordField getFieldPassword();

}

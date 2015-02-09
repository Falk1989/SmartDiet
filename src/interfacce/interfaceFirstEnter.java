package interfacce;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import view.DesktopWindow;

public interface interfaceFirstEnter {
	
	public JTextField getFieldUsarName();
	public void setFieldUsarName(JTextField fieldUsarName);
	public JPasswordField getFieldPassword();
	public void setFieldPassword(JPasswordField fieldPassword);
	public void importaDesktopWindow(DesktopWindow dw);	
	
	

}

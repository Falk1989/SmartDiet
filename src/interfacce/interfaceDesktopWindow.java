package interfacce;

import java.util.Map;

import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import view.Login;

public interface interfaceDesktopWindow {

	public void logOutProcedure();
	public JMenuItem getItemLogin();
	public JMenuItem getItemLogout();
	public JDesktopPane getDesktopPane();
	public JMenu getMenuFile();
	public JLabel getLblUsers();
	public JLabel getLblProfile();
	public JMenu getSmnDottDietologo();
	public JMenu getSmnAmministratoriSistema();
	public JMenu getSmnPaziente();
	public String getUtente();
	public void setUtente(String utente);
	public String getProfilo();
	public void setProfilo(String profilo);
    public void abilitaMenu(Map<String, Boolean> lista);	

}

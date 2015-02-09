package myProject;

import java.awt.EventQueue;

import controller.Controller;
import view.DesktopWindow;
import model.ActiveUser;

public class Launcher {
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		//EventQueue.invokeLater(new Runnable() {
		//	public void run() {
				try {
					
					//creazione MODEL
					ActiveUser activeUser = new ActiveUser("","");
					
					//creazione VIEW
					DesktopWindow frame = new DesktopWindow(activeUser);
					
					//creazione CONTROLLER a cui passo MODEL e VIEW
					Controller supervisor = new Controller(activeUser, frame); 
					
					//rendo visibile la FRAME PRINCIPALE
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		//	}
		//});
	}


}

package model;

import java.util.Observable;
import java.util.Observer;

public class ActiveUser extends Observable {
	private String nickName;
	private String level;
	
	
	
	public ActiveUser(String nickName, String level) {
		this.nickName = nickName;
		this.level = level;
	}
	
	public ActiveUser() {
		// TODO Auto-generated constructor stub
	}



	public String getNickName() {
		return nickName;
	}



	public void setNickName(String nickName) {
		this.nickName = nickName;
		sendNotify();
	}



	public String getLevel() {
		return level;
	}



	public void setLevel(String level) {
		this.level = level;
		sendNotify();
	}
	
	
	private void sendNotify(){ //notifica a tutti coloro che lo osservano il messaggio (gli OBSERVER)
		Message msg = new Message(nickName, level); // messaggio con lo stato corrente delle delle variabili della classe
		
		setChanged();
		
		notifyObservers(msg);
		
		
	}
	
	

}

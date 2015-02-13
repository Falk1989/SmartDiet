package dbAccess;



import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import view.DesktopWindow;

public class DbInterface{

	private DataHandler handler;



	public DbInterface() {

		handler = new DataHandler();
    }

	public boolean verifyLogin(String user, String password) {
		boolean result = false;

		// new SimpleDateFormat("dd/MM/yyyy H:mm:ss").format(new Date());

		try {
			// .....
			result = handler.verifyLoginOnDB(user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		

		return result;
	}

	public boolean registraAdmin(String profilo, String nickname,
			String level, String user, String password) {
		boolean result = false;

		try {
			// .....
			result = handler.createAdminOnDB(profilo, nickname,level, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean modificaAdmin(String profilo, String nickname,
			String oldNickName, String newNickName, String password) {
		boolean result = false;

		try {
			// .....
			result = handler.modificaAdminOnDB(profilo, nickname, oldNickName, newNickName,  password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean cancellaAdmin(String profilo, String nickname,String user) {
		boolean result = false;

		try {
			// .....
			result = handler.cancellaAdminOnDB(profilo, nickname,user);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean registraDottore(String profilo, String user,
			String level, 
			String usr, 
			String pwd, 
            String Cognome,
            String Nome,
            Date DataNascita,
            String CodFiscale,
            String Cellulare, 
            String Mail, 
            String Fax) {
		boolean result = false;

		try {
			// .....
			result = handler.createDottoreOnDB(profilo, user,
					level, usr, pwd,
                    Cognome, Nome,
                    DataNascita,
                    CodFiscale,
                    Cellulare, 
                    Mail, 
                    Fax);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean modificaDottore(String profilo, String user,
			String level,
			String oldNickName,
			String newNickName, 
			String pwd, 
            String Cognome,
            String Nome,
            Date DataNascita,
            String CodFiscale,
            String Cellulare, 
            String Mail, 
            String Fax,
            int CodDottore) {
		boolean result = false;

		try {
			// .....
			result = handler.modificaDottoreOnDB(profilo, user,level,
					oldNickName,
					newNickName, 
					pwd, 
		            Cognome,
		            Nome,
		            DataNascita,
		            CodFiscale,
		            Cellulare, 
		            Mail, 
		            Fax,
		            CodDottore);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean cancellaDottore(String profilo, String nickname,String user) {
		boolean result = false;

		try {
			// .....
			result = handler.cancellaDottoreOnDB(profilo, nickname,user);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	
	
	
		
	
	
	
	public boolean verifyFirstAccess() {
		boolean result = false;

		try {
			// .....
			result = handler.verifyFirstAccessOnDB();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}
	

	public int getCodProfileByDescr(String descr){
		int result = -1;

		try {
			// .....
			result = handler.getCodProfileByDescrOnDB(descr);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		}
	
	
	
	public int getCodDottByNickName(String user) {
		int result = -1;

		try {
			// .....
			result = handler.getCodDottByNickNameOnDB(user);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		}


	
	public String getProfileByNickname(String user){
		String result = "";

		try {
			// .....
			result = handler.getProfileByNicknameOnDB(user);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		}
	
	public String getProfileByOwnType(int type){
		String result = "";

		try {
			// .....
			result = handler.getProfileByOwnType(type);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		}
	
	public Map<String, Boolean> getUserVisibilityByLevel(String level ){
		Map<String, Boolean> result = null;

		try {
			// .....
			result = handler.getUserVisibilityByLevelOnDB(level );
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
 		
	}
	
	public DataHandler getHandler() {
		return handler;
	}

	public Vector<String> getInfoAboutDoctor(String user ){
		Vector<String> result = null;

		try {
			// .....
			result =  handler.getInfoAboutDoctorOnDB(user);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	
 		
	}
	
	public Vector<Vector<Object>> getMatriceByLog(){
		Vector<Vector<Object>> result = null;

		try {
			// .....
			result =  handler.getMatriceByLogOnDB();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	
 		
	}
	
	public Vector<Vector<Object>> getMatriceByLogFiltroProfili(String profilo){
		Vector<Vector<Object>> result = null;

		try {
			// .....
			result =  handler.getMatriceByLogFiltroProfiliOnDB(profilo);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	
 		
	}
	
	public Vector<Vector<Object>> getMatriceByLogFiltroNickName(String nickname){
		Vector<Vector<Object>> result = null;

		try {
			// .....
			result =  handler.getMatriceByLogFiltroNickNameOnDB(nickname);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	
 		
	}
	
	public Vector<String> getColonneByLog(){
		Vector<String> result = null;

		try {
			// .....
			result =  handler.getColonneByLogOnDB();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	
 		
	}
	
	
	
	
	
	
	public ArrayList<String> getElencoProfili(){
		ArrayList<String> result = null;
		try {
			// .....
			result = handler.getElencoProfiliOnDB();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	public Vector<String> getElencoNickName(String level){
		Vector<String> result = null;
		try {
			// .....
			result = handler.getElencoNickNameOnDB(level);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	
	public boolean verifyNickName(String user) {
		boolean result = false;

		try {
			// .....
			result = handler.verifyNickNameOnDB(user);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}
	
	
	
	//classi del FEDE
	
	public boolean registraCliente(String CF, String nome, String cognome, int età, int attività, int sesso, int peso, int altezza) {
		boolean result= false;
		try {
			 result= handler.registraClienteOnDB( CF,  nome,  cognome,  età,  attività,  sesso, peso, altezza);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Vector<String> getDataVisitaFromPaziente(String nome, String cognome){
		Vector<String> result = new Vector<String>();
		try {
			// .....
			result = handler.getDataVisitaFromPazienteOnDB(nome, cognome);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Vector<String> getNomiPazienti(int code){
		Vector<String> result = null;
		try {
			// .....
			result = handler.getNomiPazientiOnDB(code);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}	
	
	public boolean registraCliente(String CF, String nome, String cognome,
			int età, char attività, char sesso, int peso, int altezza,
			int codDottore) {
		boolean result = false;
		try {
			result = handler.registraClienteOnDB(CF, nome, cognome, età,
					attività, sesso, peso, altezza, codDottore);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	public Vector<Object> getElementsOfVisita(String nome, String cognome, java.util.Date data){
		Vector<Object> result = new Vector<Object>();
		try {
			// .....
			result = handler.getElementsOfVisitaOnDB(nome,cognome,data);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean salvaVisita(Stack<String> x, Date data, char sesso,int età, char attività,
			 int peso, int altezza, int circVita, int circFianchi){
			 boolean result= false;
			 try {
			 result= handler.salvaVisitaOnDB( x, data, sesso, età, attività, peso, altezza,
			 circVita, circFianchi);
			 } catch (ClassNotFoundException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }
			
			 return result;
		}
	
	
	public boolean registraCredenziali(String profilo, String nickname, String level, String user, String password) {
		return registraAdmin(profilo, nickname, level, user, password);

	}
	
	
	public Vector<String> getProfileByLog(){
		Vector<String> result = null;

		try {
			// .....
			result = handler.getProfileByLogOnDB();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		}

	public Vector<String> getNickNameByLog(){
		Vector<String> result = null;

		try {
			// .....
			result = handler.getNickNameByLogOnDB();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		}
	
}

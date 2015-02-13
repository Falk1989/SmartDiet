package dbAccess;



import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import javax.tools.JavaCompiler;

import view.DesktopWindow;

public class DataHandler {
	

	
	

	public boolean createAdminOnDB(String profilo, String nickname,
			String level, String usr, String pwd)
			throws ClassNotFoundException {
		boolean result = false;
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
	
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------

			// ipotetiche query
            String query = "insert into Users values('" + usr + "','"
					+ pwd + "','"+getCodProfileByDescrOnDB(level)+"')";
			statement.executeUpdate(query);
			result = true;

			//scrittura del LOG
			statement.executeUpdate(writeLog(query, profilo, nickname));

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;

	}

	public boolean modificaAdminOnDB(String profilo, String nickname,
			String oldNickName, String newNickName,
			String password) throws ClassNotFoundException {
		boolean result = false;
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		ResultSet rs = null;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------

			// ipotetiche query
            String query = "update Users set Nickname = '"
					+ newNickName + "', Password = '" + password
					+ "' where Nickname = '" + oldNickName + "'";
			statement.executeUpdate(query);
			result = true;

			//scrittura del LOG
			statement.executeUpdate(writeLog(query, profilo, nickname));

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;

	}

	public boolean cancellaAdminOnDB(String profilo, String nickname,
			String user) throws ClassNotFoundException {
		boolean result = false;
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		ResultSet rs = null;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------

			// ipotetiche query
            String query = "delete from Users where NickName ='"
					+ user + "'";
			statement.executeUpdate(query);
			result = true;

			
			//scrittura del LOG
			statement.executeUpdate(writeLog(query, profilo, nickname));

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;

	}

	public boolean createDottoreOnDB(String profilo, String user,
			String level, 
			String usr, 
			String pwd,
			String Cognome, 
			String Nome,  
			Date DataNascita,
			String CodFiscale, 
			String Cellulare, 
			String Mail, 
			String Fax)
			throws ClassNotFoundException {
		boolean result = false;
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
	
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------

			// ipotetiche query
            String query1 = "insert into Dottori values ('" + usr
					+ "','" + Cognome + "','" + Nome
					+ "','"+ DataNascita +"','"
					+ CodFiscale + "','" + Cellulare + "','" + Mail + "','"
					+ Fax + "',-1)";
            // inserimento nella tabella dottori
			statement.executeUpdate(query1);
			
			// inserimento nella tabella Users
			String query2 = "insert into Users values('" + usr + "','"
					+ pwd + "'," + getCodProfileByDescrOnDB(level) + ")";
			statement.executeUpdate(query2);
			

			//scrittura del LOG
			statement.executeUpdate(writeLog(query1, profilo, user));
			statement.executeUpdate(writeLog(query2, profilo, user));
			
			
			result = true;

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;

	}

	public boolean modificaDottoreOnDB(String profilo, String user,
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
            int CodDottore) throws ClassNotFoundException {
		boolean result = false;
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		ResultSet rs = null;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------

			// ipotetiche query
           String query1 = "update Dottori set Nickname = '" + newNickName +
					"', Cognome = '" + Cognome +
					"', Nome = '" + Nome +
	     			"', DataNascita = '" + DataNascita +
		    		"', CodFiscale = '" + CodFiscale +
					"', Cellulare = '" + Cellulare +
					"', Mail = '" + Mail +
					"', Fax = '" + Fax +
					"', CodDottore = '" + CodDottore 
					+ "' where Nickname = '" + oldNickName + "'";
			//aggiornamento tabella Dottori
			statement.executeUpdate(query1);
			
			//aggiornamento tabella USERS
			String query2 = "update Users set Nickname = '"
					+ newNickName + "', Password = '" + pwd
					+ "' where Nickname = '" + oldNickName + "'";
			statement.executeUpdate(query2);
			
			result = true;

			//scrittura del LOG
			statement.executeUpdate(writeLog(query1, profilo, user));
			statement.executeUpdate(writeLog(query2, profilo, user));

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;

	}

	public boolean cancellaDottoreOnDB(String profilo, String nickname,String user)
			throws ClassNotFoundException {
		boolean result = false;
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		ResultSet rs = null;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------

			// ipotetiche query

			//cancellazione del dottore dalla tabella dottori
			String query1 = "delete from Dottori where NickName ='"
					+ user + "'";
			statement.executeUpdate(query1);
			
			String query2 = "delete from Users where NickName ='"
					+ user + "'";
			//cancellazione del dottore dalla tabella Users
			
			statement.executeUpdate(query2);
			result = true;

						
			//scrittura del LOG
			statement.executeUpdate(writeLog(query1, profilo, nickname));
			statement.executeUpdate(writeLog(query2, profilo, nickname));
			
			
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;

	}
	
	
	public boolean registraPazienteOnDB(String profilo, String user,
			 String codFiscale,
			 String nome,
			 String cognome,
			 Date dataNascita,
			 int sesso,
			 String mail,
			 int codDottore,
			 String level, 
			 String pwd)
			throws ClassNotFoundException {
		boolean result = false;
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
	
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------

			// ipotetiche query
            String query1 = "insert into Pazienti values('"+codFiscale+"', '"+nome+"','"+cognome+
            		"','"+dataNascita+"',"+sesso+",'"+mail+"',"+
            		getCodDottByNickNameOnDB(user)+")";
            // inserimento nella tabella Pazienti
			statement.executeUpdate(query1);
			
			// inserimento nella tabella Users
			String query2 = "insert into Users values('" + mail + "','"
					+ pwd + "'," + getCodProfileByDescrOnDB(level) + ")";
			statement.executeUpdate(query2);
			

			//scrittura del LOG
//			statement.executeUpdate(writeLog(query1, profilo, user));
//			statement.executeUpdate(writeLog(query2, profilo, user));
			
			
			result = true;

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;

	}
	
	
	
	
	
	
	

	public boolean verifyFirstAccessOnDB() throws ClassNotFoundException {
		boolean result = false;
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		ResultSet rs = null;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------
			String query = "select count(*) as count from Users";
			rs = statement.executeQuery(query);
			if (rs.getInt("count") == 0)
				result = true;

			
			//scrittura del LOG
			//writeLog(connection, statement, query);

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;

	}

	// quella più completa
	public boolean verifyLoginOnDB(String usr, String pwd)
			throws ClassNotFoundException {
		boolean result = false;
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		ResultSet rs = null;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------

			// ipotetiche query
			// statement.executeUpdate("drop table if exists person");
			// statement.executeUpdate("create table person (id integer, name string)");
			// statement.executeUpdate("insert into person values(1, 'Pippo')");
			// statement.executeUpdate("insert into person values(2, 'Foo')");
			String query = "select * from Users where NickName = '"
					+ usr.toString() + "' and Password = '"
					+ pwd.toString() + "'";
			rs = statement
					.executeQuery(query);
			
			
			
			if (rs.next() == true)
				result = true;
			
			//scrittura del LOG
			String queryLog = "L' utente si è loggato";
			statement.executeUpdate(writeLog(queryLog, getProfileByNicknameOnDB(usr), usr));


			
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;

	}

	
	
	public String getProfileByOwnType(int type) throws ClassNotFoundException {
		String result = null;
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		ResultSet rs = null;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------

			// ipotetiche query
			// statement.executeUpdate("drop table if exists person");
			// statement.executeUpdate("create table person (id integer, name string)");
			// statement.executeUpdate("insert into person values(1, 'Pippo')");
			// statement.executeUpdate("insert into person values(2, 'Foo')");
            String query = "select l.Descrizione " + "from Level as l "
					+ "where l.type = '" + type + "'";
			rs = statement.executeQuery(query);
			result = rs.getString("Descrizione");

			//scrittura del LOG
			//writeLog(connection, statement, query);

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;
	}
	
	
	
	public int getCodProfileByDescrOnDB(String descr)
			throws ClassNotFoundException {
		int result = -1;
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		ResultSet rs = null;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------

			// ipotetiche query
			// statement.executeUpdate("drop table if exists person");
			// statement.executeUpdate("create table person (id integer, name string)");
			// statement.executeUpdate("insert into person values(1, 'Pippo')");
			// statement.executeUpdate("insert into person values(2, 'Foo')");

			String query = "select l.Type " + "from Level as l "
					+ "where l.Descrizione = '" + descr + "'";
			rs = statement.executeQuery(query);
			result = rs.getInt("Type");

			//scrittura del LOG
			//writeLog(connection, statement, query);

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;
	}
	
	public int getCodDottByNickNameOnDB(String user) 	throws ClassNotFoundException {
		int result = -1;
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		ResultSet rs = null;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------

			// ipotetiche query
			// statement.executeUpdate("drop table if exists person");
			// statement.executeUpdate("create table person (id integer, name string)");
			// statement.executeUpdate("insert into person values(1, 'Pippo')");
			// statement.executeUpdate("insert into person values(2, 'Foo')");
            
			String query = "select CodDottore " + "from Dottori "
					+ "where NickName = '" + user + "'";
			rs = statement.executeQuery(query);
			result = rs.getInt("CodDottore");

			//scrittura del LOG
			//writeLog(connection, statement, query);

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;
	}

	
	
	
		public Vector<String> getProfileByLogOnDB() throws ClassNotFoundException {
		Vector<String> result = new Vector<String>();
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		ResultSet rs = null;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------
			String query = "select l.Profilo from Log as l group by l.Profilo";
			rs = statement
					.executeQuery(query);
			
			while (rs.next())
			{
				result.add(rs.getString("Profilo"));
			}
			
					

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;
	}
	
	

		public Vector<String> getNickNameByLogOnDB() throws ClassNotFoundException {
		Vector<String> result = new Vector<String>();
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		ResultSet rs = null;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------
			String query = "select l.NickName from Log as l group by l.NickName";
			rs = statement
					.executeQuery(query);
			
			while (rs.next())
			{
				result.add(rs.getString("NickName"));
			}
			
					

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;
	}
	
	public String getProfileByNicknameOnDB(String user)
			throws ClassNotFoundException {
		String result = "";
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		ResultSet rs = null;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------

			// ipotetiche query
			// statement.executeUpdate("drop table if exists person");
			// statement.executeUpdate("create table person (id integer, name string)");
			// statement.executeUpdate("insert into person values(1, 'Pippo')");
			// statement.executeUpdate("insert into person values(2, 'Foo')");

			String query = "select l.Descrizione "
					+ "from Users as u, Level as l "
					+ "where u.Profilo = l.Type and u.Nickname = '" + user
					+ "'";
			rs = statement.executeQuery(query);
			result = rs.getString("Descrizione");

			//scrittura del LOG
			//writeLog(connection, statement, query);

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;
	}

	public Map<String, Boolean> getUserVisibilityByLevelOnDB(String level)
			throws ClassNotFoundException {
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		ResultSet rs = null;
		ResultSetMetaData rsm = null; //serve per ospitare i nomi delle colonne di una query
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------
			String query = "select *  from Level where Descrizione = '"
					+ level + "'";
			rs = statement
					.executeQuery(query);
			
			rsm = rs.getMetaData();
			//lo faccio partire da 3 così salto le d colonne Type e descrizione e prendo solo i GRANT
			for(int i = 3; i <= rsm.getColumnCount();i++)
				result.put(rsm.getColumnName(i),new Boolean(rs.getBoolean(rsm.getColumnName(i))));

			//scrittura del LOG
			//writeLog(connection, statement, query);

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;
	}
	
	public Vector<String> getInfoAboutDoctorOnDB(String user ) throws ClassNotFoundException {
		Vector<String> result = new Vector<String>();
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		ResultSet rs = null;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------
			String query = "select *  from Dottori where Nickname = '"
					+ user + "'";
			rs = statement
					.executeQuery(query);
			
			result.add(rs.getString("Nickname"));
			result.add(getPasswordByNickNameOnDB(user));
			result.add(rs.getString("Cognome"));
			result.add(rs.getString("Nome"));
			result.add(rs.getString("DataNascita"));
			result.add(rs.getString("CodFiscale"));
			result.add(rs.getString("Cellulare"));
			result.add(rs.getString("Mail"));
			result.add(rs.getString("Fax"));
			
			
			//scrittura del LOG
			//writeLog(connection, statement, query);

			

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;
	}

	
	public Vector<Vector<Object>> getMatriceByLogOnDB() throws ClassNotFoundException {
		Vector<Vector<Object>> result = new Vector<Vector<Object>>();
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		ResultSet rs = null;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------
			String query = "select * from Log";
			rs = statement
					.executeQuery(query);
			
    		ResultSetMetaData rsm = rs.getMetaData(); //serve per poter intercettare il numero di colonne della matrice
			
			Vector<Object> app; //serve per allestire il vettore interno che rappresenta le colonne di ogni riga
			
			while (rs.next())
			{
				app = new Vector<Object>();
				for(int i = 1; i <= rsm.getColumnCount();i++)
					app.addElement(rs.getObject(i));
				
				result.addElement(app);
				
				
			}
			
			//scrittura del LOG
			//writeLog(connection, statement, query);
			

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;
	}
	
	
	public Vector<Vector<Object>> getMatriceByLogFiltroProfiliOnDB(String profilo) throws ClassNotFoundException {
		Vector<Vector<Object>> result = new Vector<Vector<Object>>();
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		ResultSet rs = null;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------
			String query = "select * from Log as l where l.profilo = '"+profilo+"'";
			rs = statement
					.executeQuery(query);
			
    		ResultSetMetaData rsm = rs.getMetaData(); //serve per poter intercettare il numero di colonne della matrice
			
			Vector<Object> app; //serve per allestire il vettore interno che rappresenta le colonne di ogni riga
			
			while (rs.next())
			{
				app = new Vector<Object>();
				for(int i = 1; i <= rsm.getColumnCount();i++)
					app.addElement(rs.getObject(i));
				
				result.addElement(app);
				
				
			}
			
			//scrittura del LOG
			//writeLog(connection, statement, query);
			

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;
	}

	public Vector<Vector<Object>> getMatriceByLogFiltroNickNameOnDB(String nickname) throws ClassNotFoundException {
		Vector<Vector<Object>> result = new Vector<Vector<Object>>();
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		ResultSet rs = null;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------
			String query = "select * from Log as l where l.nickname = '"+nickname+"'";
			rs = statement
					.executeQuery(query);
			
    		ResultSetMetaData rsm = rs.getMetaData(); //serve per poter intercettare il numero di colonne della matrice
			
			Vector<Object> app; //serve per allestire il vettore interno che rappresenta le colonne di ogni riga
			
			while (rs.next())
			{
				app = new Vector<Object>();
				for(int i = 1; i <= rsm.getColumnCount();i++)
					app.addElement(rs.getObject(i));
				
				result.addElement(app);
				
				
			}
			
			//scrittura del LOG
			//writeLog(connection, statement, query);
			

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;
	}
	public Vector<String> getColonneByLogOnDB() throws ClassNotFoundException {
		Vector<String> result = new Vector<String>();
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		ResultSet rs = null;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------
			String query = "select * from Log";
			rs = statement.executeQuery(query);
			
    		ResultSetMetaData rsm = rs.getMetaData();
			
			//allestimento del vettore nomi colonna
				for(int i = 1; i <= rsm.getColumnCount();i++)
					result.addElement(rsm.getColumnName(i));
			
				

				
						
			

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;
	}
	
	
	public Vector<String> getElencoNickNameOnDB(String level)
			throws ClassNotFoundException {
		Vector<String> result = new Vector<String>();
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		ResultSet rs = null;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------
			String query = "select u.Nickname from Users as u, Level as l where u.Profilo = l.Type and  l.Descrizione = '"
					+ level + "'";
			rs = statement
					.executeQuery(query);
			while (rs.next()) {
				result.add(rs.getString("NickName"));
			}

			//scrittura del LOG
			//writeLog(connection, statement, query);

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;
	}

	public ArrayList<String> getElencoProfiliOnDB()
			throws ClassNotFoundException {
		ArrayList<String> result = new ArrayList<String>();
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		ResultSet rs = null;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------
			String query = "select Descrizione from Level";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				result.add(rs.getString("Descrizione"));
			}
			
			//scrittura del LOG
			//writeLog(connection, statement, query);


		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;
	}

	public String getPasswordByNickNameOnDB(String user)
			throws ClassNotFoundException {
		String result = null;
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		ResultSet rs = null;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------
			String query = "select Password from Users where NickName = '" + user + "'";
			rs = statement.executeQuery(query);
				result = (rs.getString("Password"));
				//scrittura del LOG
				//writeLog(connection, statement, query);


		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;
	}
	
	public int getCodDottoreByNickNameOnDB(String user)
			throws ClassNotFoundException {
		int result = -1;
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		ResultSet rs = null;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------
			String query = "select d.CodDottore from dottori as d where d.NickName = '" + user + "'";
			rs = statement.executeQuery(query);
				result = (rs.getInt("CodDottore"));
				//scrittura del LOG
				//writeLog(connection, statement, query);


		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;
	}

	
	public boolean verifyNickNameOnDB(String user)
			throws ClassNotFoundException {
		boolean result = false;
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		ResultSet rs = null;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------
            
			String query = "select NickName from Users where NickName = '"
					+ user.toString() + "'";
			rs = statement
					.executeQuery(query);
			//scrittura del LOG
			//writeLog(connection, statement, query);

			if (rs.next() == true)
				result = true;

			// while(rs.next())
			// {
			// // read the result set
			// System.out.println("name = " + rs.getString("Nome"));
			// System.out.println("cognome = " + rs.getString("Cognome"));
			// System.out.println("data = " + rs.getString("DataNascita"));
			// System.out.println("cod = " + rs.getString("CodFiscale"));
			//
			// }

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;
	}

	
	//classi del FEDE
	public boolean registraClienteOnDB(String CF, String nome, String cognome, int età, int attività, int sesso, int peso, int altezza)
			throws ClassNotFoundException {
				boolean result = false;
				// load the sqlite-JDBC driver using the current class loader
				Class.forName("org.sqlite.JDBC");
				Connection connection = null;
				try {
					// create a database connection
					connection = DriverManager
							.getConnection("jdbc:sqlite:database\\smartDietDB.db");
					Statement statement = connection.createStatement();
					statement.setQueryTimeout(30); // set timeout to 30 sec.
					// ------------------------------------------------------------------------

					// ipotetiche query
					String query = "insert into Pazienti values('" + CF + "','"
							+ nome +"','"+ cognome +"',"+ età+","+attività+"," + sesso + "," + peso + ","+ altezza + ")";
					statement.executeUpdate(query);
					//scrittura del LOG
					//writeLog(connection, statement, query);
					result = true;
					
				} catch (SQLException e) {
					// if the error message is "out of memory",
					// it probably means no database file is found
					System.err.println(e.getMessage());
				} finally {
					try {
						if (connection != null)
							connection.close();
					} catch (SQLException e) {
						// connection close failed.
						System.err.println(e);
					}
				}
				return result;
		}
	
	
	public Vector<String> getNomiPazientiOnDB(int code)
			throws ClassNotFoundException {
		Vector<String> result = new Vector<String>();
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		ResultSet rs = null;
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------
			String query = "select Nome, Cognome  from Pazienti where CodDottore = "
					+ code + " order by Cognome, Nome";

			rs = statement.executeQuery(query);
			String insert = null;
			while (rs.next()) {

				insert = (rs.getString("Cognome")).concat(" ").concat(
						rs.getString("Nome"));
				// result.add(insert);
				result.addElement(insert);
			}

			// scrittura del LOG
			// writeLog(connection, statement, query);

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;
	}
	
	public Vector<String> getDataVisitaFromPazienteOnDB(String nome, String cognome)
			throws ClassNotFoundException {
		Vector<String> result = new Vector<String>();
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		ResultSet rs = null;
		String insert = new String();
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------
			String query = "select DataVisita  from Visita where Nome = '"
					+ nome + "' and Cognome = '" + cognome + "'";
			System.out.println("ESEGUO QUERY DATA");
			rs = statement.executeQuery(query);
			
			while (rs.next()) {
				insert = (rs.getDate("DataVisita")).toString();
				result.addElement(insert);
			}

			// scrittura del LOG
			// writeLog(connection, statement, query);

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

		return result;
	}
	
	public boolean salvaVisitaOnDB(Stack<String> x, Date dataNascita,
			char sesso, int età, char attività, int peso, int altezza,
			int circVita, int circFianchi) throws ClassNotFoundException {
		boolean result = false;
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------

			// ipotetiche query
			String send = "insert into Pazienti values('";
			int counter = 38;
			
			LocalDateTime date = LocalDateTime.now();
			GregorianCalendar g = new GregorianCalendar(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
			java.util.Date u = g.getTime();
			
			
			statement.executeUpdate("insert into Visita values('" +  new java.sql.Date (u.getTime())
					+ "','" + x.pop() + "','" + x.pop() + "','" + dataNascita
					+ "'," + età + ",'" + x.pop() + "','" + sesso + "','"
					+ x.pop() + "','" + attività + "','" + x.pop() + "','"
					+ x.pop() + "','" + x.pop() + "','" + x.pop() + "','"
					+ x.pop() + "','" + x.pop() + "','" + x.pop() + "','"
					+ x.pop() + "','" + x.pop() + "','" + x.pop() + "','"
					+ x.pop() + "','" + x.pop() + "','" + x.pop() + "','"
					+ x.pop() + "','" + x.pop() + "','" + x.pop() + "','"
					+ x.pop() + "','" + x.pop() + "','" + x.pop() + "','"
					+ x.pop() + "','" + x.pop() + "','" + x.pop() + "','"
					+ x.pop() + "','" + x.pop() + "','" + x.pop() + "','"
					+ x.pop() + "','" + x.pop() + "'," + peso + "," + altezza
					+ "," + circVita + "," + circFianchi + ",'" + x.pop()
					+ "','" + x.pop() + "')");

			result = true;

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}
		return result;
	}
	public boolean registraClienteOnDB(String CF, String nome, String cognome,
			int età, char attività, char sesso, int peso, int altezza,
			int codDottore) throws ClassNotFoundException {
		boolean result = false;
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------

			// ipotetiche query

			statement.executeUpdate("insert into Pazienti values('" + CF
					+ "','" + nome + "','" + cognome + "'," + età + ",'"
					+ attività + "','" + sesso + "'," + peso + "," + altezza
					+ "," + codDottore + ")");

			result = true;

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}
		return result;
	}
	
	public Vector<Object> getElementsOfVisitaOnDB(String nome, String cognome,
			java.util.Date data) throws ClassNotFoundException {
		Vector<Object> result = new Vector<Object>();
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		ResultSet rs = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------

			String query = "select * from Visita where Nome = '" + nome
					+ "', Cognome = '" + cognome + "', DataVisita = " + data;

			rs = statement.executeQuery(query);

			// Inserimento dei campi all'interno del Vector result che ritornerà
			// alla funzione
			result.addElement(rs.getDate("DataVisita"));
			result.addElement(rs.getString("Nome"));
			result.addElement(rs.getString("Cognome"));
			result.addElement(rs.getDate("DataNascita"));
			result.addElement(rs.getInt("Eta"));
			result.addElement(rs.getString("CF"));
			result.addElement(rs.getString("Sesso"));
			result.addElement(rs.getString("AttivitaLavorativa"));
			result.addElement(rs.getString("AttivitaFisica"));
			result.addElement(rs.getString("NoteAttivitaFisica"));
			result.addElement(rs.getString("Idratazione"));
			result.addElement(rs.getString("Alcolici"));
			result.addElement(rs.getString("BevandeGasate"));
			result.addElement(rs.getString("PesoMinimo"));
			result.addElement(rs.getString("PesoMassimo"));
			result.addElement(rs.getString("DietePassate"));
			result.addElement(rs.getString("Colazione"));
			result.addElement(rs.getString("SpuntinoMattino"));
			result.addElement(rs.getString("Pranzo"));
			result.addElement(rs.getString("SpuntinoPome"));
			result.addElement(rs.getString("Cena"));
			result.addElement(rs.getString("DopoCena"));
			result.addElement(rs.getString("Carne"));
			result.addElement(rs.getString("Pesce"));
			result.addElement(rs.getString("Formaggio"));
			result.addElement(rs.getString("Affettati"));
			result.addElement(rs.getString("Uova"));
			result.addElement(rs.getString("Tonno"));
			result.addElement(rs.getString("Patate"));
			result.addElement(rs.getString("Verdure"));
			result.addElement(rs.getString("Frutta"));
			result.addElement(rs.getString("Condimenti"));
			result.addElement(rs.getString("Dolci"));
			result.addElement(rs.getString("Pizza"));
			result.addElement(rs.getString("Piace"));
			result.addElement(rs.getString("NonPiace"));
			result.addElement(rs.getInt("Peso"));
			result.addElement(rs.getInt("Altezza"));
			result.addElement(rs.getInt("CircVita"));
			result.addElement(rs.getInt("CircFianchi"));
			result.addElement(rs.getString("Mestruazioni"));
			result.addElement(rs.getString("Conclusioni"));

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}
		return result;
	}
	
	
	//--------------------------------------------------------------------------
	
	
	
	public void registraLogOutOnDB(String profilo, String usr, String query)
			throws ClassNotFoundException {
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:database\\smartDietDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			// ------------------------------------------------------------------------

					
			//scrittura del LOG
			
			statement.executeUpdate(writeLog(query, profilo, usr));


			
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}

	}
	
	

	public String writeLog(String query, String profilo, String user){
		String queryLog = "";
		
		//allestimento data
		LocalDateTime localDateTime = LocalDateTime.now();
		//composizionene della query di iserimento su Log
		queryLog = "insert into Log values((SELECT 1 + COALESCE(MAX(Progressivo), 0) FROM Log),'" + localDateTime + "','"
				+ profilo +"','"+ user +"','"+ query.replace("'", " ")+"')";
		return queryLog;	
	}
	
	
}// fine classe


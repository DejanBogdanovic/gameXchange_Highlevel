package ch.bbcag.gamexchange.highlevel.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MySqlDB {

	static InitialContext ctx;
	static DataSource ds;
	static Connection con = null;
	PreparedStatement prepStmt;
	ResultSet resultSet;
	String sqlState;
	
	String sqlQuery;
	
	/**
	 * Konstruktor: Erstellt eine Verbindung zur Datenbank
	 */
	public MySqlDB() {
		if (con == null) {
			try {
				ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("Onlineshop");
				con = ds.getConnection();
			} catch (NamingException ex) {
				ex.printStackTrace();
				throw new RuntimeException();
				
			} catch (SQLException ex) {
				printSQLException(ex);
				throw new RuntimeException();
			}
		}
	}
	
	/**
	 * Führt eine SELECT-Query aus und liefert das Resultset
	 * @param sqlQuery
	 * @param values
	 * @return ResultSet
	 * @throws Exception 
	 */
	public ResultSet sqlSelect (String sqlQuery, String[] values) {
		resultSet = null;

		try {
			
			prepStmt = con.prepareStatement(sqlQuery);
	        
	        if (values != null) {
	        	setValues(values);
	        }
	        resultSet = prepStmt.executeQuery();
			
		} catch (SQLException ex) {
			sqlState = ex.getSQLState();
			printSQLException(ex);
		}
		return resultSet;
	}
	
	/**
	 * Führt ein UPDATE-Query aus
	 * @param sqlQuery
	 * @param values
	 * @return true=ok / false=Fehler
	 */
	public boolean sqlUpdate (String sqlQuery, String[] values) throws Exception {
		boolean success = false;
		try {
			
			prepStmt = con.prepareStatement(sqlQuery);
	        
	        if (values != null) {
	        	setValues(values);
	        }
	        if (prepStmt.executeUpdate() == 1) 
	        {
				success = true;
	        } else {
	        	success = false;
	        }
			
		} catch (SQLException ex) {
			sqlState = ex.getSQLState();
			if (sqlState.equals("23000")) {           // Referential Integrity verletzt
				throw new Exception("CONSTRAINT");
			} else if (sqlState.equals("22001")) {    // Daten wurden gekürzt
				throw new Exception("TRUNCATE");
			} else {
				printSQLException(ex);
				throw new RuntimeException();
			}
		}
		
		return success;
	}
	
	/**
	 * Führt ein DELETE-Query aus
	 * @param sqlQuery
	 * @param values
	 * @return true=ok / false=Fehler
	 */
	public boolean sqlDelete (String sqlQuery, String[] values) throws Exception {
		boolean success;
		try {
			
			prepStmt = con.prepareStatement(sqlQuery);
	        
	        if (values != null) {
	        	setValues(values);
	        }
	        if (prepStmt.executeUpdate() == 1) 
	        {
				success = true;
	        } else {
	        	success = false;
	        }
			
		} catch (SQLException ex) {
			sqlState = ex.getSQLState();
			if (sqlState.equals("23000")) {     // Delete wegen FK nicht möglich
				throw new Exception("CONSTRAINT");
			} else {
				printSQLException(ex);
				throw new RuntimeException();
			}
			
		}
		
		return success;
	}
	
	/**
	 * Führt ein INSERT-Query aus
	 * @param sqlQuery
	 * @param values
	 * @return Primary Key / null=Fehler
	 */
	public String sqlInsert (String sqlQuery, String[] values) throws Exception {
		String primaryKey = null;;
		try {
			
			prepStmt = con.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
	        
	        if (values != null) {
		        setValues(values);
	        }
	        if (prepStmt.executeUpdate() == 1) 
	        {
	        	resultSet = prepStmt.getGeneratedKeys();    
	        	resultSet.next();  
	        	primaryKey = resultSet.getString(1);
	        } 
			
		} catch (SQLException ex) {
			sqlState = ex.getSQLState();
			if (sqlState.equals("23000")) {           // Referential Integrity verletzt
				throw new Exception("CONSTRAINT");
			} else if (sqlState.equals("22001")) {    // Daten wurden gekürzt
				throw new Exception("TRUNCATE");
			} else {
				printSQLException(ex);
				throw new RuntimeException();
			}
		}
		
		return primaryKey;
	}
	
	/**
	 * Fügt die Werte in das prepared-Statement ein
	 * @param values
	 * @throws SQLException
	 */
	public void setValues(String [] values) throws SQLException {
		for (int count = 0; count < values.length; count++) {
        	prepStmt.setString(count+1, values[count]);
        }
	}
	
	/**
	 * Schliesst das ResultSet und das Prepared Statement
	 */
	public void sqlClose () {
		try {
			resultSet.close();
	        prepStmt.close();
		} catch (SQLException ex) {
			printSQLException(ex);
		}  
	}
	
	/**
	 * Zeigt alle Fehlercodes und Meldungen einer SQL-Exception an
	 * @param ex
	 */
	public static void printSQLException(SQLException ex) {

	    for (Throwable e : ex) {
	        if (e instanceof SQLException) {
	            

                e.printStackTrace(System.err);
                System.err.println("SQLState: " +
                    ((SQLException)e).getSQLState());

                System.err.println("Error Code: " +
                    ((SQLException)e).getErrorCode());

                System.err.println("Message: " + e.getMessage());

                Throwable t = ex.getCause();
                while(t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause(); 
	            }
	        }
	    }
	}
}

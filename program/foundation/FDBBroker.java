package foundation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import acquaintance.*;

/**
 * FDBBroker.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public class FDBBroker
{    
    private ADBInfo dbInfo = null;
    private Connection dbcon = null; 
    private Statement dbstat = null;
    private ResultSet dbresult = null;
    private String[] sqlLines = null;
    private List<IAEntityMapper> entities = new ArrayList<IAEntityMapper>();
    
    public boolean setDBConnection(ADBInfo info){
    	if(!(dbcon==null)) closeDBConnection();
    	dbInfo = info;
    	try{
			Class.forName(info.getDriver());
			dbcon = DriverManager.getConnection(info.getUrl(),info.getUser(),info.getPassword());
			dbstat = dbcon.createStatement();
		}
    	catch (ClassNotFoundException e) {e.printStackTrace();return false;}
		catch (Exception e){e.printStackTrace();return false;}
    	return buildCSSSchema();
    }
    
    private boolean buildCSSSchema(){
    	String[] sqlLine = new String[30];
    
    	try {
			BufferedReader reader = new BufferedReader(new FileReader("css_sql_lines.txt"));
			int line=0;
			do{
				sqlLine[line]=reader.readLine();
				if(!(sqlLine[line].contains("//"))) line++;
			}
			while(!(sqlLine[line-1].contains("eof")));
			reader.close();
			// debug for(String s:sqlLine) System.out.println(s);
		}
    	catch (Exception e) {e.printStackTrace();}
    	
    	try{
    		// Create CSS Schema: line 0-1
    		for(int k=0;k<2;k++) dbstat.execute(sqlLine[k]);
    	
    	}
    	catch(SQLException sqle){sqle.printStackTrace();return false;}
    	
    	/*;

*/
    return true;	
    }
    
    public boolean closeDBConnection(){
    	if(!(dbcon==null)) try {
    		dbcon.close();
    	}
    	catch(SQLException sqle){sqle.printStackTrace();return false;}
    	
    	return true;
    }
    
    public IAEntityMapper getEntityMapper(){
    	IAEntityMapper mapper = new FEntityMapper(this);
    	return mapper;
    }
    
    public boolean registerMapper(IAEntityMapper mapper){
    	entities.add(mapper);
    	// kontroller databasen
    	try {
			dbstat.execute("CREATE SCHEMA IF NOT EXISTS "+mapper.getSchema());
			
			return true;
		}
    	catch (SQLException e) {e.printStackTrace();}
    	
    	
    	return false;
    }
    
}

package foundation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

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
    
    public boolean setDBConnection(ADBInfo info){
    	dbInfo = info;
    	try{
			Class.forName(info.getDriver());
			dbcon = DriverManager.getConnection(info.getUrl(),info.getUser(),info.getPassword());
			dbstat = dbcon.createStatement();
		}
    	catch (ClassNotFoundException e) {e.printStackTrace();return false;}
		catch (Exception e){e.printStackTrace();return false;}
    	finally{return true;}

    	
    }
    
}

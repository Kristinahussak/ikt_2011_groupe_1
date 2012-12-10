package foundation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Field;
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
	private int nextOID = -1;
    private ADBInfo dbInfo = null;
    private Connection dbcon = null; 
    private Statement dbstat = null;
    private ResultSet dbresult = null;
    private String[] sqlLines = null;
    private List<FEntityMapper> entities = new ArrayList<FEntityMapper>();
    
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
    	return true;
    	//return buildCSSSchema(); // Kun til test!!!!!!
    }
    
  /*  private boolean buildCSSSchema(){
    	// Test metode!!!
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
    return true;	
    }*/
    
    public boolean closeDBConnection(){
    	if(!(dbcon==null)) try {dbcon.close();}
    	catch(SQLException sqle){sqle.printStackTrace();return false;}
    	return true;
    }
    
    public IAEntityMapper getEntityMapper(){
    	IAEntityMapper mapper = new FEntityMapper(this);
    	return mapper;
    }
    
    private void executeSQLLine(String sqlString){
    	try {dbstat.execute(sqlString);} 
    	catch (SQLException e) {e.printStackTrace();}
    	System.out.println("SQL: "+sqlString);
    }
    
    public boolean registerMapper(IAEntityMapper mapper) throws SQLException{
    	// er egentlig private men kan kun 'ses' af MBroker der ikke bruger den.
    	// Kaldes fra FEntityMapper.registerMapper()
    	ResultSet result = null;
    	FEntityMapper map = (FEntityMapper)mapper;
    	entities.add(map);
    	executeSQLLine("CREATE SCHEMA IF NOT EXISTS "+map.getSchema()+";");
    	
    	executeSQLLine("CREATE TABLE IF NOT EXISTS "+map.getSchema()+".`oid` (`next_oid` INT ,PRIMARY KEY (`next_oid`));");
    	(result = dbstat.executeQuery("SELECT MAX(`next_oid`) FROM "+map.getSchema()+".`oid`;")).first();
    	nextOID = (result.getInt(1));
    	if(nextOID<1){
    		nextOID=1;
      		executeSQLLine("INSERT INTO "+map.getSchema()+".`oid` SET `next_oid` = 1;");
    	}
    	//System.out.println("DEBUG First OID = "+nextOID);
    	if(map.getDebugDropTable()){executeSQLLine("DROP TABLE IF EXISTS "+map.getSchema()+"."+map.getTableName()+";");}
    	executeSQLLine("CREATE TABLE IF NOT EXISTS "+map.getSchema()+"."+map.getTableName()+map.getCreateColumns()+";");
 		return true;
    }
    
	private Field getField(Class c,String fname) throws NoSuchFieldException{
		// bruges af getvalues
		try {return c.getDeclaredField(fname);}
		catch (NoSuchFieldException e) {
		      Class superClass = c.getSuperclass();
		      if (superClass == null) {throw e;}
		      else {return getField(superClass, fname);}
		}
	}

    public boolean updateEntity(Object entity){
    	// er entitys OID<0 er den ikke i Databasen endnu og skal indsættes ellers updates
    	int oid = -1;
    	ResultSet result = null;
    	String entityName = entity.getClass().getCanonicalName();
    	FEntityMapper map = null;
    	for(FEntityMapper m:entities){if(m.getEntity().equals(entityName)){map=m;}}
    	if(!(map==null)){
    		//System.out.println("debug - map ok - values"+map.getValues(entity));
    		try {
    			String s = null;
    			if(map.getOID()<0){
       				//System.out.println("Debug select : "+s+" , next id : "+nextOID);
  					Field f = getField(entity.getClass(),map.getPKField());
  					f.setAccessible(true);
  					f.setInt(entity, (nextOID));
  					nextOID++;
  					dbstat.executeUpdate("UPDATE "+map.getSchema()+".`oid` SET `next_oid` = "+nextOID+" WHERE `next_oid` = "+(nextOID-1)+";");
  					s = "INSERT INTO "+map.getSchema()+"."+map.getTableName()+map.getColumns()+" values "+map.getValues(entity)+";";
      			}
   				else{s=map.getUpdateString(entity);} 
      			//System.out.println("SQL: exeUpd. : "+s);
      		  	executeSQLLine(s);
   		}
    		catch (SQLException e) {e.printStackTrace();}
			catch (SecurityException e) {e.printStackTrace();}
			catch (NoSuchFieldException e) {e.printStackTrace();} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	else return false; // pågældende entity findes ikke blandt maps!
    	
    	return true;
    }
    
    
}

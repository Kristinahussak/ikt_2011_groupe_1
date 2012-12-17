package foundation;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
    //private ADBInfo dbInfo = null;
    private Connection dbcon = null; 
    private Statement dbstat = null;
 //   private ResultSet dbresult = null;
 //   private String[] sqlLines = null;
    private List<FEntityMapper> entities = new ArrayList<FEntityMapper>();
    private Class lastSuperClass = null;
    
    public boolean setDBConnection(ADBInfo info){
    	if(!(dbcon==null)) closeDBConnection();
    	//dbInfo = info;
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
       	System.out.println("SQL: "+sqlString);
        try {dbstat.execute(sqlString);} 
    	catch (SQLException e) {e.printStackTrace();}
    }
    
    public boolean registerMapper(IAEntityMapper mapper) throws SQLException{
    	// er egentlig private men kan kun 'ses' af MBroker der ikke bruger den.
    	// Kaldes fra FEntityMapper.registerMapper()
    	FEntityMapper map = (FEntityMapper)mapper;
    	entities.add(map);
    	executeSQLLine("CREATE SCHEMA IF NOT EXISTS "+map.getSchema()+";");
    	executeSQLLine("CREATE TABLE IF NOT EXISTS "+map.getSchema()+".`oid` (`oid` INT ,`entity` TEXT,PRIMARY KEY (`oid`));");
    	nextOID = getLastOID(map.getSchema());
    	if(nextOID<0){nextOID=0;}
    	if(map.getDebugDropTable()){executeSQLLine("DROP TABLE IF EXISTS "+map.getSchema()+"."+map.getTableName()+";");}
    	executeSQLLine("CREATE TABLE IF NOT EXISTS "+map.getSchema()+"."+map.getTableName()+map.getCreateColumns()+";");
 		return true;
    }
    
    public ResultSet queryTable(String entity){
     	
        FEntityMapper map = null;
    	for(FEntityMapper m:entities){if(m.getEntity().equals(entity)){map=m;}}
    	if(!(map==null)){
    		String s = "SELECT * FROM "+map.getSchema()+"."+map.getTableName()+";";
    		//System.out.println("Debug query "+s);
    		try {
				return dbstat.executeQuery(s);
			}
    		catch (SQLException e) {e.printStackTrace();}
    	}
    	return null;
    }
    
	private Field getField(Class c,String fname) throws NoSuchFieldException{
		// bruges af getvalues
		try {
			lastSuperClass = c;
			return c.getDeclaredField(fname);}
		catch (NoSuchFieldException e) {
		      Class superClass = c.getSuperclass();
		      lastSuperClass = superClass;
		     if (superClass == null) {throw e;}
		     else {return getField(superClass, fname);}
		}
	}

	
	private int getLastOID(String schema){
		ResultSet result = null;
    	try {
			(result = dbstat.executeQuery("SELECT MAX(`oid`) FROM "+schema+".`oid`;")).first();
			return result.getInt(1);
		}
    	catch (SQLException e) {e.printStackTrace();}
 		return 0;
	}
	
    public boolean putEntity(IAEntity entity) {
    	// er entitys OID<0 er den ikke i Databasen endnu og skal indsættes ellers updates
    	FEntityMapper map = null;
    	for(FEntityMapper m:entities){if(m.getEntity().equals(entity.getClass().getCanonicalName())){map=m;}}
    	if(!(map==null)){
    		try {
    			String s = null;
    			Field f = getField(entity.getClass(),map.getPKField());
    			f.setAccessible(true);
    			if(entity.getOID()<0){ // entity er uinitialiseret!!!
       				entity.setOID(1+getLastOID(map.getSchema()));
       				s = "INSERT INTO "+map.getSchema()+".`oid`(`oid`,`entity`) VALUES ("+entity.getOID()+",'"+entity.getClass().getCanonicalName()+"');";
       				executeSQLLine(s);
       	   			s = "INSERT INTO "+map.getSchema()+"."+map.getTableName()+map.getColumns()+" values "+map.getValues(entity)+";"; 
       	         
    			}
   				else{s=map.getUpdateString(entity);} 
    			executeSQLLine(s); //
   		}
    		catch (SecurityException e) {e.printStackTrace();}
			catch (NoSuchFieldException e) {e.printStackTrace();} catch (IllegalArgumentException e) {e.printStackTrace();}
    	}
    	else return false; // pågældende entity findes ikke blandt maps!
    	return true;
    }
    
  
    public boolean getEntity(IAEntity entity){
    	if(!(entity==null))if(entity.getOID()>-1){
	    	ResultSet result = null;
	    	FEntityMapper map = null;
	    	for(FEntityMapper m:entities){if(m.getEntity().equals(entity.getClass().getCanonicalName())){map=m;}}
	    	if(!(map==null)){
		    	try {
		   			Class c = Class.forName(map.getEntity());
		   			Field[] fields = c.getDeclaredFields();
		   			result = dbstat.executeQuery(map.getQueryString(entity));
		   			ResultSetMetaData meta = result.getMetaData();
		   			result.first();
		   			for(int k=1;k<= meta.getColumnCount();k++){
		   				for(int kk=0;kk<fields.length;kk++){
		    				if(map.getFieldFromColumn(meta.getColumnName(k)).equals(fields[kk].getName())){
		   						String sValue = result.getString(k);
		   						String sType = map.getTypeFromColumn(meta.getColumnName(k));
		    					fields[kk].setAccessible(true);
		    					if(sType.equals("int")) {System.out.println("Go!");/* sValue="2000";*/fields[kk].setInt(entity,(int)Integer.parseInt(sValue));}
		    					if(sType.equals("double")) {System.out.println("Go!"); /*sValue="2000";*/fields[kk].setDouble(entity,(double)Double.parseDouble(sValue));}
		    					if(sType.equals("String")) { fields[kk].set(entity,sValue);}
		    				}
		    			}
		    		}
		    		return true;
		 		} 
		    	catch (ClassNotFoundException e1) {e1.printStackTrace();}
		     	catch (SQLException e) {e.printStackTrace();} 
		    	catch (IllegalArgumentException e) {e.printStackTrace();}
		    	catch (IllegalAccessException e) {e.printStackTrace();}
	    	}	
	    	
    	}
    	return false;
    }    

}

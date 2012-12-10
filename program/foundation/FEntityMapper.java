package foundation;
import java.lang.reflect.Field;
import java.sql.SQLException;

import acquaintance.*;

/**
 * FEntityMapper.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */


public class FEntityMapper implements IAEntityMapper{
	
	private FDBBroker owner = null;
	private String schema = null; 
	private String tableName = null;
	private String entity = null;
	private String primaryKey = null;
	private String columns = "";
	private boolean registered = false;
	private final int relaMax = 100;
	private int relaCount = 0;
	private String[][] relations = new String[relaMax][]; // = 50 relationer
    private boolean debugDropTable = false;
	private int OID = -1;
	private String pkField = "";
	private String pkColumn = "";
	
	public FEntityMapper(FDBBroker owner){
		this.owner = owner;
	}
	
	public boolean registerMap() {
		if(!registered){
			if(!(schema==null))
			 if(!(tableName==null))
			  if(!(entity==null))	
			   if(relaCount>0){
				   // register her
					registered = true;
					try {
						owner.registerMapper(this);
					} catch (SQLException e) {e.printStackTrace();	}
			   }
		}
		
		return registered;
	}

	public void setSchema(String schema) {
		this.schema = "`"+schema+"`";
	}

	@Override
	public void setTable(String tableName) {
		this.tableName = "`"+tableName+"`";
	}

	@Override
	public void setEntity(String entity) {
		this.entity = (entity);
	}

	@Override
	public void setRelation(String field, String colName, String type, String attributes,int keys) {
		if(relaCount<relaMax){
			// ingen kontrol af de 4 felters syntaks
			String vtype = type;if(vtype.contains("String")){vtype = "varchar(1024)";}
			relations[relaCount] = new String[4];
			relations[relaCount][0] = field;
			relations[relaCount][1] = "`"+colName+"`";
			relations[relaCount][2] = vtype;
			relations[relaCount][3] = attributes;
			if((keys==IAEntityMapper.EM_PRIMARY_KEY)){
				primaryKey = "primary key (`"+colName+"`)";
				pkField = field;
				pkColumn = colName;
			}
			relaCount++;
				}
		
	}

	// gettere
	
	public String getSchema(){return schema;}
	
	public String getTableName(){return tableName;}
	
	public String getEntity(){return entity;}
	
	public String getColumns(){
		String s="(";
		for(int k=0;k<relaCount;k++) {s=s+relations[k][1]+",";}
		s=s.substring(0,s.length()-1)+")";
		return s;
	}

	public String getCreateColumns(){
		String s="(";
		for(int k=0;k<relaCount;k++) {s=s+relations[k][1]+" "+relations[k][2]+" "+relations[k][3]+",";}
		s=s+primaryKey+")";
		return s;
	}
	
	
	private Field getField(Class c,String fname) throws NoSuchFieldException{
		// bruges af getvalues
		try {return c.getDeclaredField(fname);}
		catch (NoSuchFieldException e) {
		      Class superClass = c.getSuperclass();
		      if (superClass == null) {System.out.println("GetField fejl");throw e;}
		      else {return getField(superClass, fname);}
		}
	}
	
	
	public String getValues(Object entity){
		// kaldes af fdbbroker. entity passer til dette map (er kontrolleret)
		String s="(";
		for(int k=0;k<relaCount;k++){
			Field field = null;
			try {
				//System.out.println("debug getvalues : "+relations[k][0]);
				field = getField(entity.getClass(),relations[k][0]);
				field.setAccessible(true);
				s=s+field.get(entity)+",";
				if(relations[k][0]=="OID"){OID = (Integer)field.get(entity);}
			}
			catch (SecurityException e) {e.printStackTrace();}
			catch (NoSuchFieldException e) {e.printStackTrace();}
			catch (IllegalArgumentException e) {e.printStackTrace();}
			catch (IllegalAccessException e) {e.printStackTrace();}
		}
		s=s.substring(0,s.length()-1)+")";
		
		return s;
	}
	
	public String getUpdateString(Object entity){
		String s ="UPDATE "+schema+"."+tableName;
		Field f;
		try {
			for(int k=0;k<relaCount;k++){
				System.out.println("Debug getUpdString Loop: "+relations[k][0]);
				f = (entity.getClass()).getField(relations[k][0]);
				f.setAccessible(true);
				s = s+" SET ";
				s=s+ relations[k][1]+" = "+f.get(entity.getClass())+",";
				s=s.substring(0,s.length()-1)+" WHERE "+pkField+" =";
			}
		}
		catch (SecurityException e) {e.printStackTrace();}
		catch (NoSuchFieldException e) {e.printStackTrace();}
		catch (IllegalArgumentException e) {e.printStackTrace();}
		catch (IllegalAccessException e) {e.printStackTrace();}
		
		return s;
	}
	
	public void setDebugDropTable(boolean drop){debugDropTable = drop;};
	public boolean getDebugDropTable(){return debugDropTable;}
	
	public int getOID(){return OID;}
	
	public String getPKField(){return pkField;}
	public String getPKColumn(){return pkColumn;}
		
	
}

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
	private boolean registered = false;
	private final int relaMax = 100;
	private int relaCount = 0;
	//private String[][] relations = new String[relaMax][]; // = 50 relationer
	private Relation[] relations = new Relation[99];
    private boolean debugDropTable = false;
	private int OID = -1;
	private String pkField = "";
	private String pkColumn = "";
	
	public FEntityMapper(FDBBroker owner){
		this.owner = owner;
		setRelation("OID","OID","int",EM_NOT_NULL,EM_PRIMARY_KEY);

	}
	
	public boolean registerMap() {
		if(!registered){
			if(!(schema==null))
			 if(!(tableName==null))
			  if(!(entity==null))	
			   if(relaCount>0){
				   // register her
				   registered = true;
				   try{owner.registerMapper(this);}
				   catch (SQLException e){e.printStackTrace();}
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
	public void setRelation(String field, String columnName, String dataType, String attributes,int keys) {
		if(relaCount<relaMax){
			// ingen kontrol af de 4 felters syntaks
			String vtype = dataType;if(vtype.contains("String")){vtype = "text";}
			relations[relaCount] = new Relation(field,columnName,vtype,attributes);
			
			if((keys==IAEntityMapper.EM_PRIMARY_KEY)){
				primaryKey = "primary key (`"+columnName+"`)";
				pkField = field;
				pkColumn = columnName;
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
		for(int k=0;k<relaCount;k++) {s=s+relations[k].columnName+",";}
		s=s.substring(0,s.length()-1)+")";
		return s;
	}

	public String getCreateColumns(){
		String s="(";
		for(int k=0;k<relaCount;k++) {s=s+relations[k].columnName+" "+relations[k].dataType+" "+relations[k].attributes+",";}
		s=s+primaryKey+")";
		return s;
	}
	
	
	private Field getField(Class c,String fname) throws NoSuchFieldException{
		try {return c.getDeclaredField(fname);}
		catch (NoSuchFieldException e) {
		      Class superClass = c.getSuperclass();
		      if (superClass == null) {System.out.println("GetField fejl");throw e;}
		      else {return getField(superClass, fname);}
		}
	}
	
	
	public String getValues(Object entity){
		String s="(";
		for(int k=0;k<relaCount;k++){
			Field field = null;
			try {
				field = getField(entity.getClass(),relations[k].field);
				field.setAccessible(true);
				if(relations[k].dataType=="text"){s=s+"'"+field.get(entity)+"',";}
				else {s=s+field.get(entity)+",";}
				if(relations[k].field=="OID"){OID = (Integer)field.get(entity);}
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
		String s ="UPDATE "+schema+"."+tableName+" SET ";
		Field f;
		try {
			for(int k=0;k<relaCount;k++){
				//System.out.println("Debug getUpdString Loop: "+relations[k][0]);
				if(!(relations[k].field==pkField)){
					f = getField(entity.getClass(),relations[k].field);
					f.setAccessible(true);
					if(relations[k].dataType=="text"){s=s+ relations[k].columnName+" = '"+f.get(entity)+"',";}
					else{s=s+ relations[k].columnName+" = "+f.get(entity)+",";}
				}
			}
			s=s.substring(0,s.length()-1)+" WHERE "+pkField+" = "+OID+";";
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

 class Relation{
	
	public String field = null;
	public String columnName =  null;
	public String dataType = null;
	public String attributes = null;
	
	public Relation(String field,String columnName,String dataType,String attributes){
		this.field = field;
		this.columnName = columnName;
		this.dataType = dataType;
		this.attributes = attributes;
	}
}

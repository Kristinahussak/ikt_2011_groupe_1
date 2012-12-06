package foundation;
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
	private boolean registered = false;
	private final int relaMax = 100;
	private int relaCount = 0;
	private String[][] relations = new String[relaMax][]; // = 50 relationer

	
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
			   
			   }
		}
		
		return registered;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	@Override
	public void setTable(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public void SetEntity(String entity) {
		this.entity = entity;
	}

	@Override
	public void SetRelation(String field, String colName, String type, String keys) {
		if(relaCount<relaMax){
			// ingen kontrol af de 4 felters syntaks
			relations[relaCount] = new String[4];
			relations[relaCount][0] = field;
			relations[relaCount][1] = colName;
			relations[relaCount][2] = type;
			relations[relaCount][3] = keys;
			relaCount++;
				}
		
	}

	
	
}

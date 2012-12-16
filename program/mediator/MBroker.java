package mediator;

import entity.EItem;
import entity.EOrder;
import acquaintance.*;
import foundation.*;

/**
 * MBroker.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public class MBroker implements IAMBroker{

	private static MBroker broker = null;
	private FDBBroker db = null;
	
	public static MBroker getMBrokerInstance() {
		// Singleton
		if(broker==null) broker = new MBroker();
		return broker;
	}
	
	private MBroker(){}
	
	public boolean setDB(ADBInfo dbInfo){
		if(db==null) db = new FDBBroker(); else db.closeDBConnection();
		return db.setDBConnection(dbInfo);
	
	}
	
	public boolean close(){
		if(!(db==null)) {return db.closeDBConnection();} 
		return true;
	
	}

	public IAEntityMapper getEntityMapper(){return db.getEntityMapper();}
	
	public boolean putEntity(IAEntity entity){return db.putEntity(entity);}
	
	public IAEntity getEntity(IAEntity entity){return db.getEntity(entity);}

	@Override
	public IAMBroker getBroker() {			
		return MBroker.getMBrokerInstance();
	}
    
}

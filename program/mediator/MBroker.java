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

public class MBroker implements IMBroker{

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

	@Override
	public boolean updateItem(EItem item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOrder(EOrder order) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public IAEntityMapper getEntityMapper(){return db.getEntityMapper();}
    
}

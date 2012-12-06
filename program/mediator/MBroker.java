package mediator;

import entity.EItem;
import entity.EOrder;

/**
 * MBroker.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public class MBroker implements IMBroker{

	public static MBroker broker = null;
	
	public static IMBroker getMBroker() {
		// Singleton
		if(broker==null) broker = new MBroker();
		return broker;
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
	
  
    
}

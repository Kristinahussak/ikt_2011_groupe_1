package mediator;
import entity.*;
import acquaintance.*;


/**
 * MBroker.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public interface IMBroker {

	//public IMBroker getBroker();
	public boolean setDB(ADBInfo info);
	public boolean close();
	public boolean updateItem(EItem item);
	public boolean updateOrder(EOrder order);
}

package acquaintance;


/**
 * MBroker.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public interface IAMBroker {

	//public IMBroker getBroker();
	public boolean setDB(ADBInfo info);
	public boolean close();
	public boolean putEntity(IAEntity entity);
	public IAEntity getEntity(IAEntity entity);
	public IAMBroker getBroker();
	
}

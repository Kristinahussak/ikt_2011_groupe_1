package acquaintance;

/**
 * IAComponent.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public interface IAComponent{    
 	
	public boolean add(IAComponent item);
    
	public IAComponent remove(int barcode);
	
	public int[] getPositions();
	
	public boolean isUpdated();
		    
	public int getOID();

	public int getState();

	public void setOID(int OID);
	    
	public void setState(int state);

}


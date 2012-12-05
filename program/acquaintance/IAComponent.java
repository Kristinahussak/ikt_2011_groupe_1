package acquaintance;

/**
 * IAComponent.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

// Testkommentar kan fjernes igen HF
public interface IAComponent{   
	
	//States
	public final static int ORDER_OPEN = 101;
	public final static int ORDER_CLOSED = 102;
	public final static int ITEM_AVAILABLE = 201;
	public final static int ITEM_RESERVED = 202;
	public final static int ITEM_SHIPPED = 203;
	public final static int STOCK_FULL = 301;
	public final static int STOCK_AVAILABLE = 302;
	public final static int STOCK_EMPTY = 303;	
 	
	public boolean add(IAComponent item);
    
	public IAComponent remove(int barcode);
	
	public int[] getPositions();
	
	public boolean isUpdated();
		    
	public int getOID();

	public int getState();

	public void setOID(int OID);
	    
	public void setState(int state);

}

